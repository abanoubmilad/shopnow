package com.shopnow.obj;

public class PromotionItemEntry extends IDItemEntry {
	private String quantity;

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public PromotionItemEntry(String id, String info_id, String name, String quantity) {
		super(id, info_id, name);
		this.quantity = quantity;
	}

}
