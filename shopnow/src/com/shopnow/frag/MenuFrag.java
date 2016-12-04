package com.shopnow.frag;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.shopnow.R;
import com.shopnow.adapt.NavAdapter;
import com.shopnow.main.SignIn;
import com.shopnow.menu.Account;
import com.shopnow.menu.Contact;
import com.shopnow.menu.Preferences;
import com.shopnow.menu.Settings;
import com.shopnow.menu.WatchList;
import com.shopnow.obj.NavItem;

public class MenuFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_menu, container,
				false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ListView lv = (ListView) view.findViewById(R.id.lv);

		ArrayList<NavItem> navItems = new ArrayList<NavItem>(10);
		navItems.add(new NavItem("Watchlist", R.drawable.ic_watch));
		navItems.add(new NavItem("Preferences", R.drawable.ic_preferences));
		navItems.add(new NavItem("Account", R.drawable.ic_account));
		navItems.add(new NavItem("Settings", R.drawable.ic_settings));
		navItems.add(new NavItem("Contact", R.drawable.ic_contact));
		navItems.add(new NavItem("Sign out", R.drawable.ic_signout));

		lv.setAdapter(new NavAdapter(getActivity(), navItems));

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					startActivity(new Intent(getActivity(), WatchList.class));
					break;
				case 1:
					startActivity(new Intent(getActivity(), Preferences.class));

					break;
				case 2:
					startActivity(new Intent(getActivity(), Account.class));

					break;
				case 3:
					startActivity(new Intent(getActivity(), Settings.class));

					break;
				case 4:
					startActivity(new Intent(getActivity(), Contact.class));

					break;
				default:
					Intent intent = new Intent(getActivity(), SignIn.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
					startActivity(intent);
					getActivity().finish();
					break;
				}
			}
		});
	}
}