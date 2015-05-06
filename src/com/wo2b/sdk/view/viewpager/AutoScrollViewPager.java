package com.wo2b.sdk.view.viewpager;

import java.lang.reflect.Field;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;

/**
 * Auto scroll viewpager
 * 
 * @author 笨鸟不乖
 * @email benniaobuguai@gmail.com
 * @version 1.0.0
 * @since 2015-3-21
 * @Modify 2015-4-22
 */
public class AutoScrollViewPager extends ViewPagerCompat
{
	
	private static final String TAG = "AutoScrollViewPager";
	
	/**
	 * 默认切换周期
	 */
	private static final int SCROLL_PERIOD_DEFAULT = 3 * 1000;
	
	/**
	 * 当前切换周期
	 */
	private long mScrollPeriod = SCROLL_PERIOD_DEFAULT;
	
	/**
	 * 默认开始的位置
	 */
	private int mBeginIndex = 0;
	
	/**
	 * 是否可以自动播放
	 */
	private boolean mAutoScrollable = false;
	
	/**
	 * 是否被停止
	 */
	private boolean mStopped = false;
	
	private AutoScroller mAutoScroller;
	
	private Handler mHandler = new Handler();
	
	private Runnable mScrollRunnable = new Runnable()
	{
		
		@Override
		public void run()
		{
			scrollSelf();
			
			mHandler.postDelayed(this, mScrollPeriod);
		}
	};
	
	/**
	 * 
	 * @param context
	 */
	public AutoScrollViewPager(Context context)
	{
		super(context);
		setUp();
	}
	
	/**
	 * 
	 * @param context
	 * @param attrs
	 */
	public AutoScrollViewPager(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		setUp();
	}
	
	private void setUp()
	{
		// do nothing.
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		int action = MotionEventCompat.getActionMasked(ev);
		
		if (action == MotionEvent.ACTION_DOWN)
		{
			stopScroll();
		}
		else if (ev.getAction() == MotionEvent.ACTION_UP)
		{
			resumeScroll();
		}
		
		this.getParent().requestDisallowInterceptTouchEvent(true);
		
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		try
		{
			return super.onInterceptTouchEvent(ev);
		}
		catch (IllegalArgumentException e)
		{
			// 多点触控异常
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Set default image background
	 * 
	 * @param resid
	 */
	public void setLoadingImage(int resid)
	{
		// TODO:
	}
	
	/**
	 * Start Scroll
	 * 
	 * @param duration
	 */
	public void startAutoScroll(int duration)
	{
		startAutoScroll(duration, 0);
	}
	
	/**
	 * Auto Scroll
	 * 
	 * @param duration
	 */
	public void startAutoScroll(int duration, int position)
	{
		this.mScrollPeriod = duration;
		
		if (this.getAdapter() instanceof AutoScrollPagerAdapter<?>)
		{
			this.mBeginIndex = position + 10000 * ((AutoScrollPagerAdapter<?>) this.getAdapter()).getCountReal();
		}
		else
		{
			this.mBeginIndex = position + 10000 * this.getAdapter().getCount();
		}
		
		this.mAutoScrollable = true;
		this.setCurrentItem(mBeginIndex);
		
		mHandler.removeCallbacks(mScrollRunnable);
		mHandler.postDelayed(mScrollRunnable, mScrollPeriod);
	}
	
	/**
	 * Resume Scroll
	 */
	public void resumeScroll()
	{
		if (mStopped)
		{
			mHandler.removeCallbacks(mScrollRunnable);
			mHandler.postDelayed(mScrollRunnable, mScrollPeriod);
		}
	}
	
	/**
	 * Change Scroll Period
	 * 
	 * @param duration
	 */
	public void changeScrollPeriod(int duration)
	{
		this.mScrollPeriod = duration;
	}
	
	/**
	 * Stop Scroll
	 */
	public void stopScroll()
	{
		mHandler.removeCallbacks(mScrollRunnable);
		mStopped = true;
	}
	
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
	
}
