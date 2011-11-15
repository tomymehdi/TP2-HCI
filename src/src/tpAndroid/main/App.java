package tpAndroid.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class App {

	private List<Item> search=null;
	private List<Order> orders=null;
	private List<Address> addreses=null;
	private List<Category> categories=null;
	private List<Item> items;
	private User user;
	private String token=null;
	private String username=null;
	private int language;


	public App() {
		// TODO Auto-generated constructor stub
	}

	public List<Item> getItems(){
		return items;
	}
	public List<Item> getSearch() {
		return search;
	}
	public void setLanguageId(int lang){
		this.language=lang;
	}
	public int getLanguageId(){
		return language;
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
	
	


	public List<? extends Map<String, ?>> getCategoriesAsMap() {
	
		String[] categoryFields = { "name" };
		
		List<Category> categories = this.categories;
		List<Map<String, String>> transformedCategories = new ArrayList<Map<String, String>>();
		for (Category c : categories) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(categoryFields[0], c.getName());
			transformedCategories.add(map);
		}
		return transformedCategories;
	}


	
	public String[] getCategoriesNames() {
		
		String[] categNames = new String[getCategories().size()];
		
		for(int i=0; i< this.categories.size();i++){
			categNames[i]=this.categories.get(i).getName();
			
		}
		return categNames;
	}

	public String[] getSubCategoriesNames(int CategId) {
		
		String[] SubcategNames = new String[this.getCategory(CategId).getSubCategories().size()];
		
		Category cat= this.getCategory(CategId);
		
		for(int i=0; i< cat.getSubCategories().size();i++){
	
			SubcategNames[i]=cat.getSubCategories().get(i).getName();
			
		}
		return SubcategNames;
	}

	public SubCategory getSubcategory(int scateg_id) {
		for(Category cat : categories){
			for(SubCategory sub : cat.getSubCategories()){
				if(sub.getId() == scateg_id){
					return sub;
				}
			}
		}
		return null;
	}


}
