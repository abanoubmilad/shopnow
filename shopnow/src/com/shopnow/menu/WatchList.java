package com.shopnow.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shopnow.R;
import com.shopnow.adapt.WatchListAdapter;
import com.shopnow.display.DisplayItem;
import com.shopnow.main.ConnectManager;
import com.shopnow.main.SignIn;
import com.shopnow.main.TaskManager;
import com.shopnow.obj.WatchListEntry;

public class WatchList extends Activity {
	private ListView promotionslv, itemslv;
	private ProgressBar pBar1;
	private ProgressBar pBar2;

	private class FetchItemsWLTask extends
			AsyncTask<Void, Void, WatchListAdapter> {

		@Override
		protected void onPreExecute() {
			pBar2.setVisibility(View.VISIBLE);

		}

		@Override
		protected WatchListAdapter doInBackground(Void... params) {
			ArrayList<WatchListEntry> items = TaskManager.checkItemWatchlist();
			if (items == null)
				return null;
			return new WatchListAdapter(getApplicationContext(), items);
		}

		@Override
		protected void onPostExecute(WatchListAdapter result) {
			switch (TaskManager.status) {
			case -1:
				Toast.makeText(getApplicationContext(), "json failed",
						Toast.LENGTH_SHORT).show();
				break;
			case 0:
				Intent intent = new Intent(getApplicationContext(),
						SignIn.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				finish();
				break;
			case 3:
				Toast.makeText(getApplicationContext(), "db error",
						Toast.LENGTH_SHORT).show();
				break;
			case 7:
				if (result != null)
					itemslv.setAdapter(result);
				break;
			default:
				break;
			}
			pBar2.setVisibility(View.GONE);
			itemslv.setVisibility(View.VISIBLE);

		}

	}

	private class FetchPromotionsWLTask extends
			AsyncTask<Void, Void, WatchListAdapter> {

		@Override
		protected void onPreExecute() {
			pBar1.setVisibility(View.VISIBLE);

		}

		@Override
		protected WatchListAdapter doInBackground(Void... params) {
			ArrayList<WatchListEntry> items = TaskManager
					.checkPromotionWatchlist();
			if (items == null)
				return null;
			return new WatchListAdapter(getApplicationContext(), items);
		}

		@Override
		protected void onPostExecute(WatchListAdapter result) {
			switch (TaskManager.status) {
			case -1:
				Toast.makeText(getApplicationContext(), "json failed",
						Toast.LENGTH_SHORT).show();
				break;
			case 0:
				Intent intent = new Intent(getApplicationContext(),
						SignIn.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				finish();
				break;
			case 3:
				Toast.makeText(getApplicationContext(), "db error",
						Toast.LENGTH_SHORT).show();
				break;
			case 7:
				if (result != null)
					promotionslv.setAdapter(result);

				break;
			default:
				break;
			}
			pBar1.setVisibility(View.GONE);
			promotionslv.setVisibility(View.VISIBLE);

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_watchlist);
		getActionBar().hide();
		pBar1 = (ProgressBar) findViewById(R.id.progress1);
		pBar2 = (ProgressBar) findViewById(R.id.progress2);

		((TextView) findViewById(R.id.title)).setText("Watchlist");
		((ImageView) findViewById(R.id.back))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});

		itemslv = (ListView) findViewById(R.id.items_lv);
		promotionslv = (ListView) findViewById(R.id.promotions_lv);

		itemslv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				WatchListEntry item = (WatchListEntry) parent
						.getItemAtPosition(position);
				if (item.isChecked()) {
					Intent intent = new Intent(getApplicationContext(),
							DisplayItem.class);

					intent.putExtra("itemid", item.getId());
					intent.putExtra("infoid", item.getInfo_id());
					intent.putExtra("name", item.getName());
					startActivity(intent);
				} else {
					Toast.makeText(
							getApplicationContext(),
							"not available yet! We will notify you once it's avaialble",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		promotionslv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				WatchListEntry item = (WatchListEntry) parent
						.getItemAtPosition(position);
				if (item.isChecked()) {
					Intent intent = new Intent(getApplicationContext(),
							DisplayItem.class);
					intent.putExtra("itemid", item.getId());
					intent.putExtra("infoid", item.getInfo_id());
					intent.putExtra("name", item.getName());
					startActivity(intent);
				} else {
					Toast.makeText(
							getApplicationContext(),
							"not available yet! We will notify you once it's avaialble",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		if (ConnectManager.isNetworkAvailable(getApplicationContext())) {
			new FetchItemsWLTask().execute();
			new FetchPromotionsWLTask().execute();
		}
	}

}