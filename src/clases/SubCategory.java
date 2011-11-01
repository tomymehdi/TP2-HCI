package clases;

import java.util.List;

public class SubCategory {
	
	private List<Item> items;
	private String subCategoryName;
	
	public SubCategory(List<Item> items, String subCategoryName) {
		this.items = items;
		this.subCategoryName = subCategoryName;
	}
	
	public int countItems(){
		return items.size();
	}
	
	public String getSubCategoryName() {
		return subCategoryName;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
}
