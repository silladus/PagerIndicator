# PagerIndicator
一个简单的页面指示器

效果预览
-------  

![image](https://github.com/silladus/PagerIndicator/blob/master/PagerIndicator/img/view.png)

示例代码
-------
```java
        mPagerIndicator.setViewPager(mViewPager)// 绑定ViewPager
                .setIndicatorDrawable(R.drawable.guide_point_select, R.drawable.guide_point_nomal)// 设置指示器样式
                .setIndicatorSize(6, 6)// 设置指示器大小，默认8dp
				.setPageCount(3) //页面个数，若不设置则默认取ViewPager页数
                .initDot();// 初始化指示器，这一方法须在做一系列指示器配置后方能调用
```
	
或者
```java 
		mPagerIndicator.setViewPager(mViewPager)// 绑定ViewPager
                .setIndicatorColorDrawable(Color.GREEN, Color.MAGENTA)// 设置指示器样式
                .setIndicatorSize(8, 8)// 设置指示器大小，默认8dp
                .setPageCount(3) //页面个数，若不设置则默认取ViewPager页数
                .initDot();// 初始化指示器，这一方法须在做一系列指示器配置后方能调用
```