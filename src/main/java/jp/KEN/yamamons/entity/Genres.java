package jp.KEN.yamamons.entity;

import java.io.Serializable;

public class Genres implements Serializable {
	private String genreNo;
	private String genre;
	
	public String getGenreNo() {
		return genreNo;
	}
	public void setGenreNo(String genreNo) {
		this.genreNo = genreNo;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
}
