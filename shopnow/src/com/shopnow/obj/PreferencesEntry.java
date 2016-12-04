package com.shopnow.obj;

public class PreferencesEntry extends ItemEntry {
	private boolean checked;

	public PreferencesEntry(String id, String name, boolean checked) {
		super(id, name);
		this.checked = checked;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
