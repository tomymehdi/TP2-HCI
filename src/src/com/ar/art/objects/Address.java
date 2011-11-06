package com.ar.art.objects;

import org.w3c.dom.Element;

public class Address {

	private String full_name;
	private String address_line_1;
	private String address_line_2;
	private String city;
	private String zip_code;
	private String phone_number;

	public Address(String full_name,String address_line_1,String address_line_2, String city,String zip_code,String phone_number){
		this.full_name=full_name;
		this.address_line_1=address_line_1;
		this.address_line_2=address_line_2;
		this.city=city;
		this.phone_number=phone_number;
		this.zip_code=zip_code;
	}

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public String getFull_name() {
		return full_name;
	}

	public String getAddress_line_1() {
		return address_line_1;
	}

	public String getAddress_line_2() {
		return address_line_2;
	}

	public String getCity() {
		return city;
	}

	public String getZip_code() {
		return zip_code;
	}

	public String getPhone_number() {
		return phone_number;
	}

	
	
}
