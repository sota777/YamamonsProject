package jp.KEN.yamamons.entity;

public class RentalHistory {
	private String orderDate;
	private String itemNo;
	private String itemName;
	private String customerId;
	private String customerName;
	private String rentalStatusNo;
	private String genreNo;
	private String orderNo;
		private String itemPicture;

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

	public String getRentalStatusNo() {
		return rentalStatusNo;
	}

	public void setRentalStatusNo(String rentalStatusNo) {
		this.rentalStatusNo = rentalStatusNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getGenreNo() {
		return genreNo;
	}

	public void setGenreNo(String genreNo) {
		this.genreNo = genreNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getItemPicture() {
		return itemPicture;
	}

	public void setItemPicture(String itemPicture) {
		this.itemPicture = itemPicture;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
