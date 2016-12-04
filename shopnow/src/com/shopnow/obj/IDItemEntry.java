package com.shopnow.obj;

public class IDItemEntry extends ItemEntry {

	private String info_id;

	public IDItemEntry(String id,String info_id, String name) {
		super(id, name);
		this.info_id = info_id;
	}

	public String getInfo_id() {
		return info_id;
	}

	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}

}
