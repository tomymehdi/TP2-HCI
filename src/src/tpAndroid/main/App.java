package tpAndroid.main;

import java.util.List;

public class App {

	private List<Item> search=null;
	private List<Order> orders=null;
	private List<Address> addreses=null;
	private List<Category> categories=null;
	private List<Item> items;
	private User user;
	private String token=null;

	public App() {
		// TODO Auto-generated constructor stub
	}

	public List<Item> getItems(){
		return items;
	}
	public List<Item> getSearch() {
		return search;
	}




	public User getUser() {
		return user;
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


	public void setSearch(List<Item> items2) {
		this.search = items2;
	}

	public void setCategories(List<Category> categories) {

		this.categories = categories;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setItems(List<Item> items) {

			this.items=items;
	}

	public void setUser(User user) {
		this.user=user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token2) {
			this.token=token2;		
	}

}
