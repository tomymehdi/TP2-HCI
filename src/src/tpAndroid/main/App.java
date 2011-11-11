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
	private String username=null;

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
	
	public Item getItemById(int id){
		for(Item item : items){
			if(item.getId() == id){
				return item;
			}
		}
		return null;
		//TODO:Lanzar excepcion.
	}

	public Category getCategory(int id) {
//		List<Category> cats = getCategories();
		for(Category cat : getCategories()){
			if(cat.getId() == id){
				return cat;
			}
		}
		return null;
	}

	public void setUsername(String user2) {
		this.username=user2;
	}
	public String getUsername(){
		return username;
	}
}
