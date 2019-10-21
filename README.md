# ctool能力描述

> Java

# IO

> Android

# context[强依赖上下文]

## TContext

1. set/get上下文

2. TSP

3. TToast

4. TVibrator

# System

## ISystemInterface简化接口

1. TextChangedWatcher
	
	仅监听输入框变化的TextWatcher

2. AnimatorEndListener

	仅监听动画结束的AnimatorListener

3. PageChangeListener

	仅监听页面选中的OnPageChangeListener

## netstate网络相关

1. NetBroadcastReceiver

	监听网络状态的广播

2. TNetwork

	a. 获取ip：getIp()

## sensor传感器

1. 屏幕亮度TBrightness

2. 闪光灯TFlash

3. 震动TVibrator

## view相关

### action事件相关

1. AntiShakeClickListener

	防抖的OnClickListener

### activity

### listview

1. TempItem

	预置选项条目

### recyclerview

1. ItemClickSupport

	支持recyclerview点击事件

2. CustomBindingAdapter & ListBindingAdapter

	RecyclerView.Adapter的简化

### textview

1. TTextView

	a. 文本高亮：highlight()

### viewpager

1. NoScrollViewPager

	控制viewpager切页是否支持滑动和点击是否有滑动切页效果

## 其他

