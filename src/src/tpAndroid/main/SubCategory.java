package tpAndroid.main;


public class SubCategory {

	private int id;
	private String code;
	private String name;
	private int categ;

	public SubCategory(int id, String code, String name, int categ) {
		this.categ=categ;
		this.code=code;
		this.id=id;
		this.name=name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCateg() {
		return categ;
	}

	public void setCateg(int categ) {
		this.categ = categ;
	}
	

}
