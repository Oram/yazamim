package yazamimDB;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Binding {

	enum MarketingStatus {

		NONE('N'), LOW('L'), MEDIUM('M'), HIGH('H'), SUCCESS('S');

		char statusChar;

		private MarketingStatus(char statusChar) {
			this.statusChar = statusChar;
		}

		public static MarketingStatus fromString(String status) {
			if (status != null) {
				for (MarketingStatus value : MarketingStatus.values()) {
					if (status.equalsIgnoreCase(value.toString())) {
						return value;
					}
				}
			} else {
				return MarketingStatus.NONE;
			}
			throw new IllegalArgumentException("No constant with statusChar "
					+ status + " found");
		}

		@Override
		public String toString() {
			return String.valueOf(this.statusChar);
		}
	}

	private int bindingYear;
	private MarketingStatus marketingStatus;

	 private boolean gotContract;
	 private String gotContractDate;
	 private boolean gotRegistration;
	 private String registrationDate;
	 private boolean sentMaterials;
	 private String materialsSentDate;
	 private boolean gotMaterials;
	 private String materialsGotDate;
	 private String gotMaterialsContact;
	 private String gotMaterialsPhone;
	 private School school;
	 
	 public Binding(int bindingYear,School school, boolean gotContract, Date gotContractDate,
		 		boolean gotRegistration,
				Date registrationDate, boolean sentMaterials,
				Date materialsSentDate,
		 		boolean gotMaterials, Date materialsGotDate, String gotMaterialsContact, String gotMaterialsPhone) {
			super();
			this.bindingYear = bindingYear;
			this.school = school;
			this.gotContract = gotContract;
			this.gotContractDate = getFormattedDate(gotContractDate);
			this.gotRegistration = gotRegistration;
			this.registrationDate = getFormattedDate(registrationDate);
			this.sentMaterials = sentMaterials;
			this.materialsSentDate = getFormattedDate(materialsGotDate);
			this.gotMaterials = gotMaterials;
			this.materialsGotDate = getFormattedDate(materialsGotDate);
			this.gotMaterialsContact = gotMaterialsContact;
			this.gotMaterialsPhone = gotMaterialsPhone;
		}
	 
	public boolean isGotContract() {
		return gotContract;
	}

	public void setGotContract(boolean gotContract) {
		this.gotContract = gotContract;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = getFormattedDate(registrationDate);
	}

	public boolean isSentMaterials() {
		return sentMaterials;
	}

	public void setSentMaterials(boolean sentMaterials) {
		this.sentMaterials = sentMaterials;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getMaterialsSentDate() {
		return materialsSentDate;
	}

	public void setMaterialsSentDate(Date materialsSentDate) {
		this.materialsSentDate = getFormattedDate(materialsSentDate);
	}

	public boolean isGotMaterials() {
		return gotMaterials;
	}

	public void setGotMaterials(boolean isGotMaterials) {
		this.gotMaterials = isGotMaterials;
	}

	public String getMaterialsGotDate() {
		return materialsGotDate;
	}

	public void setMaterialsGotDate(Date materialsGotDate) {
		this.materialsGotDate = getFormattedDate(materialsGotDate);
	}

	public String getGotMaterialsContact() {
		return gotMaterialsContact;
	}

	public void setGotMaterialsContact(String gotMaterialsContact) {
		this.gotMaterialsContact = gotMaterialsContact;
	}

	public String getGotMaterialsPhone() {
		return gotMaterialsPhone;
	}

	public void setGotMaterialsPhone(String gotMaterialsPhone) {
		this.gotMaterialsPhone = gotMaterialsPhone;
	}

	public String getGotContractDate() {
		return gotContractDate;
	}

	public void setGotContractDate(Date gotContractDate) {
		this.gotContractDate = getFormattedDate(gotContractDate);
	}

	public boolean isGotRegistration() {
		return gotRegistration;
	}

	public void setGotRegistration(boolean gotRegistration) {
		this.gotRegistration = gotRegistration;
	}

	public String getFormattedDate(Date date) {
		
		if (date == null) {
			return null;
		}
		  
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
		
	}

	public Date setFormattedDate(String dateString)
	{
		java.sql.Date sqlDate = null;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			java.util.Date utilDate = formatter.parse(dateString);
			sqlDate = new java.sql.Date(utilDate.getTime());
		}catch(Exception e){
			e.printStackTrace();	
		}	

		return sqlDate;
	}

	public Binding(int bindingYear, MarketingStatus marketingStatus) {
		this.bindingYear = bindingYear;
		this.marketingStatus = marketingStatus;
	}

	public Binding(int bindingYear, String marketingStatus) {
		this(bindingYear, MarketingStatus.fromString(marketingStatus));
	}

	public int getBindingYear() {
		return bindingYear;
	}

	public void setBindingYear(int bindingYear) {
		this.bindingYear = bindingYear;
	}

	public MarketingStatus getMarketingStatus() {
		return marketingStatus;
	}

	public void setMarketingStatus(MarketingStatus marketingStatus) {
		this.marketingStatus = marketingStatus;
	}

	@Override
	public String toString() {
		return "Binding [bindingYear=" + bindingYear + ", marketingStatus="
				+ marketingStatus + "]";
	}

}
