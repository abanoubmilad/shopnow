package com.shopnow.adapt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.shopnow.R;
import com.shopnow.obj.ItemEntry;

public class ItemExpAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<String> categories;
	private HashMap<String, List<ItemEntry>> categorySubMap;

	public ItemExpAdapter(Context context, List<String> categories) {
		this.context = context;
		this.categories = categories;
		categorySubMap = new HashMap<String, List<ItemEntry>>(categories.size());
		for (String str : categories) {
			categorySubMap.put(str, new ArrayList<ItemEntry>());
		}
	}

	public void putSubcategory(String category, List<ItemEntry> subcategory) {
		categorySubMap.put(category, subcategory);
	}

	public boolean containsSubcategory(String category) {
		return categorySubMap.get(category).size() != 0;
	}

	public boolean containsSubcategory(int groupPosition) {
		return categorySubMap.get(categories.get(groupPosition)).size() != 0;
	}

	@Override
	public ItemEntry getChild(int groupPosition, int childPosititon) {
		return categorySubMap.get(categories.get(groupPosition)).get(
				childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		ItemEntry item = (ItemEntry) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.item_list_row_view, null);
		}

		TextView name = (TextView) convertView.findViewById(R.id.name);
		name.setText(item.getName());
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return categorySubMap.get(categories.get(groupPosition)).size();
	}

	@Override
	public String getGroup(int groupPosition) {
		return categories.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return categories.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.exp_header, null);
		}

		TextView header = (TextView) convertView.findViewById(R.id.list_header);
		header.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
