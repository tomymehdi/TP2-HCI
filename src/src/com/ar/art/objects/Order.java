package com.ar.art.objects;


public class Order {

	
	int id;
	String created_date;
	String confirmed_date;
	Address address;
	

	public Order(int id, String created_date2, String confirmed_date2) {
		this.id=id;
		this.created_date=created_date2;
		this.confirmed_date=confirmed_date2;
	
	}
	public void addAddress(Address add){
		this.address=add;
	}
	public int getId() {
		return id;
	}
	public String getCreated_date() {
		return created_date;
	}
	public String getConfirmed_date() {
		return confirmed_date;
	}
	public Address getAddress() {
		return address;
	}
	
	

}
