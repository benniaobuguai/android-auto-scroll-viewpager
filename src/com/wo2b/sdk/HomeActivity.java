/**
 * 
 */
package com.wo2b.sdk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.wo2b.sdk.samples.AdFragment;

/**
 * @author 笨鸟不乖
 * @email benniaobuguai@gmail.com
 * @version 1.0.0
 * @since 2015-5-4
 * @Modify 2015-5-4
 */
public class HomeActivity extends FragmentActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wo2b_home);
		
		Fragment fragment = new AdFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.ad_container, fragment).commit();
		
	}
}
