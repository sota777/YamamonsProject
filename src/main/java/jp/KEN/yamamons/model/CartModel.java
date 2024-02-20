package jp.KEN.yamamons.model;

import java.util.ArrayList;

import jp.KEN.yamamons.entity.Items;

public class CartModel {

	private ArrayList<String> cart;
	private ArrayList<Items> cartItems;
	private String itemNo;
	
	public ArrayList<Items> getCartItems() {
		return cartItems;
	}

	public void setCartItems(ArrayList<Items> cartItems) {
		this.cartItems = cartItems;
	}


	

	public CartModel() {
		this.cart = new ArrayList<String>();
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
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
