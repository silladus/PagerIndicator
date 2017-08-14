﻿# PagerIndicator
一个简单的页面指示器

效果预览
-------  

![image](https://github.com/silladus/PagerIndicator/blob/master/PagerIndicator/img/view.png)

示例代码
-------

        mPagerIndicator.setViewPager(carInfoPager)// 绑定ViewPager
                .setIndicatorDrawable(R.drawable.guide_point_red, R.drawable.guide_point_nomal)// 设置指示器样式
                .setIndicatorSize(6)// 设置指示器大小，默认8dp
                .initDot(list.size());// 初始化指示器，这一方法须在做一系列指示器配置后方能调用