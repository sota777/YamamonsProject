package jp.KEN.yamamons.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class AdminModel implements Serializable{

	private int itemNo;
	private String itemNunber;
	private String StatusNo;
	@NotEmpty(message="※Not Empty 商品名")
	private String itemName;
	@NotEmpty(message="※Not Empty 入荷数")
	private String itemQuanity;
	@NotEmpty(message="※Not Empty ジャンルナンバー")
	private String genreNo;
	@NotEmpty(message="※Not Empty 監督名")
	private String director;
	@NotEmpty(message="※Not Empty タイプナンバー")
	private String typeNo;
	private String itemPicture;
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemNunber() {
		return itemNunber;
	}
	public void setItemNunber(String itemNunber) {
		this.itemNunber = itemNunber;
	}
	public String getStatusNo() {
		return StatusNo;
	}
	public void setStatusNo(String statusNo) {
		StatusNo = statusNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemQuanity() {
		return itemQuanity;
	}
	public void setItemQuanity(String itemQuanity) {
		this.itemQuanity = itemQuanity;
	}
	public String getGenreNo() {
		return genreNo;
	}
	public void setGenreNo(String genreNo) {
		this.genreNo = genreNo;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public String getItemPicture() {
		return itemPicture;
	}
	public void setItemPicture(String itemPicture) {
		this.itemPicture = itemPicture;
	}



}
