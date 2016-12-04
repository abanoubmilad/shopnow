package com.shopnow.display;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shopnow.R;
import com.shopnow.adapt.FeaturesAdapter;
import com.shopnow.adapt.PromotionItemAdapter;
import com.shopnow.adapt.StoreAdapter;
import com.shopnow.main.SignIn;
import com.shopnow.main.TaskManager;
import com.shopnow.obj.FeatureEntry;
import com.shopnow.obj.IDItemEntry;
import com.shopnow.obj.ItemEntry;
import com.shopnow.obj.Promotion;
import com.shopnow.obj.StoreEntry;

public class DisplayPromotion extends Activity {
	private String promotionid;
	private ListView details_lv, items_lv, stores_lv;
	private ProgressBar pBar1, pBar2;

	private class FetchPromotionTask extends AsyncTask<Void, Void, Promotion> {

		@Override
		protected void onPreExecute() {
			pBar1.setVisibility(View.VISIBLE);

		}

		@Override
		protected Promotion doInBackground(Void... params) {
			return TaskManager.getPromotion(promotionid);

		}

		@Override
		protected void onPostExecute(Promotion result) {
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
				if (result != null) {
					items_lv.setAdapter(new PromotionItemAdapter(
							getApplicationContext(), result.getPromotionItems()));

					ArrayList<FeatureEntry> entries = new ArrayList<FeatureEntry>(
							5);
					entries.add(new FeatureEntry(result.getName(), "Name"));
					entries.add(new FeatureEntry(result.getPrice(), "Price"));
					entries.add(new FeatureEntry(result.getStartIn(),
							"Starts in"));
					entries.add(new FeatureEntry(result.getEndIn(), "Ends in"));
					details_lv.setAdapter(new FeaturesAdapter(
							getApplicationContext(), entries));

				}
				break;
			default:
				break;
			}

			pBar1.setVisibility(View.GONE);

		}

	}

	private class FetchStoresTask extends
			AsyncTask<Void, Void, ArrayAdapter<StoreEntry>> {

		@Override
		protected void onPreExecute() {
			pBar2.setVisibility(View.VISIBLE);

		}

		@Override
		protected ArrayAdapter<StoreEntry> doInBackground(Void... params) {
			ArrayList<StoreEntry> stores = TaskManager
					.getPromotionRelatedStores(promotionid);
			if (stores != null)
				return new StoreAdapter(getApplicationContext(), stores);
			return null;
		}

		@Override
		protected void onPostExecute(ArrayAdapter<StoreEntry> result) {
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
				if (result != null)
					stores_lv.setAdapter(result);
				break;
			default:
				break;
			}

			pBar2.setVisibility(View.GONE);

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_promotion);
		getActionBar().hide();
		pBar1 = (ProgressBar) findViewById(R.id.progress1);
		pBar2 = (ProgressBar) findViewById(R.id.progress2);
		promotionid = getIntent().getStringExtra("promotionid");

		((TextView) findViewById(R.id.title)).setText(getIntent()
				.getStringExtra("name"));
		((ImageView) findViewById(R.id.back))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});

		details_lv = (ListView) findViewById(R.id.details_lv);
		items_lv = (ListView) findViewById(R.id.items_lv);
		stores_lv = (ListView) findViewById(R.id.stores_lv);
		new FetchPromotionTask().execute();
		new FetchStoresTask().execute();

		items_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				IDItemEntry item = (IDItemEntry) parent
						.getItemAtPosition(position);
				Intent intent = new Intent(getApplicationContext(),
						DisplayItem.class);
				intent.putExtra("itemid", item.getId());
				intent.putExtra("infoid", item.getInfo_id());
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
