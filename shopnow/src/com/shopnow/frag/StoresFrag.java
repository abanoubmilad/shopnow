package com.shopnow.frag;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shopnow.R;
import com.shopnow.adapt.StoreAdapter;
import com.shopnow.display.DisplayStore;
import com.shopnow.main.SignIn;
import com.shopnow.main.TaskManager;
import com.shopnow.obj.ItemEntry;
import com.shopnow.obj.StoreEntry;

public class StoresFrag extends Fragment {
	private ListView stores_lv;
	private ProgressBar pBar;

	private class FetchStoresTask extends
			AsyncTask<Void, Void, ArrayAdapter<StoreEntry>> {

		@Override
		protected void onPreExecute() {
			pBar.setVisibility(View.VISIBLE);

		}

		@Override
		protected ArrayAdapter<StoreEntry> doInBackground(Void... params) {
			ArrayList<StoreEntry> stores = TaskManager.getStores();
			if (stores != null)
				return new StoreAdapter(getActivity(), stores);
			return null;
		}

		@Override
		protected void onPostExecute(ArrayAdapter<StoreEntry> result) {
			switch (TaskManager.status) {
			case -1:
				Toast.makeText(getActivity(), "json failed", Toast.LENGTH_SHORT)
						.show();
				break;
			case 1:
				Toast.makeText(getActivity(), "data not sent",
						Toast.LENGTH_SHORT).show();
				break;
			case 0:
				Intent intent = new Intent(getActivity(), SignIn.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				getActivity().finish();
				break;
			case 2:
				Toast.makeText(getActivity(), "invalid params",
						Toast.LENGTH_SHORT).show();
				break;
			case 3:
				Toast.makeText(getActivity(), "db error returned false",
						Toast.LENGTH_SHORT).show();
				break;
			case 7:
				if (result != null)
					stores_lv.setAdapter(result);
				break;
			default:
				break;
			}

			pBar.setVisibility(View.GONE);

		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_stores, container,
				false);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		pBar = (ProgressBar) view.findViewById(R.id.progress);

		stores_lv = (ListView) view.findViewById(R.id.stores_lv);
		((TextView) view.findViewById(R.id.title)).setText("Stores");

		stores_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				ItemEntry item = (ItemEntry) parent.getItemAtPosition(position);
				Intent intent = new Intent(getActivity(), DisplayStore.class);
				intent.putExtra("storeid", item.getId());
				intent.putExtra("name", item.getName());
				startActivity(intent);
			}
		});
		new FetchStoresTask().execute();

	}

}