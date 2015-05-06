<p>
<img src="https://github.com/benniaobuguai/android-auto-scroll-viewpager/blob/master/screenshot/1.png" width="220" alt="Screenshot"/>
<img src="https://github.com/benniaobuguai/android-auto-scroll-viewpager/blob/master/screenshot/2.png" width="220" alt="Screenshot"/>
<img src="https://github.com/benniaobuguai/android-auto-scroll-viewpager/blob/master/screenshot/3.png" width="220" alt="Screenshot"/>
</p>


**1. 无限循环轮播的ViewPager，切换平滑**  
**2. 可以添加切换动画效果，最低支持到版本2.2**  
**3. 支持左右无限循环播放**  
**4. 正在切换时，手指放至ViewPager时，停止轮播**  
**5. 手动切换时，轮播会停止，手动切换结束后，继续轮播**  
**6. 手动切换效果，不会因轮播造成破坏**  
**7. 切换至其它Activity时，可控制停止播放**  

  
  
每次轮播完成后，切换速度会自动调整，代码如下：
<code>
	/**
	 * Scroll itself
	 */
	protected void scrollSelf()
	{
		Field field = null;
		if (mAutoScroller == null)
		{
			mAutoScroller = new AutoScroller(getContext(), new AccelerateInterpolator());
		}
		
		try
		{
			field = ViewPagerCompat.class.getDeclaredField("mScroller");
			field.setAccessible(true);
			field.set(this, mAutoScroller);
			// 时长因子++
			mAutoScroller.setFactor(AutoScroller.FACTOR_LONG);
		}
		catch (Exception e)
		{
			Log.e(TAG, "", e);
		}
		
		int newPosition = getCurrentItem() + 1;
		this.setCurrentItem(newPosition, true);
		
		try
		{
			field = ViewPagerCompat.class.getDeclaredField("mScroller");
			field.setAccessible(true);
			field.set(this, mAutoScroller);
			// 时长因子--
			mAutoScroller.setFactor(AutoScroller.FACTOR_SHORT);
		}
		catch (Exception e)
		{
			Log.e(TAG, "", e);
		}
	}
	
</code>  

更多使用技巧, 请关注: [https://github.com/benniaobuguai/android-project-wo2b](https://github.com/benniaobuguai/android-project-wo2b)


