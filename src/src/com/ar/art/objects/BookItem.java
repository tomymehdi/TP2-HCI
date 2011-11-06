package com.ar.art.objects;

import java.sql.Date;

public class BookItem extends Item {
	

	private String authors;
	private String publisher;
	private Date pub_date;
	private String ISBN_10;
	private String ISBN_13;
	private String language;

	public BookItem(int id, int sales, String name, int categ, int scateg, double price2, String imgUrl) {
		super(id, sales, name, categ, scateg, price2, imgUrl);
	}
	
	
	@Override  //FOR TESTING ONLY, CHANGE TO A GOOD ONE
	public String getInfo() {
		return "authors: "+authors+"Publisher :"+publisher;
	}
	
	public void addInfo(String authors, String publisher, Date pubdate, String iSBN_102, String iSBN_132, String lang){
		this.authors=authors;
		this.publisher=publisher;
		this.pub_date=pubdate;
		this.ISBN_10=iSBN_102;
		this.ISBN_13=iSBN_132;
		this.language=lang;
	}

	public String getAuthors() {
		return authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public Date getPub_date() {
		return pub_date;
	}

	public String getISBN_10() {
		return ISBN_10;
	}

	public String getISBN_13() {
		return ISBN_13;
	}

	public String getLanguage() {
		return language;
	}
	
	
}
