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
import com.shopnow.obj.NavItem;

public class NavAdapter extends ArrayAdapter<NavItem> {

	public NavAdapter(Context context, ArrayList<NavItem> items) {
		super(context, 0, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NavItem item = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.menu_list_row_view, parent, false);
		}
		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

		imgIcon.setImageResource(item.getIcon());
		txtTitle.setText(item.getTitle());

		return convertView;
	}
}
