package jp.KEN.yamamons.entity;

import java.io.Serializable;

public class Items implements Serializable {
	//t_item(商品情報)のエンティティ
	private String itemNo;
	private String itemName;
	private String itemQuantity;
	private String genreNo;
	private String director;
	private String typeNo;
	private String itemPicture;

	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
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
