package com.shopnow.display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shopnow.R;
import com.shopnow.adapt.FeaturesAdapter;
import com.shopnow.adapt.ItemAdapter;
import com.shopnow.adapt.ItemExpAdapter;
import com.shopnow.main.SignIn;
import com.shopnow.main.TaskManager;
import com.shopnow.obj.FeatureEntry;
import com.shopnow.obj.ItemEntry;

public class DisplayStore extends Activity {
	private String storeid;
	private ListView details_lv, promotions_lv;
	private LinearLayout promotionLayout;
	private ExpandableListView item_lv_exp;
	private ItemExpAdapter adapter;
	private ProgressBar pBar1, pBar2, pBar3;

	private class FetchStoreDetailsTask extends
			AsyncTask<Void, Void, FeaturesAdapter> {

		@Override
		protected void onPreExecute() {
			pBar1.setVisibility(View.VISIBLE);

		}

		@Override
		protected FeaturesAdapter doInBackground(Void... params) {
			List<String> details = TaskManager.getStore(storeid);

			if (details != null) {
				ArrayList<FeatureEntry> entries = new ArrayList<FeatureEntry>(5);
				entries.add(new FeatureEntry(details.get(2), "Branch name"));
				entries.add(new FeatureEntry(details.get(3), "Email"));
				entries.add(new FeatureEntry(details.get(4), "Mobile"));
				entries.add(new FeatureEntry(details.get(5), "Tel."));
				entries.add(new FeatureEntry(details.get(6), "Address"));
				return new FeaturesAdapter(getApplicationContext(), entries);
			}
			return null;
		}

		@Override
		protected void onPostExecute(FeaturesAdapter result) {
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
				Toast.makeText(getApplicationContext(),
						"db error returned false", Toast.LENGTH_SHORT).show();
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
					.getStoreRelatedPromotions(storeid);
			if (promotions != null)
				return new ItemAdapter(getApplicationContext(), promotions);
			return null;
		}

		@Override
		protected void onPostExecute(ArrayAdapter<ItemEntry> result) {
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
				promotionLayout.setVisibility(View.GONE);

				Toast.makeText(getApplicationContext(),
						"db error returned false or no offers",
						Toast.LENGTH_SHORT).show();
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

	private class FetchItemsTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			pBar3.setVisibility(View.VISIBLE);

		}

		@Override
		protected Boolean doInBackground(Void... params) {
			HashMap<String, List<ItemEntry>> stores = TaskManager
					.getStoreRelatedItems(storeid);
			if (stores != null) {
				ArrayList<String> keys = new ArrayList<String>(stores.keySet());
				adapter = new ItemExpAdapter(getApplicationContext(), keys);
				for (String str : keys)
					adapter.putSubcategory(str, stores.get(str));
				return true;
			}
			return false;
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
				Toast.makeText(getApplicationContext(),
						"db error returned false", Toast.LENGTH_SHORT).show();
				break;
			case 7:
				if (result)
					item_lv_exp.setAdapter(adapter);
				break;
			default:
				break;
			}

			pBar3.setVisibility(View.GONE);

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_store);
		getActionBar().hide();
		pBar1 = (ProgressBar) findViewById(R.id.progress1);
		pBar2 = (ProgressBar) findViewById(R.id.progress2);
		pBar3 = (ProgressBar) findViewById(R.id.progress3);
		storeid = getIntent().getStringExtra("storeid");

		promotionLayout = (LinearLayout) findViewById(R.id.promotions_linear_layout);
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
		((TextView) findViewById(R.id.items_tv))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

					}
				});

		details_lv = (ListView) findViewById(R.id.details_lv);
		promotions_lv = (ListView) findViewById(R.id.promotions_lv);

		item_lv_exp = (ExpandableListView) findViewById(R.id.items_lv_exp);
		item_lv_exp.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				ItemEntry item = adapter.getChild(groupPosition, childPosition);
				Intent intent = new Intent(getApplicationContext(),
						DisplayItems.class);
				intent.putExtra("infoid", item.getId());
				intent.putExtra("storeid", item.getId());
				intent.putExtra("name", item.getName());
				startActivity(intent);
				return true;
			}

		});
		new FetchStoreDetailsTask().execute();
		new FetchItemsTask().execute();
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

	}
}
