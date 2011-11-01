package clases;

import java.util.List;

public class Category {
	
	private List<SubCategory> subCategorys;
	private List<Item> items;
	private String categoryName;
	
	public Category(List<SubCategory> subCategorys, List<Item> items, String categoryName) {
		this.subCategorys = subCategorys;
		this.items = items;
		this.categoryName = categoryName;
	}
	
	public List<SubCategory> getSubCategorys() {
		return subCategorys;
	}
	
	public int countSubCategorys(){
		return subCategorys.size();
	}
	
	public int countItems(){
		return items.size();
	}
	
	public String getCategoryName() {
		return categoryName;
	}

}
