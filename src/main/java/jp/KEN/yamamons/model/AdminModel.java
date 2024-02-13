package jp.KEN.yamamons.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class AdminModel implements Serializable{

	private int itemNo;
	private int StatusNo;
	@NotEmpty(message="商品名は必須です")
	private String itemName;
	private String itemQuanity;
	private String genreNo;
	@NotEmpty(message="監督名は必須です")
	private String director;
	private String typeNo;
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public int getStatusNo() {
		return StatusNo;
	}
	public void setStatusNo(int statusNo) {
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

}