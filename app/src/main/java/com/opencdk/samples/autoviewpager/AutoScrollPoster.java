package com.opencdk.samples.autoviewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.opencdk.view.viewpager.AutoScrollableView;
import com.opencdk.samples.R;

/**
 * Auto scroll poster, need a DisplayImageOptions.
 *
 * @author 笨鸟不乖
 * @version 2.0.0
 * @email benniaobuguai@gmail.com
 * @Modify 2015-11-08
 * @since 2015-3-21
 */
public class AutoScrollPoster extends AutoScrollableView<String> {

    private static final String TAG = "AutoScrollPoster";

    private ScaleType mScaleType = null;
    private boolean mNeedLoadAnimation = true;
    private Drawable mLoadIndeterminateDrawable = null;

    private LayoutInflater mLayoutInflater;

    private ImageLoader mImageLoader = null;
    private DisplayImageOptions mDisplayImageOptions;

    public AutoScrollPoster(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoScrollPoster(Context context) {
        super(context);
        init();
    }

    private void init() {
        mLayoutInflater = LayoutInflater.from(getContext());

        // Get singleton instance
        mImageLoader = ImageLoader.getInstance();
        mDisplayImageOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.image_loading)
                .showImageOnFail(R.drawable.image_loading)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .considerExifParams(true)
                .build();

    }

    /**
     * How to display images
     *
     * @param options
     */
    public void setDisplayImageOptions(DisplayImageOptions options) {
        this.mDisplayImageOptions = options;
    }

    /**
     * Options for scaling the bounds of an image to the bounds of this view.
     *
     * @param scaleType
     */
    public void setScaleType(ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    /**
     * Needs a load animation.
     *
     * @param needAnimation
     */
    public void needLoadAnimation(boolean needAnimation) {
        this.mNeedLoadAnimation = needAnimation;
    }

    /**
     * {@link android.widget.ProgressBar#setIndeterminateDrawable(android.graphics.drawable.Drawable)}
     */
    public void setLoadIndeterminateDrawable(Drawable drawable) {
        this.mLoadIndeterminateDrawable = drawable;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        final View imageLayout = mLayoutInflater.inflate(R.layout.poster_list_item, view, false);
        ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
        if (mScaleType != null) {
            imageView.setScaleType(mScaleType);
        }

        final String imageUri = getItem(position);

        imageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mOnItemViewClickListener != null) {
                    mOnItemViewClickListener.onItemViewClick(v, imageUri);
                }
            }
        });

        // Load image, decode it to Bitmap and display Bitmap in ImageView (or any other view
        //  which implements ImageAware interface)
        // mImageLoader.displayImage(imageUri, imageView);

        mImageLoader.displayImage(imageUri, imageView, mDisplayImageOptions, new SimpleImageLoadingListener() {

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

            }

            @Override
            public void onLoadingStarted(String imageUri, View view) {
                super.onLoadingStarted(imageUri, view);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                super.onLoadingFailed(imageUri, view, failReason);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                super.onLoadingCancelled(imageUri, view);
            }

        });

        view.addView(imageLayout, 0);
        return imageLayout;
    }

    /**
     * item view click listener.
     */
    private OnItemViewClickListener mOnItemViewClickListener;

    public void setOnItemViewClickListener(OnItemViewClickListener listener) {
        this.mOnItemViewClickListener = listener;
    }

    /**
     * ItemView click
     *
     * @author 笨鸟不乖
     * @version 1.0.0
     * @email benniaobuguai@gmail.com
     * @Modify 2015-4-23
     * @since 2015-4-24
     */
    public static interface OnItemViewClickListener {

        void onItemViewClick(View view, Object object);

    }

}
