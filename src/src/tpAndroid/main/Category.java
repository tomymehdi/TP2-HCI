package tpAndroid.main;

import java.util.List;


public class Category {
	
	private String name;
	private String code;
	int id;
	private List<SubCategory> subCategories;
	

	
	public Category(int id, String code, String name2) {

		this.id=id;
		this.code=code;
		this.name=name2;
	}

	public String toString(){
		return name;
	}

	public int getId() {
		return id;
	}

	public void addSubCategory(List<SubCategory> subcategories) {
		this.subCategories=subcategories;
		
	}
	public List<SubCategory> getSubCategories(){
		return subCategories;
	}

}
