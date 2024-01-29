package jp.KEN.yamamons.model;

import java.io.Serializable;
import java.util.ArrayList;

public class CartModel implements Serializable {
	private ArrayList<String> cart;
	private String itemNo;

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	
	public CartModel() {
		this.cart = new ArrayList<String>();
	}

	public ArrayList<String> getCart() {
		return cart;
	}

	public void setCart(ArrayList<String> cart) {
		this.cart = cart;
	}

	public void addCart(String item) {
		this.cart.add(item);
	}
}
