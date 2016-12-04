package com.shopnow.obj;

public class ItemRankEntry extends ItemEntry {

	private int rank;

	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public ItemRankEntry(String id, String name, int rank) {
		super(id, name);
		this.rank = rank;
	}

	

}
