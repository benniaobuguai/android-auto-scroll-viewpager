package com.wo2b.samples;

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

import com.opencdk.view.viewpager.ZoomOutPageTransformer;
import com.wo2b.samples.autoviewpager.AutoScrollPoster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Poster fragment
 *
 * @author 笨鸟不乖
 * @version 2.0.0
 * @email benniaobuguai@gmail.com
 * @Modify 2015-11-08
 * @since 2015-5-6
 */
public class PosterFragment extends Fragment implements OnClickListener {

    private static final String TAG = "PosterFragment";

    private AutoScrollPoster mPosterView;
    private Button mBtnAddAnimation;
    private Button mBtnRemoveAnimation;

    /**
     * local or net image
     */
    private boolean mLocalData = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.poster_list, container, false);
        initView(view);

        return view;
    }

    protected void initView(View view) {
        mBtnAddAnimation = (Button) view.findViewById(R.id.btn_add);
        mBtnRemoveAnimation = (Button) view.findViewById(R.id.btn_remove);
        mPosterView = (AutoScrollPoster) view.findViewById(R.id.viewPager);
        // mPosterView.setDisplayImageOptions(displayImageOptions);
        mPosterView.setScaleType(ScaleType.FIT_XY);
        mPosterView.needLoadAnimation(false);

        List<String> imageList = new ArrayList<String>();
        if (mLocalData) {
            // local images
            imageList.addAll(Arrays.asList(TestData.IMAGE_FROM_LOCAL));
        } else {
            // net images
            imageList.addAll(Arrays.asList(TestData.IMAGE_FROM_NET));
        }

        mPosterView.addItems(imageList);
        mPosterView.startAutoScroll(5 * 1000);

        mPosterView.setOnItemViewClickListener(new AutoScrollPoster.OnItemViewClickListener() {

            @Override
            public void onItemViewClick(View view, Object item) {
                Toast.makeText(getActivity(), "Click...", Toast.LENGTH_SHORT).show();
            }
        });

        mBtnAddAnimation.setOnClickListener(this);
        mBtnRemoveAnimation.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "Resume scroll...");
        mPosterView.resumeScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "Stop scroll...");
        mPosterView.stopScroll();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add: {
                mPosterView.setPageTransformer(true, new ZoomOutPageTransformer());
                break;
            }
            case R.id.btn_remove: {
                mPosterView.setPageTransformer(true, null);
                break;
            }
        }
    }

}
