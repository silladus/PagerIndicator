# PagerIndicator
一个简单的页面指示器

效果预览
-------  

![image](https://github.com/silladus/PagerIndicator/blob/master/img/view.png)

示例代码
-------
```java
        // 绑定ViewPager
        mPagerIndicator.setViewPager(mViewPager)
                // 设置指示器样式
                .setIndicatorDrawable(R.drawable.guide_point_select, R.drawable.guide_point_normal)
                // 设置指示器大小，默认8dp
                .setIndicatorSize(6, 6)
                //页面个数，若不设置则默认取ViewPager页数
                .setPageCount(3)
                // 初始化指示器，这一方法须在做一系列指示器配置后方能调用
                .initDot();
```
Installation
-------
Add the JitPack repository in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven {
            url 'https://jitpack.io'
        }
    }
}
```
Add the dependency in your module build.gradle:
```
dependencies {
	implementation 'com.github.silladus:PagerIndicator:1.0.2'
}
```