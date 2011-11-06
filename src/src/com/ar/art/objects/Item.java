package com.ar.art.objects;


public abstract  class Item {

	private int id;
	private int sales_rank;
	private int categ_id;
	private String name;
	private int scateg_id;
	private double price;
	private String imgUrl;


	public Item(int id, int sales, String name, int categ, int scateg,
			double price2, String imgUrl) {

		this.id=id;
		this.sales_rank=sales;
		this.name=name;
		this.categ_id=categ;
		this.scateg_id=scateg;
		this.price=price2;
		this.imgUrl=imgUrl;
	
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getSales_rank() {
		return sales_rank;
	}


	public void setSales_rank(int sales_rank) {
		this.sales_rank = sales_rank;
	}


	public int getCateg_id() {
		return categ_id;
	}


	public void setCateg_id(int categ_id) {
		this.categ_id = categ_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getScateg_id() {
		return scateg_id;
	}


	public void setScateg_id(int scateg_id) {
		this.scateg_id = scateg_id;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public abstract String getInfo();

	
	
}
