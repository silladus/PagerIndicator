package com.silladus.pagerindicator

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.annotation.ColorInt
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.RelativeLayout

/**
 * Created by silladus on 2018/7/5/0005.
 * GitHub: https://github.com/silladus
 * Description:
 */
class PagerIndicator : RelativeLayout, ViewPager.OnPageChangeListener {
    private val mIndicatorContainer = LinearLayout(context)
    private val mIndicatorIndexPoint = View(context)
    private lateinit var mViewPager: ViewPager
    /**
     * 背景圆点
     */
    private var mIndicatorPointDrawableRes: Int = 0
    private lateinit var bgDrawable: Drawable

    private var dis: Int = 0
    private var mIndicatorWidth = 8
    private var mIndicatorHeight = 8
    private var mIndicatorMargin = 8f
    private var mPageCount: Int = 0

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    constructor(ctx: Context, attrs: AttributeSet, defStyleArray: Int)
            : super(ctx, attrs, defStyleArray)

    init {
        this.addView(mIndicatorContainer)
        this.addView(mIndicatorIndexPoint)
    }

    fun setViewPager(mViewPager: ViewPager): PagerIndicator {
        this.mViewPager = mViewPager
        mViewPager.removeOnPageChangeListener(this)
        mViewPager.addOnPageChangeListener(this)
        mPageCount = mViewPager.adapter?.count ?: 1
        return this
    }

    fun setIndicatorDrawable(selectPointDrawableRes: Int, bgPointDrawableRes: Int): PagerIndicator {
        mIndicatorIndexPoint.setBackgroundResource(selectPointDrawableRes)
        this.mIndicatorPointDrawableRes = bgPointDrawableRes
        return this
    }

    fun setIndicatorColorDrawable(@ColorInt selectColor: Int, @ColorInt bgColor: Int): PagerIndicator {
        mIndicatorIndexPoint.background = initPoint(selectColor)
        this.bgDrawable = initPoint(bgColor)
        return this
    }

    fun setIndicatorSize(width: Int, height: Int, margin: Float): PagerIndicator {
        this.mIndicatorWidth = width
        this.mIndicatorHeight = height
        this.mIndicatorMargin = margin
        return this
    }

    fun setPageCount(mPageCount: Int): PagerIndicator {
        this.mPageCount = mPageCount
        return this
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val lp = mIndicatorIndexPoint.layoutParams as RelativeLayout.LayoutParams
        //获取瞬时距离值
        lp.leftMargin = (dis * positionOffset + position * dis).toInt()
        //设置距左属性
        mIndicatorIndexPoint.layoutParams = lp
    }

    override fun onPageSelected(position: Int) {
    }

    private fun initPoint(@ColorInt color: Int): Drawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.OVAL
        gradientDrawable.setSize(mIndicatorWidth, mIndicatorHeight)
        gradientDrawable.setColor(color)
        return gradientDrawable
    }

    private fun resetView() {
        mIndicatorContainer.removeAllViews()
        mIndicatorIndexPoint.layoutParams = RelativeLayout.LayoutParams(
                dp2px(mIndicatorWidth.toFloat()), dp2px(mIndicatorHeight.toFloat()))
        onPageScrolled(mViewPager.currentItem, 0f, 0)
    }

    fun initDot() {
        resetView()
        if (mPageCount > 1) {
            //ViewPager 小圆点初始化
            for (i in 0 until mPageCount) {
                val view = View(context)
                if (mIndicatorPointDrawableRes != 0) {
                    view.setBackgroundResource(mIndicatorPointDrawableRes)
                } else {
                    view.background = bgDrawable
                }
                //设置小圆点的宽高
                val params = LinearLayout.LayoutParams(
                        dp2px(mIndicatorWidth.toFloat()), dp2px(mIndicatorHeight.toFloat())
                )
                //设置小圆点的左间距
                if (i > 0) {
                    params.leftMargin = dp2px(mIndicatorMargin)
                }
                view.layoutParams = params
                //LineaLayout 添加小圆点
                mIndicatorContainer.addView(view)
            }
            //获取圆点与圆点之间的距离
            getDistances()
        }
    }

    //获取圆点与圆点之间的距离
    private fun getDistances() {
        val view0 = mIndicatorContainer.getChildAt(0)
        val view1 = mIndicatorContainer.getChildAt(1)
        //在onDraw方法后才调用，不能直接获取属性值
        mIndicatorContainer.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                dis = view1.left - view0.left
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mIndicatorContainer.viewTreeObserver.removeOnGlobalLayoutListener(this)
                } else {
                    mIndicatorContainer.viewTreeObserver.removeGlobalOnLayoutListener(this)
                }
            }
        })
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private fun dp2px(dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return Math.round(dpValue * scale)
    }
}