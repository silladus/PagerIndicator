package com.silladus.pagerindicatorsample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silladus.pagerindicator.PagerIndicator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ViewPager mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        PagerIndicator mPagerIndicator = (PagerIndicator) findViewById(R.id.m_pager_indicator);
        String[] contentArr = new String[3];
        for (int i = 0; i < contentArr.length; i++) {
            contentArr[i] = "this is the page_" + i;
        }
        mViewPager.setAdapter(new MyPagerAdapter(contentArr));
        // 绑定ViewPager
        mPagerIndicator.setViewPager(mViewPager)
                // 设置指示器样式
//                .setIndicatorDrawable(R.drawable.guide_point_select, R.drawable.guide_point_nomal)
                .setIndicatorDrawable(R.drawable.guide_rectangle_select, R.drawable.guide_rectangle_nomal)
                // 设置指示器大小，默认8dp
                .setIndicatorSize(28, 8, 8)
                //页面个数，若不设置则默认取ViewPager页数
                .setPageCount(3)
                // 初始化指示器，这一方法须在做一系列指示器配置后方能调用
                .initDot();
//        mPagerIndicator.setViewPager(mViewPager)// 绑定ViewPager
//                .setIndicatorColorDrawable(Color.GREEN, Color.MAGENTA)// 设置指示器样式
//                .setIndicatorSize(28, 8, 8)// 设置指示器大小，默认8dp
//                .setPageCount(3) //页面个数，若不设置则默认取ViewPager页数
//                .initDot();// 初始化指示器，这一方法须在做一系列指示器配置后方能调用
    }

    private static class MyPagerAdapter extends PagerAdapter {
        @NonNull
        String[] contentArr;

        public MyPagerAdapter(@NonNull String[] contentArr) {
            this.contentArr = contentArr;
        }

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
            TextView tv = new TextView(container.getContext());
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
    }

}
