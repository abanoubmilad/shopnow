package com.shopnow.display;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shopnow.R;
import com.shopnow.adapt.FeaturesAdapter;
import com.shopnow.adapt.ItemAdapter;
import com.shopnow.adapt.StoreAdapter;
import com.shopnow.main.SignIn;
import com.shopnow.main.TaskManager;
import com.shopnow.obj.FeatureEntry;
import com.shopnow.obj.ItemEntry;
import com.shopnow.obj.StoreEntry;

public class DisplayItem extends Activity {
	private String itemid;
	private String infoid;
	private ListView details_lv, promotions_lv, stores_lv;
	private LinearLayout watchlistLayout;
	private ProgressBar pBar1, pBar2, pBar3;
	
	
	private class FetchItemDetailsTask extends
			AsyncTask<Void, Void, FeaturesAdapter> {

		@Override
		protected void onPreExecute() {
			pBar1.setVisibility(View.VISIBLE);

		}

		@Override
		protected FeaturesAdapter doInBackground(Void... params) {
			List<String> details = TaskManager.getItem(itemid, infoid);
			List<String> names = TaskManager.getItemColumnNames(infoid);

			if (details != null && names != null) {
				ArrayList<FeatureEntry> entries = new ArrayList<FeatureEntry>(
						names.size());
				for (int i = 0, e = 2; i < names.size(); i++, e++)
					entries.add(new FeatureEntry(details.get(e), names.get(i)));
				return new FeaturesAdapter(getApplicationContext(), entries);
			}
			return null;
		}

		@Override
		protected void onPostExecute(FeaturesAdapter result) {
			switch (TaskManager.status) {
			case -1:
//				Toast.makeText(getApplicationContext(), "json failed",
//						Toast.LENGTH_SHORT).show();
				break;

			case 1:
//				Toast.makeText(getApplicationContext(), "data not sent",
//						Toast.LENGTH_SHORT).show();
				break;
			case 0:
				Intent intent = new Intent(getApplicationContext(),
						SignIn.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				finish();
				break;
			case 2:
				Toast.makeText(getApplicationContext(), "invalid params",
						Toast.LENGTH_SHORT).show();
				break;
			case 3:
//				Toast.makeText(getApplicationContext(),
//						"db error returned false", Toast.LENGTH_SHORT).show();
				break;
			case 7:
				if (result != null)
					details_lv.setAdapter(result);
				break;
			default:
				break;
			}

			pBar1.setVisibility(View.GONE);

		}

	}

	private class FetchPromotionsTask extends
			AsyncTask<Void, Void, ArrayAdapter<ItemEntry>> {

		@Override
		protected void onPreExecute() {
			pBar2.setVisibility(View.VISIBLE);

		}

		@Override
		protected ArrayAdapter<ItemEntry> doInBackground(Void... params) {
			ArrayList<ItemEntry> promotions = TaskManager
					.getItemRelatedPromotions(itemid, infoid);
			if (promotions != null)
				return new ItemAdapter(getApplicationContext(), promotions);
			return null;
		}

		@Override
		protected void onPostExecute(ArrayAdapter<ItemEntry> result) {
			switch (TaskManager.status) {
			case -1:
//				Toast.makeText(getApplicationContext(), "json failed",
//						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "data not sent",
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
			case 2:
				Toast.makeText(getApplicationContext(), "invalid params",
						Toast.LENGTH_SHORT).show();
				break;
			case 3:
				promotions_lv.setVisibility(View.GONE);
				watchlistLayout.setVisibility(View.VISIBLE);

//				Toast.makeText(getApplicationContext(),
//						"db error returned false or no offers",
//						Toast.LENGTH_SHORT).show();
				break;
			case 7:
				if (result != null)
					promotions_lv.setAdapter(result);
				break;
			default:
				break;
			}

			pBar2.setVisibility(View.GONE);

		}

	}

	private class FetchStoresTask extends
			AsyncTask<Void, Void, ArrayAdapter<StoreEntry>> {

		@Override
		protected void onPreExecute() {
			pBar3.setVisibility(View.GONE);

		}

		@Override
		protected ArrayAdapter<StoreEntry> doInBackground(Void... params) {
			ArrayList<StoreEntry> stores = TaskManager.getItemRelatedStores(
					itemid, infoid);
			if (stores != null)
				return new StoreAdapter(getApplicationContext(), stores);
			return null;
		}

		@Override
		protected void onPostExecute(ArrayAdapter<StoreEntry> result) {
			switch (TaskManager.status) {
			case -1:
//				Toast.makeText(getApplicationContext(), "json failed",
//						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "data not sent",
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
			case 2:
				Toast.makeText(getApplicationContext(), "invalid params",
						Toast.LENGTH_SHORT).show();
				break;
			case 3:
				Toast.makeText(getApplicationContext(),
						"db error returned false", Toast.LENGTH_SHORT).show();
				break;
			case 7:
				if (result != null)
					stores_lv.setAdapter(result);
				break;
			default:
				break;
			}

			pBar3.setVisibility(View.GONE);

		}

	}

	private class addPromotionWLTask extends AsyncTask<Void, Void, Boolean> {
		private ProgressDialog pBar;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(DisplayItem.this);
			pBar.setCancelable(false);
			pBar.setTitle(R.string.msg_progress_loading);
			pBar.setMessage(getResources().getString(
					R.string.msg_progress_add_watchlist));
			pBar.show();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			return TaskManager.addPromotionWatchlist(itemid, infoid,
					"this is name");
		}

		@Override
		protected void onPostExecute(Boolean result) {
			switch (TaskManager.status) {
			case -1:
//				Toast.makeText(getApplicationContext(), "json failed",
//						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "data not sent",
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
			case 2:
				Toast.makeText(getApplicationContext(), "invalid params",
						Toast.LENGTH_SHORT).show();
				break;
			case 3:
//				Toast.makeText(getApplicationContext(),
//						"db error returned false", Toast.LENGTH_SHORT).show();
				break;
			case 7:
				if (result == true)
					Toast.makeText(getApplicationContext(),
							"promotion was sent to watchlist",
							Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(getApplicationContext(),
							"could not add to watchlist, please try again later", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}

			pBar.dismiss();

		}

	}

	private class addItemWLTask extends AsyncTask<Void, Void, Boolean> {
		private ProgressDialog pBar;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(DisplayItem.this);
			pBar.setCancelable(false);
			pBar.setTitle(R.string.msg_progress_loading);
			pBar.setMessage(getResources().getString(
					R.string.msg_progress_add_watchlist));
			pBar.show();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			return TaskManager.addItemWatchlist(itemid, infoid, "this is name");
		}

		@Override
		protected void onPostExecute(Boolean result) {
			switch (TaskManager.status) {
			case -1:
				Toast.makeText(getApplicationContext(), "json failed",
						Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(getApplicationContext(), "data not sent",
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
			case 2:
				Toast.makeText(getApplicationContext(), "invalid params",
						Toast.LENGTH_SHORT).show();
				break;
			case 3:
//				Toast.makeText(getApplicationContext(),
//						"db error returned false", Toast.LENGTH_SHORT).show();
				break;
			case 7:
				if (result == true)
					Toast.makeText(getApplicationContext(),
							"added to watchlist", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(getApplicationContext(),
							"could not add to watchlist, please try again later", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}

			pBar.dismiss();

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_item);
		getActionBar().hide();
		pBar1 = (ProgressBar) findViewById(R.id.progress1);
		pBar2 = (ProgressBar) findViewById(R.id.progress2);
		pBar3 = (ProgressBar) findViewById(R.id.progress3);

		itemid = getIntent().getStringExtra("itemid");
		infoid = getIntent().getStringExtra("infoid");

		watchlistLayout = (LinearLayout) findViewById(R.id.add_linear_layout);
		watchlistLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new addPromotionWLTask().execute();
			}
		});

		((TextView) findViewById(R.id.title)).setText(getIntent()
				.getStringExtra("name"));
		((ImageView) findViewById(R.id.back))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});

		((TextView) findViewById(R.id.details_tv))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
					}
				});
		((TextView) findViewById(R.id.promotions_tv))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
					}
				});
		((TextView) findViewById(R.id.stores_tv))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

					}
				});

		details_lv = (ListView) findViewById(R.id.details_lv);
		promotions_lv = (ListView) findViewById(R.id.promotions_lv);
		stores_lv = (ListView) findViewById(R.id.stores_lv);

		new FetchItemDetailsTask().execute();
		new FetchStoresTask().execute();
		new FetchPromotionsTask().execute();

		promotions_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ItemEntry item = (ItemEntry) parent.getItemAtPosition(position);
				Intent intent = new Intent(getApplicationContext(),
						DisplayPromotion.class);
				intent.putExtra("promotionid", item.getId());
				intent.putExtra("name", item.getName());
				startActivity(intent);
			}
		});
		stores_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				ItemEntry item = (ItemEntry) parent.getItemAtPosition(position);
				Intent intent = new Intent(getApplicationContext(),
						DisplayStore.class);
				intent.putExtra("storeid", item.getId());
				intent.putExtra("name", item.getName());
				startActivity(intent);
			}
		});

	}
}
