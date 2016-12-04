package com.shopnow.obj;

public class WatchListEntry extends IDItemEntry {
	private boolean checked;

	public WatchListEntry(String id, String info_id, String name, boolean checked) {
		super(id, info_id, name);
		this.checked = checked;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}


}
