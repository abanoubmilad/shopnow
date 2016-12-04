package com.shopnow.obj;

public class StoreEntry extends ItemEntry{
	private String branchName;

	public StoreEntry(String id, String name, String branchName) {
		super(id, name);
		this.branchName = branchName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}


}
