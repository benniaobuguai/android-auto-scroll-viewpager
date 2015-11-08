package com.wo2b.samples;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * @author 笨鸟不乖
 * @version 2.0.0
 * @email benniaobuguai@gmail.com
 * @Modify 2015-11-08
 * @since 2015-5-3
 */
public class GApplication extends Application {

    private static final String TAG = "Global.Application";

    @Override
    public void onCreate() {
        super.onCreate();

        initImageLoader(this);
    }

    /**
     * ImageLoader init.
     *
     * @param context
     */
    protected void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                        //.writeDebugLogs() // Remove for release app
                .build();

        ImageLoader.getInstance().init(config);
    }


}
