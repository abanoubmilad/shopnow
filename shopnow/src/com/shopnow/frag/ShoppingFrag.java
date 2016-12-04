package com.shopnow.frag;

import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shopnow.R;
import com.shopnow.adapt.ItemExpAdapter;
import com.shopnow.display.DisplayItems;
import com.shopnow.main.ConnectManager;
import com.shopnow.main.SignIn;
import com.shopnow.main.TaskManager;
import com.shopnow.obj.ItemEntry;

public class ShoppingFrag extends Fragment {
	private ExpandableListView lv;
	private ItemExpAdapter adapter;
	private ProgressBar pBar;

	private class FetchCategoriesTask extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			pBar.setVisibility(View.VISIBLE);

		}

		@Override
		protected Boolean doInBackground(Void... params) {
			List<String> cats = TaskManager.getItemCategories();
			if (cats != null) {
				adapter = new ItemExpAdapter(getActivity()
						.getApplicationContext(), cats);
				return true;
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			switch (TaskManager.status) {
			case -1:
				Toast.makeText(getActivity(), "json failed", Toast.LENGTH_SHORT)
						.show();
				break;
			case 0:
				Intent intent = new Intent(getActivity(), SignIn.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				getActivity().finish();
				break;
			case 3:
				Toast.makeText(getActivity(), "db error", Toast.LENGTH_SHORT)
						.show();
				break;
			case 7:
				if (result)
					lv.setAdapter(adapter);
				break;
			default:
				break;
			}
			pBar.setVisibility(View.GONE);
			lv.setVisibility(View.VISIBLE);

		}

	}

	private class FetchSubcategoriesTask extends
			AsyncTask<Integer, Void, Boolean> {
		private int groupPosition;

		@Override
		protected void onPreExecute() {
			pBar.setVisibility(View.VISIBLE);

		}

		@Override
		protected Boolean doInBackground(Integer... params) {
			groupPosition = params[0].intValue();
			String groupName = adapter.getGroup(groupPosition);

			List<ItemEntry> subs = TaskManager.getItemSubCategories(groupName);
			if (subs != null) {
				adapter.putSubcategory(groupName, subs);
				return true;
			}
			return false;

		}

		@Override
		protected void onPostExecute(Boolean result) {
			switch (TaskManager.status) {
			case -1:
				Toast.makeText(getActivity(), "json failed", Toast.LENGTH_SHORT)
						.show();
				break;
			case 0:
				Intent intent = new Intent(getActivity(), SignIn.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				getActivity().finish();
				break;
			case 1:
				Toast.makeText(getActivity(), "data not sent",
						Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(getActivity(), "invalid params",
						Toast.LENGTH_SHORT).show();
				break;
			case 3:
				Toast.makeText(getActivity(), "db error", Toast.LENGTH_SHORT)
						.show();
				break;
			case 7:
				if (result) {
					adapter.notifyDataSetChanged();
					lv.expandGroup(groupPosition);
				}
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
		View rootView = inflater.inflate(R.layout.fragment_shopping, container,
				false);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		pBar = (ProgressBar) view.findViewById(R.id.progress);

		lv = (ExpandableListView) view.findViewById(R.id.lv_exp);
		((TextView) view.findViewById(R.id.title)).setText("Shopping");

		lv.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				ItemEntry item = adapter.getChild(groupPosition, childPosition);
				Intent intent = new Intent(getActivity(), DisplayItems.class);
				intent.putExtra("infoid", item.getId());
				intent.putExtra("name", item.getName());
				startActivity(intent);
				return true;
			}

		});
		lv.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				if (!adapter.containsSubcategory(groupPosition))
					if (ConnectManager.isNetworkAvailable(getActivity()
							.getApplicationContext()))
						new FetchSubcategoriesTask().execute(groupPosition);
			}
		});

		if (ConnectManager.isNetworkAvailable(getActivity()
				.getApplicationContext()))
			new FetchCategoriesTask().execute();
	}

}