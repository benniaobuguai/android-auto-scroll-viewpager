<p>
<img src="https://github.com/benniaobuguai/android-auto-scroll-viewpager/blob/master/screenshot/1.png" width="270" alt="Screenshot"/>
</p>

***

无限循环播放的ViewPager，可添加切换动画，最低支持到版本2.2。


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


#### 更多使用技巧, 请关注: [https://github.com/benniaobuguai/android-project-wo2b](https://github.com/benniaobuguai/android-project-wo2b)


