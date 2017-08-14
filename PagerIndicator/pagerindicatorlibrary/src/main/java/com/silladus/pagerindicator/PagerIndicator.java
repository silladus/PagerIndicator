package com.silladus.pagerindicator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by silladus on 2017/8/3/0003.
 */

public class PagerIndicator extends RelativeLayout implements ViewPager.OnPageChangeListener {
    private Context mContext;
    private LinearLayout mIndicatorContainer;
    private View mIndicatorIndexPoint;
    private ViewPager mViewPager;
    private int mIndicatorPointDrawableRes;
    private int dis;
    private float mPointSize = 8;

    public PagerIndicator(Context context) {
        this(context, null);
    }

    public PagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mIndicatorContainer = new LinearLayout(mContext);
        mIndicatorIndexPoint = new View(context);
        this.addView(mIndicatorContainer);
        this.addView(mIndicatorIndexPoint);
    }

    public PagerIndicator setViewPager(@NonNull ViewPager mViewPager) {
        this.mViewPager = mViewPager;
        mViewPager.removeOnPageChangeListener(this);
        mViewPager.addOnPageChangeListener(this);
        return this;
    }

    public PagerIndicator setIndicatorDrawable(int selectPointDrawableRes, int bgPointDrawableRes) {
        mIndicatorIndexPoint.setBackgroundResource(selectPointDrawableRes);
        this.mIndicatorPointDrawableRes = bgPointDrawableRes;
        return this;
    }

    public PagerIndicator setIndicatorSize(float mPointSize) {
        this.mPointSize = mPointSize;
        return this;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //获取瞬时距离值
        int left = (int) (dis * positionOffset + position * dis);
        LayoutParams params = (LayoutParams) mIndicatorIndexPoint.getLayoutParams();
        params.leftMargin = left;
        //设置距左属性
        mIndicatorIndexPoint.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void resetView() {
        mIndicatorContainer.removeAllViews();
        LayoutParams mLP = new LayoutParams(dp2px(mPointSize), dp2px(mPointSize));
        mIndicatorIndexPoint.setLayoutParams(mLP);
        onPageScrolled(mViewPager.getCurrentItem(), 0, 0);
    }

    public void initDot(int size) {
        resetView();
        if (size > 1) {
            //ViewPager 小圆点初始化
            for (int i = 0; i < size; i++) {
                View view = new View(mContext);
                view.setBackgroundResource(mIndicatorPointDrawableRes);
                //设置小圆点的宽高
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dp2px(mPointSize), dp2px(mPointSize));
                //设置小圆点的左间距
                if (i > 0) {
                    params.leftMargin = 15;
                }
                view.setLayoutParams(params);
                //LineaLayout 添加小圆点
                mIndicatorContainer.addView(view);
            }
            //获取圆点与圆点之间的距离
            getDistancesPoint();
        }
    }

    //获取圆点与圆点之间的距离
    private void getDistancesPoint() {
        final View view0 = mIndicatorContainer.getChildAt(0);
        final View view1 = mIndicatorContainer.getChildAt(1);
        //在onDraw方法后才调用，不能直接获取属性值
        mIndicatorContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                dis = view1.getLeft() - view0.getLeft();
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mIndicatorContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                } else {
//                    guide_ll_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                }
            }
        });
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dp2px(float dpValue) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return Math.round(dpValue * scale);
    }

}
