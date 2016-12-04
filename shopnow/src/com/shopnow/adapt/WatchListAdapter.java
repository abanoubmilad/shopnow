package com.shopnow.adapt;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shopnow.R;
import com.shopnow.obj.WatchListEntry;

public class WatchListAdapter extends ArrayAdapter<WatchListEntry> {
	// private static final int uncheck = Color.rgb(228, 110, 96), check = Color
	// .rgb(130, 199, 132);

	public WatchListAdapter(Context context, ArrayList<WatchListEntry> items) {
		super(context, 0, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WatchListEntry item = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.watch_list_row_view, parent, false);
		}
		((TextView) convertView.findViewById(R.id.name))
				.setText(item.getName());
		if (item.isChecked())
			((ImageView) convertView.findViewById(R.id.pic))
					.setBackgroundResource(R.drawable.ic_right);
		else
			((ImageView) convertView.findViewById(R.id.pic))
					.setBackgroundResource(R.drawable.ic_time);
		return convertView;
	}
}