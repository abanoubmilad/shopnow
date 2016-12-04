package com.shopnow.opr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shopnow.R;
import com.shopnow.adapt.CustomSearchAdapter;
import com.shopnow.adapt.ItemRankAdapter;
import com.shopnow.display.DisplayItem;
import com.shopnow.main.SignIn;
import com.shopnow.main.TaskManager;
import com.shopnow.obj.CustomSearchEntry;
import com.shopnow.obj.ItemRankEntry;

public class CustomItemSearch extends Activity {
	private String infoid;
	private ListView lv;
	private ListView resultslv;
	private String[] featureKeys;
	private String[] featureValues;
	private View tempView;

	private class FetchItemDetailsTask extends
			AsyncTask<Void, Void, ArrayList<CustomSearchEntry>> {
		private ProgressDialog pBar;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(CustomItemSearch.this);
			pBar.setCancelable(false);
			pBar.setTitle(R.string.msg_progress_loading);
			pBar.setMessage(getResources().getString(
					R.string.msg_progress_loading));
			pBar.show();
		}

		@Override
		protected ArrayList<CustomSearchEntry> doInBackground(Void... params) {
			return TaskManager.getSearchableFeatures(infoid);

		}

		@Override
		protected void onPostExecute(ArrayList<CustomSearchEntry> result) {
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
					lv.setAdapter(new CustomSearchAdapter(
							getApplicationContext(), result));
				featureKeys = new String[result.size()];
				int itr = 0;
				for (CustomSearchEntry customSearchEntry : result) {
					featureKeys[itr++] = customSearchEntry.getId();
				}
				featureValues = new String[result.size()];
				break;
			default:
				break;
			}

			pBar.dismiss();

		}

	}

	private class FetchColumnFeaturesTask extends
			AsyncTask<String, Void, String[]> {
		private ProgressDialog pBar;
		private int position;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(CustomItemSearch.this);
			pBar.setCancelable(false);
			pBar.setTitle(R.string.msg_progress_loading);
			pBar.setMessage(getResources().getString(
					R.string.msg_progress_loading));
			pBar.show();
		}

		@Override
		protected String[] doInBackground(String... params) {
			position = Integer.parseInt(params[0]);
			return TaskManager.getItemColumnFeatures(infoid, params[1]);

		}

		@Override
		protected void onPostExecute(String[] result) {
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
				((CustomSearchEntry) lv.getAdapter().getItem(position))
						.setFeatures(result);
				lv.performItemClick(lv.getChildAt(position), position, lv
						.getAdapter().getItemId(position));

				break;
			default:
				break;
			}

			pBar.dismiss();

		}
	}

	private class CustomSearchTask extends
			AsyncTask<String, Void, ArrayList<ItemRankEntry>> {
		private ProgressDialog pBar;

		@Override
		protected void onPreExecute() {
			pBar = new ProgressDialog(CustomItemSearch.this);
			pBar.setCancelable(false);
			pBar.setTitle(R.string.msg_progress_loading);
			pBar.setMessage(getResources().getString(
					R.string.msg_progress_loading));
			pBar.show();
		}

		@Override
		protected ArrayList<ItemRankEntry> doInBackground(String... params) {

			ArrayList<ItemRankEntry> arr = TaskManager.customSearch(infoid,
					params[0]);
			if (arr == null)
				return null;
			else {
				Collections.sort(arr, new Comparator<ItemRankEntry>() {

					@Override
					public int compare(ItemRankEntry lhs, ItemRankEntry rhs) {
						return lhs.getRank() - rhs.getRank();
					}
				});
				return arr;
			}

		}

		@Override
		protected void onPostExecute(ArrayList<ItemRankEntry> result) {
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
				resultslv.setAdapter(new ItemRankAdapter(
						getApplicationContext(), result));
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
		setContentView(R.layout.activity_custom_item_search);
		getActionBar().hide();

		lv = (ListView) findViewById(R.id.lv);
		resultslv = (ListView) findViewById(R.id.results_lv);
		resultslv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ItemRankEntry item = (ItemRankEntry) parent
						.getItemAtPosition(position);
				Intent intent = new Intent(getApplicationContext(),
						DisplayItem.class);
				intent.putExtra("itemid", item.getId());
				intent.putExtra("infoid", infoid);
				intent.putExtra("name", item.getName());
				startActivity(intent);
			}
		});
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				tempView = view;
				final CustomSearchEntry entry = ((CustomSearchEntry) lv
						.getAdapter().getItem(position));
				if (entry.isPopulated()) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							CustomItemSearch.this);
					builder.setTitle("Make your selection");
					builder.setItems((CharSequence[]) entry.getFeatures(),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									String temp = entry.getItemAtIndex(which);
									((TextView) tempView
											.findViewById(R.id.spin))
											.setText(temp);
									if (!temp.equals("any"))
										featureValues[position] = temp;
								}

							});
					builder.create().show();
				} else {
					new FetchColumnFeaturesTask().execute(position + "",
							entry.getId());
				}

			}
		});

		infoid = getIntent().getStringExtra("infoid");
		((TextView) findViewById(R.id.title)).setText(getIntent()
				.getStringExtra("name") + " - Custom search");
		((ImageView) findViewById(R.id.back))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});
		final TextView searchBtn = ((TextView) findViewById(R.id.search));
		searchBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String features = "";
				for (int i = 0; i < featureKeys.length; i++) {
					if (featureValues[i] != null)
						features += ";" + featureKeys[i] + ";"
								+ featureValues[i];
				}
				if (features.length() != 0) {
					if (features.charAt(0) == ';')
						features = features.substring(1);
					lv.setVisibility(View.GONE);
					resultslv.setVisibility(View.VISIBLE);
					searchBtn.setVisibility(View.GONE);
					new CustomSearchTask().execute(features);
				}
			}
		});

		new FetchItemDetailsTask().execute();

	}
}
