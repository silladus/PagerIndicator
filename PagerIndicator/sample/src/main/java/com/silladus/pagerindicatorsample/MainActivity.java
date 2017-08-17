package com.silladus.pagerindicatorsample;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silladus.pagerindicator.PagerIndicator;

public class MainActivity extends AppCompatActivity {
    private String[] contentArr = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ViewPager mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mViewPager.setAdapter(initPagerAdapter());
        PagerIndicator mPagerIndicator = (PagerIndicator) findViewById(R.id.m_pager_indicator);
        for (int i = 0; i < contentArr.length; i++) {
            contentArr[i] = "this is the page_" + i;
        }
        mPagerIndicator.setViewPager(mViewPager)// 绑定ViewPager
                .setIndicatorDrawable(R.drawable.guide_point_select, R.drawable.guide_point_nomal)// 设置指示器样式
                .setIndicatorSize(8, 8)// 设置指示器大小，默认8dp
                .setPageCount(3) //页面个数，若不设置则默认取ViewPager页数
                .initDot();// 初始化指示器，这一方法须在做一系列指示器配置后方能调用
    }

    private PagerAdapter initPagerAdapter() {
        return new PagerAdapter() {
            @Override
            public int getCount() {
                return contentArr.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView tv = new TextView(MainActivity.this);
                tv.setText(contentArr[position]);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
                tv.setLayoutParams(lp);
                tv.setGravity(Gravity.CENTER);
                container.addView(tv);
                return tv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        };
    }

}
