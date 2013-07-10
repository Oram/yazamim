package yazamimDB;

public class ProductCategory {
	private int catId;
	private String catName;
	
	public ProductCategory(int catId, String catName) {
		super();
		this.catId = catId;
		this.catName = catName;
	}
	
	public ProductCategory(int catId) {
		super();
		this.catId = catId;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	
	
}
