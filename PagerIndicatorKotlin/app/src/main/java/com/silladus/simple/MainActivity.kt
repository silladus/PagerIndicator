package com.silladus.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.silladus.pagerindicator.PagerIndicator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val mViewPager = findViewById<ViewPager>(R.id.mViewPager)
        val mPagerIndicator = findViewById<PagerIndicator>(R.id.m_pager_indicator)
        val contentArr = arrayOfNulls<String?>(3)
        for (i in contentArr.indices) {
            contentArr[i] = "this is the page_$i"
        }
        mViewPager.adapter = MyPagerAdapter(contentArr)
        // 绑定ViewPager
        mPagerIndicator.setViewPager(mViewPager)
                // 设置指示器样式
                //.setIndicatorDrawable(R.drawable.guide_point_select, R.drawable.guide_point_nomal)
                .setIndicatorDrawable(R.drawable.guide_rectangle_select, R.drawable.guide_rectangle_nomal)
                // 设置指示器大小，默认8dp
                .setIndicatorSize(28, 8, 8f)
                //页面个数，若不设置则默认取ViewPager页数
                .setPageCount(3)
                // 初始化指示器，这一方法须在做一系列指示器配置后方能调用
                .initDot()
        //        mPagerIndicator.setViewPager(mViewPager)// 绑定ViewPager
        //                .setIndicatorColorDrawable(Color.GREEN, Color.MAGENTA)// 设置指示器样式
        //                .setIndicatorSize(28, 8, 8)// 设置指示器大小，默认8dp
        //                .setPageCount(3) //页面个数，若不设置则默认取ViewPager页数
        //                .initDot();// 初始化指示器，这一方法须在做一系列指示器配置后方能调用
    }

    private class MyPagerAdapter(internal var contentArr: Array<String?>) : PagerAdapter() {

        override fun getCount(): Int {
            return contentArr.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val tv = TextView(container.context)
            tv.text = contentArr[position]
            val lp = ViewGroup.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT)
            tv.layoutParams = lp
            tv.gravity = Gravity.CENTER
            container.addView(tv)
            return tv
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }
}
