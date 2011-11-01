package clases;

import java.util.Date;

public class Order {
	private Date creationDate;
	private Date arrivalDate;
	private Adress shippingAdress;

	public Order(Date creationDate, Date arrivalDate, Adress shippingAdress) {
		this.creationDate = creationDate;
		this.arrivalDate = arrivalDate;
		this.shippingAdress = shippingAdress;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public Adress getShippingAdress() {
		return shippingAdress;
	}
}
