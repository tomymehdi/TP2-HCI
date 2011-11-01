package clases;

import java.awt.Image;

public class Item {
	private Image image;
	private String name;
	private Double price;
	private String description;
	private int ID;
	
	public Item(Image image, String name, Double price, String description) {
		this.image = image;
		this.name = name;
		this.price = price;
		this.description = description;
	}
	
	public Image getImage() {
		return image;
	}
	
	public String getName() {
		return name;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
}
