package com.shopnow.adapt;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shopnow.R;
import com.shopnow.obj.PromotionItemEntry;

public class PromotionItemAdapter extends ArrayAdapter<PromotionItemEntry> {

	public PromotionItemAdapter(Context context, ArrayList<PromotionItemEntry> items) {
		super(context, 0, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PromotionItemEntry item = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.store_list_row_view, parent, false);
		}
		((TextView) convertView.findViewById(R.id.text1)).setText(item
				.getName());
		((TextView) convertView.findViewById(R.id.text2)).setText(item.getQuantity());

		return convertView;
	}
}