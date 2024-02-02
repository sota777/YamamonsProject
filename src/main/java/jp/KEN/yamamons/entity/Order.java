package jp.KEN.yamamons.entity;

import java.io.Serializable;

public class Order implements Serializable  {
	//在庫管理のエンティティ

	private String orderNo;
	private String orderDate;
	private String itemNo;
	private String customerId;
	private String orderQuantity;
	private String rentalStatusNo;

	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getRentalStatusNo() {
		return rentalStatusNo;
	}
	public void setRentalStatusNo(String rentalStatusNo) {
		this.rentalStatusNo = rentalStatusNo;
	}


}
