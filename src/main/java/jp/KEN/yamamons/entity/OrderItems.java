package jp.KEN.yamamons.entity;

public class OrderItems extends Items{

	private String orderDate;

	private String rentalStatusNo;



	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getRentalStatusNo() {
		return rentalStatusNo;
	}

	public void setRentalStatusNo(String rentalStatusNo) {
		this.rentalStatusNo = rentalStatusNo;
	}

}
