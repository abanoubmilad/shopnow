package com.shopnow.obj;

public class NavItem {

	private String title;
	private int icon;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public NavItem(String title, int icon) {
		super();
		this.title = title;
		this.icon = icon;
	}

	

}
