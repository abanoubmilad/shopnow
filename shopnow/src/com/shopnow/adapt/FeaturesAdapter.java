package com.shopnow.adapt;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shopnow.R;
import com.shopnow.obj.FeatureEntry;

public class FeaturesAdapter extends ArrayAdapter<FeatureEntry> {

	public FeaturesAdapter(Context context,
			ArrayList<FeatureEntry> items) {
		super(context, 0, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FeatureEntry item = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.feature_list_row_view, parent, false);
		}
		((TextView) convertView.findViewById(R.id.feature_name))
				.setText(item.getName());
		((TextView) convertView.findViewById(R.id.feature_value))
		.setText(item.getValue());

//		Spinner spinner = (Spinner) convertView.findViewById(R.id.spin);
//		ArrayAdapter<String> adapt = new ArrayAdapter<String>(pcontext,
//				android.R.layout.simple_spinner_item, android.R.id.text1);
//		adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinner.setAdapter(adapt);
//		adapt.add("any");
//		adapt.notifyDataSetChanged();

		//
		// ArrayAdapter<String> adpt = new ArrayAdapter<String>(pcontext,
		// android.R.layout.simple_list_item_1, new ArrayList<String>() {
		// {
		// add("any");
		// }
		// });
		// adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// ((Spinner) convertView.findViewById(R.id.spin)).setAdapter(adpt);
		return convertView;
	}
}