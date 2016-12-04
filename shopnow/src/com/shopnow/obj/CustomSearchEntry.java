package com.shopnow.obj;

public class CustomSearchEntry {

	private String[] features;
	private String name;
	private String id;

	public CustomSearchEntry(String id,String name) {
		this.name = name;
		this.id = id;
	}

	public String[] getFeatures() {
		return features;
	}

	public void setFeatures(String[] features) {
		this.features = features;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isPopulated() {
		return this.features != null;
	}

	public String getItemAtIndex(int index) {
		return features[index];
	}

}
