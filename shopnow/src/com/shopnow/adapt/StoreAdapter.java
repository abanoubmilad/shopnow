package com.shopnow.adapt;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shopnow.R;
import com.shopnow.obj.StoreEntry;

public class StoreAdapter extends ArrayAdapter<StoreEntry> {

	public StoreAdapter(Context context, ArrayList<StoreEntry> stores) {
		super(context, 0, stores);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		StoreEntry store = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.store_list_row_view, parent, false);
		}
		((TextView) convertView.findViewById(R.id.text1)).setText(store.getName());
		((TextView) convertView.findViewById(R.id.text2)).setText(store.getBranchName());
		
		return convertView;
	}
}