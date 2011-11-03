package com.ar.art.objects;

public class Product {
	int id;
	String name;
	float price;
	
	public Product(int id, String name, float price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public float getPrice(){
		return price;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public int hashCode() {
		return (int) (Math.pow(2, id) + Math.pow(3, id+1));
	}

}
