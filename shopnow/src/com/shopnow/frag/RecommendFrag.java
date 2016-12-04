package com.shopnow.frag;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shopnow.R;
import com.shopnow.adapt.ItemIDAdapter;
import com.shopnow.display.DisplayItem;
import com.shopnow.main.SignIn;
import com.shopnow.main.TaskManager;
import com.shopnow.obj.IDItemEntry;

public class RecommendFrag extends Fragment {
	private EditText input;
	private ListView lv;
	// private TextView body;
	private LinearLayout search_layout, results_layout;
	private ProgressBar pBar;

	private class SmartSearchTask extends AsyncTask<String, Void, IDItemEntry> {

		@Override
		protected void onPreExecute() {
			pBar.setVisibility(View.VISIBLE);

		}

		@Override
		protected IDItemEntry doInBackground(String... params) {
			return TaskManager.smartSearch(params[0]);

		}

		@Override
		protected void onPostExecute(IDItemEntry result) {
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
			case 6:
				Toast.makeText(getActivity(), "Opps, we could not recommend!",
						Toast.LENGTH_SHORT).show();
				break;
			case 7:
				if (result != null) {
					ArrayList<IDItemEntry> arr = new ArrayList<>(1);
					arr.add(result);
					lv.setAdapter(new ItemIDAdapter(getActivity(), arr));
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

		View rootView = inflater.inflate(R.layout.fragment_recommend,
				container, false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		pBar = (ProgressBar) view.findViewById(R.id.progress);

		input = (EditText) view.findViewById(R.id.search_input);

		search_layout = (LinearLayout) view.findViewById(R.id.search_layout);
		results_layout = (LinearLayout) view.findViewById(R.id.results_layout);

		input = (EditText) view.findViewById(R.id.search_input);
		((ImageView) view.findViewById(R.id.back))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						results_layout.setVisibility(View.GONE);
						search_layout.setVisibility(View.VISIBLE);
					}
				});
		((TextView) view.findViewById(R.id.title)).setText("Let Us Recommend");

		((TextView) view.findViewById(R.id.search))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String str = input.getText().toString().trim();
						if (str.length() != 0) {
							new SmartSearchTask().execute(str);
							search_layout.setVisibility(View.GONE);
							results_layout.setVisibility(View.VISIBLE);
						}
					}
				});

		lv = ((ListView) view.findViewById(R.id.lv));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				IDItemEntry item = (IDItemEntry) parent
						.getItemAtPosition(position);
				Intent intent = new Intent(getActivity(),
						DisplayItem.class);
				intent.putExtra("itemid", item.getId());
				intent.putExtra("infoid", item.getInfo_id());
				intent.putExtra("name", item.getName());
				startActivity(intent);
			}
		});
		// body = ((TextView) view.findViewById(R.id.body));
	}
}