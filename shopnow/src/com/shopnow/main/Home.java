package com.shopnow.main;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.shopnow.R;
import com.shopnow.adapt.TabsPagerAdapter;

public class Home extends FragmentActivity implements ActionBar.TabListener {

	private ViewPager viewPager;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		TabsPagerAdapter mAdapter;
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());


		
		viewPager.setAdapter(mAdapter);
		
		actionBar.setDisplayShowHomeEnabled(false);              
		actionBar.setDisplayShowTitleEnabled(false);
		
//		
//		View mCustomView = LayoutInflater.from(this).inflate(R.layout.custom_actionbar, null);
//		actionBar.setCustomView(mCustomView);
//		actionBar.setDisplayShowCustomEnabled(true);
//		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		// Adding Tabs
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_shopping)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_promotions)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_recommend)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_stores)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.ic_nav)
				.setTabListener(this));


		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

}