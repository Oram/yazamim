package yazamimDB;

import java.sql.Date;

public class Product {
	private int productNum;
	private Group group;
	private String productName;
	private String description;
	private String imageLink;
	private Manufacturer manufacturer;
	private ProductCategory category;
	private boolean sentProductForm;
	private Date productFormSentDate;
	private boolean gotProductForm;
	private Date productFormGotDate;
	
	public Product(int productNum, Group group, String productName,
			String description, String imageLink, Manufacturer manufacturer, ProductCategory category,
			boolean sentProductForm, Date productFormSentDate, 
			boolean gotProductForm, Date productFormGotDate) {
		super();
		this.productNum = productNum;
		this.group = group;
		this.productName = productName;
		this.description = description;
		this.imageLink = imageLink;
		this.manufacturer = manufacturer;
		this.category = category;
		this.sentProductForm = sentProductForm;
		this.productFormSentDate = productFormSentDate;
		this.gotProductForm = gotProductForm;
		this.productFormGotDate = productFormGotDate;
	}
	
	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public void setProductFormSentDate(Date productFormSentDate) {
		this.productFormSentDate = productFormSentDate;
	}

	public void setProductFormGotDate(Date productFormGotDate) {
		this.productFormGotDate = productFormGotDate;
	}

	public Product(int productNum, Group group, String productName, Manufacturer manufacturer , ProductCategory category)
	{
		this.productNum = productNum;
		this.group = group;
		this.productName = productName;
		this.manufacturer = manufacturer;
		this.category = category;
	}
	
	
	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public boolean isSentProductForm() {
		return sentProductForm;
	}
	
	public void setSentProductForm(boolean sentProductForm) {
		this.sentProductForm = sentProductForm;
	}

	public Date getProductFormSentDate() {
		return productFormSentDate;
	}



	public boolean isGotProductForm() {
		return gotProductForm;
	}

	public void setGotProductForm(boolean gotProductForm) {
		this.gotProductForm = gotProductForm;
	}

	public Date getProductFormGotDate() {
		return productFormGotDate;
	}


	@Override
	public String toString() {
		return "Product [productNum=" + productNum + ", group=" + group
				+ ", productName=" + productName + ", description="
				+ description + ", imageLink=" + imageLink + ", manufacturer="
				+ manufacturer + "]";
	}
	
	
	
	
}
