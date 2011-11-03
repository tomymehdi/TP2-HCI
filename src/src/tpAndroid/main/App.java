package tpAndroid.main;

import java.util.LinkedList;
import java.util.List;

public class App {

	private LinkedList<Item> itemsList;
	private LinkedList<Item> search;
	private String token;
	private String username;
	private List<Order> orders;
	private List<Address> addreses;
	private List<Category> categories;

	public App() {
		// TODO Auto-generated constructor stub
	}

	public List<Item> getItemsList() {
		return itemsList;
	}

	public List<Item> getSearch() {
		return search;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Address> getAddreses() {
		return addreses;
	}

	public void setAddreses(List<Address> addreses) {
		this.addreses = addreses;
	}

	public void setItemsList(LinkedList<Item> itemsList) {
		this.itemsList = itemsList;
	}

	public void setSearch(LinkedList<Item> search) {
		this.search = search;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Category> getCategories() {
		return categories;
	}

}
