package com.shopnow.obj;

import java.util.ArrayList;

public class Promotion {
	String name;
	String startIn;
	String endIn;
	String price;
	ArrayList<PromotionItemEntry> promotionItems;
	public Promotion(String name, String startIn, String endIn,
			String price, ArrayList<PromotionItemEntry> promotionItems) {
		super();
		this.name = name;
		this.startIn = startIn;
		this.endIn = endIn;
		this.price = price;
		this.promotionItems = promotionItems;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartIn() {
		return startIn;
	}
	public void setStartIn(String startIn) {
		this.startIn = startIn;
	}
	public String getEndIn() {
		return endIn;
	}
	public void setEndIn(String endIn) {
		this.endIn = endIn;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public ArrayList<PromotionItemEntry> getPromotionItems() {
		return promotionItems;
	}
	public void setPromotionItems(ArrayList<PromotionItemEntry> promotionItems) {
		this.promotionItems = promotionItems;
	}
	
}
