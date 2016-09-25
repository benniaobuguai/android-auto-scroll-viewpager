/**
 *
 */
package com.opencdk.samples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;


/**
 * @author 笨鸟不乖
 * @version 1.0.0
 * @email benniaobuguai@gmail.com
 * @since 2015-5-4
 * @Modify 2015-5-4
 */
public class HomeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Fragment fragment = new PosterFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.poster_container, fragment).commit();

    }
}
