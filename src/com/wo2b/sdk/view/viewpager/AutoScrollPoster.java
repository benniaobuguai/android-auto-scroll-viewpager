package com.wo2b.sdk.view.viewpager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;

import com.wo2b.sdk.R;

/**
 * Auto scroll poster, need a DisplayImageOptions.
 * 
 * @author 笨鸟不乖
 * @email benniaobuguai@gmail.com
 * @version 1.0.0
 * @since 2015-3-21
 * @Modify 2015-3-22
 */
public class AutoScrollPoster extends AutoScrollableView<Integer>
{
	
	private static final String TAG = "AutoScrollPoster";
	
	private ScaleType mScaleType = null;
	private boolean mNeedLoadAnimation = true;
	private Drawable mLoadIndeterminateDrawable = null;
	
	private LayoutInflater mLayoutInflater;
	
	public AutoScrollPoster(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}
	
	public AutoScrollPoster(Context context)
	{
		super(context);
		init();
	}
	
	private void init()
	{
		mLayoutInflater = LayoutInflater.from(getContext());
	}
	
//	/**
//	 * How to display images
//	 * 
//	 * @param options
//	 */
//	public void setDisplayImageOptions(DisplayImageOptions options)
//	{
//		this.mDisplayImageOptions = options;
//	}
	
	/**
	 * Options for scaling the bounds of an image to the bounds of this view.
	 * 
	 * @param scaleType
	 */
	public void setScaleType(ScaleType scaleType)
	{
		this.mScaleType = scaleType;
	}
	
	/**
	 * Needs a load animation.
	 * 
	 * @param needAnimation
	 */
	public void needLoadAnimation(boolean needAnimation)
	{
		this.mNeedLoadAnimation = needAnimation;
	}
	
	/**
	 * {@link ProgressBar#setIndeterminateDrawable(android.graphics.drawable.Drawable)}
	 */
	public void setLoadIndeterminateDrawable(Drawable drawable)
	{
		this.mLoadIndeterminateDrawable = drawable;
	}

	/**
	 * item view click listener.
	 */
	private OnItemViewClickListener mOnItemViewClickListener;
	
	public void setOnItemViewClickListener(OnItemViewClickListener listener)
	{
		this.mOnItemViewClickListener = listener;
	}
	
	/**
	 * ItemView click
	 * 
	 * @author 笨鸟不乖
	 * @email benniaobuguai@gmail.com
	 * @version 1.0.0
	 * @since 2015-4-24
	 * @Modify 2015-4-23
	 */
	public static interface OnItemViewClickListener
	{
		
		void onItemViewClick(View view, Object object);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wo2b.sdk.view.viewpager.IPagerAdapter#instantiateItem(android.view.ViewGroup, int)
	 */
	@Override
	public Object instantiateItem(ViewGroup view, int position)
	{
		final View imageLayout = mLayoutInflater.inflate(R.layout.ad_item, view, false);
		ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
		if (mScaleType != null)
		{
			imageView.setScaleType(mScaleType);
		}
		
		final Integer item = getItem(position);
		
		imageView.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				if (mOnItemViewClickListener != null)
				{
					mOnItemViewClickListener.onItemViewClick(v, item);
				}
			}
		});
		
		imageView.setImageResource(item);
		
		view.addView(imageLayout, 0);
		return imageLayout;
	}
	
}
