package com.shopnow.display;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shopnow.R;
import com.shopnow.adapt.ItemAdapter;
import com.shopnow.main.SignIn;
import com.shopnow.main.TaskManager;
import com.shopnow.obj.ItemEntry;
import com.shopnow.opr.CustomItemSearch;

public class DisplayItems extends Activity {
	private String infoid;
	private ListView lv;
	private ProgressBar pBar;

	private class FetchProductsTask extends AsyncTask<Void, Void, ItemAdapter> {

		@Override
		protected void onPreExecute() {
			pBar.setVisibility(View.VISIBLE);

		}

		@Override
		protected ItemAdapter doInBackground(Void... params) {
			ArrayList<ItemEntry> items = TaskManager.getItems(infoid);

			if (TaskManager.status == 7)
				return new ItemAdapter(getApplicationContext(), items);
			return null;

		}

		@Override
		protected void onPostExecute(ItemAdapter result) {
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
				lv.setAdapter(result);
				break;
			default:
				break;
			}
			pBar.setVisibility(View.GONE);

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_items);
		pBar = (ProgressBar) findViewById(R.id.progress);

		infoid = getIntent().getStringExtra("infoid");

		((TextView) findViewById(R.id.title)).setText("Shopping - "
				+ getIntent().getStringExtra("name"));
		((ImageView) findViewById(R.id.back))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});

		TextView custom = (TextView) findViewById(R.id.custom);
		custom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						CustomItemSearch.class);
				intent.putExtra("infoid", infoid);
				intent.putExtra("name", getIntent().getStringExtra("name"));
				startActivity(intent);
			}
		});

		Spinner sp = (Spinner) findViewById(R.id.spin);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		lv = (ListView) findViewById(R.id.lv);

		new FetchProductsTask().execute();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ItemEntry item = (ItemEntry) parent.getItemAtPosition(position);
				Intent intent = new Intent(getApplicationContext(),
						DisplayItem.class);
				intent.putExtra("itemid", item.getId());
				Log.i("id of item", item.getId());
				Log.i("info id is", infoid);
				intent.putExtra("infoid", infoid);
				intent.putExtra("name", item.getName());
				startActivity(intent);
			}
		});
		getActionBar().hide();

	}
}
