package clases;

import java.util.List;

public class User {
	private String password;
	private String username;
	private List<Order> orders;
	
	public User(String password, String username, List<Order> orders) {
		this.password = password;
		this.username = username;
		this.orders = orders;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
}
