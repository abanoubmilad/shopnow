package com.shopnow.adapt;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shopnow.R;
import com.shopnow.obj.ItemRankEntry;

public class ItemRankAdapter extends ArrayAdapter<ItemRankEntry> {

	public ItemRankAdapter(Context context, ArrayList<ItemRankEntry> items) {
		super(context, 0, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemRankEntry item = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.item_list_row_view, parent, false);
		}
		TextView name = (TextView) convertView.findViewById(R.id.name);
		name.setText(item.getName());
		return convertView;
	}
}