package com.wo2b.sdk.samples;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;

import com.wo2b.sdk.R;
import com.wo2b.sdk.view.viewpager.AutoScrollPoster;
import com.wo2b.sdk.view.viewpager.AutoScrollPoster.OnItemViewClickListener;
import com.wo2b.sdk.view.viewpager.ZoomOutPageTransformer;

/**
 * Ad Fragment
 * 
 * @author 笨鸟不乖
 * @email benniaobuguai@gmail.com
 * @version 1.0.0
 * @since 2015-5-6
 * @Modify 2015-5-6
 */
public class AdFragment extends Fragment implements OnClickListener
{
	
	private static final String TAG = "AdFragment";
	
	private AutoScrollPoster mPosterView;
	private Button mBtnAddAnimation;
	private Button mBtnRemoveAnimation;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.ad_fragment, container, false);
		initView(view);
		
		return view;
	}
	
	protected void initView(View view)
	{
		mBtnAddAnimation = (Button) view.findViewById(R.id.btn_add);
		mBtnRemoveAnimation = (Button) view.findViewById(R.id.btn_remove);
		mPosterView = (AutoScrollPoster) view.findViewById(R.id.viewPager);
		// mPosterView.setDisplayImageOptions(displayImageOptions);
		mPosterView.setScaleType(ScaleType.FIT_XY);
		mPosterView.needLoadAnimation(false);
		
		List<Integer> imageList = new ArrayList<Integer>();
		imageList.add(R.drawable.ads_1);
		imageList.add(R.drawable.ads_2);
		imageList.add(R.drawable.ads_3);
		imageList.add(R.drawable.ads_4);
		
		mPosterView.addItems(imageList);
		mPosterView.startAutoScroll(5 * 1000);
		
		mPosterView.setOnItemViewClickListener(new OnItemViewClickListener()
		{
			
			@Override
			public void onItemViewClick(View view, Object item)
			{
				Toast.makeText(getActivity(), "Click...", Toast.LENGTH_SHORT).show();
			}
		});
		
		mBtnAddAnimation.setOnClickListener(this);
		mBtnRemoveAnimation.setOnClickListener(this);
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		Log.i(TAG, "Resume scroll...");
		mPosterView.resumeScroll();
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		Log.i(TAG, "Stop scroll...");
		mPosterView.stopScroll();
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_add:
			{
				mPosterView.setPageTransformer(true, new ZoomOutPageTransformer());
				break;
			}
			case R.id.btn_remove:
			{
				mPosterView.setPageTransformer(true, null);
				break;
			}
		}
	}
	
}
