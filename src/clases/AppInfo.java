package clases;

import java.util.ArrayList;
import java.util.List;

public class AppInfo {
	
	private Lenguage lenguage;
	private Currency currency;
	private List<Category> categorys;
	private User user;
	
	public AppInfo(Lenguage lenguage, Currency currency, List<Category> categorys, User user) {
		this.lenguage = lenguage;
		this.currency = currency;
		this.categorys = categorys;
		this.user = user;
	}
	
	
	public List<Category> getCategorys() {
		return categorys;
	}
	
	public int countCategorys(){
		return categorys.size();
	}
	
	public List<SubCategory> getSubCategorys(){
		List<SubCategory> resp = new ArrayList<SubCategory>();
		for(Category c: categorys){
			for(SubCategory s: c.getSubCategorys()){
				resp.add(s);
			}
		}
		return resp;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public Lenguage getLenguage() {
		return lenguage;
	}
	
	public User getUser() {
		return user;
	}
	
}