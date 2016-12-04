package com.shopnow.menu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopnow.R;
import com.shopnow.adapt.PreferencesExpAdapter;
import com.shopnow.main.ConnectManager;
import com.shopnow.obj.PreferencesEntry;

public class Preferences extends Activity {
	private ExpandableListView lv;
	private PreferencesExpAdapter adapter;
	private TextView save, skip;
	private boolean [] currentMasking;

	private class FetchCategoriesTask extends AsyncTask<Void, Void, Boolean> {
		private ProgressDialog pBar;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(Preferences.this);
			pBar.setCancelable(false);
			pBar.setTitle(R.string.msg_progress_loading);
			pBar.setMessage(getResources().getString(R.string.msg_progress_fetch_preferences));
			pBar.show();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			List<String> cats = new ArrayList<String>();
			cats.add("kdckdmc");
			cats.add("kdckdmsddsc");
			cats.add("kdcksddmc");
			cats.add("kdckssdmc");
			cats.add("kdaackdmc");
			cats.add("kdackdmc");
			cats.add("kaadckdmc");
			// List<String> cats = Communicator.getCategories();
			if (cats != null) {
				adapter = new PreferencesExpAdapter(getApplicationContext(), cats);
				return true;
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result)
				lv.setAdapter(adapter);
			pBar.dismiss();

		}

	}

	private class FetchPrefSubcategoriesTask extends
			AsyncTask<Integer, Void, Boolean> {
		private ProgressDialog pBar;
		private int groupPosition;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(Preferences.this);
			pBar.setCancelable(false);
			pBar.setTitle(R.string.msg_progress_loading);
			pBar.setMessage(getResources().getString(R.string.msg_progress_fetch_preferences));
			pBar.show();
		}

		@Override
		protected Boolean doInBackground(Integer... params) {
			groupPosition = params[0].intValue();

			List<PreferencesEntry> subs =null;
//			 subs.add(new ItemCheck("0", "akdnksnkfddddddd",false));
//			 subs.add(new ItemCheck("0", "akdnksnkfddsdddddd",false));
//			 subs.add(new ItemCheck("0", "akdsdnksnkfddddddd",true));
//			 subs.add(new ItemCheck("0", "akaaadnksnkfddddddd",false));
//			 subs.add(new ItemCheck("0", "akdnsssksnkfddddddd",true));
//			 subs.add(new ItemCheck("0", "akssdnksnkfddddddd",true));
			// List<ItemCheck> subs =
			// Communicator.getPrefSubCategories(params[0]);
			if (subs != null) {
				adapter.putSubcategory(adapter.getGroup(groupPosition), subs);
				return true;
			}
			return false;

		}

		@Override
		protected void onPostExecute(Boolean result) {
			adapter.notifyDataSetChanged();
			lv.expandGroup(groupPosition);
			pBar.dismiss();

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preferences);
		getActionBar().hide();
		((TextView) findViewById(R.id.title)).setText("Preferences");
		((ImageView) findViewById(R.id.back))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});
		save = (TextView) findViewById(R.id.save);
		skip = (TextView) findViewById(R.id.skip);

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		skip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		lv = (ExpandableListView) findViewById(R.id.lv_exp);

		lv.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				PreferencesEntry item = adapter.getChild(groupPosition, childPosition);
				item.setChecked(!item.isChecked());
				((CheckBox) v.findViewById(R.id.check)).setChecked(item
						.isChecked());
				return true;
			}

		});

		lv.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				if (!adapter.containsSubcategory(groupPosition))
					if (ConnectManager.isNetworkAvailable(getApplicationContext()))
					new FetchPrefSubcategoriesTask().execute(groupPosition);
			}
		});

		if (ConnectManager.isNetworkAvailable(getApplicationContext()))
			new FetchCategoriesTask().execute();
	}

}