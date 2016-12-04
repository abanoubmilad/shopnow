package com.shopnow.adapt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shopnow.frag.MenuFrag;
import com.shopnow.frag.PromotionsFrag;
import com.shopnow.frag.RecommendFrag;
import com.shopnow.frag.ShoppingFrag;
import com.shopnow.frag.StoresFrag;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			return new ShoppingFrag();
		case 1:
			return new PromotionsFrag();
		case 2:
			return new RecommendFrag();
		case 3:
			return new StoresFrag();
		case 4:
			return new MenuFrag();
		}

		return null;
	}

	@Override
	public int getCount() {
		return 5;
	}

}