package clases;

public class Adress {
	private String direction;
	private String floor;
	private String city;
	private String country;

	public Adress(String direction, String floor, String city, String country) {
		this.direction = direction;
		this.floor = floor;
		this.city = city;
		this.country = country;
	}

	public String getDirection() {
		return direction;
	}

	public String getFloor() {
		return floor;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

}
