package com.shopnow.adapt;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shopnow.R;
import com.shopnow.obj.CustomSearchEntry;

public class CustomSearchAdapter extends ArrayAdapter<CustomSearchEntry> {

	public CustomSearchAdapter(Context context,
			ArrayList<CustomSearchEntry> items) {
		super(context, 0, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CustomSearchEntry item = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.custom_search_row_view, parent, false);
		}
		((TextView) convertView.findViewById(R.id.name))
				.setText(item.getName());

		return convertView;
	}
}