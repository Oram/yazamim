package yazamimDB;

public class GroupType {
	private int typeNum;
	private String typeName;
	private String contactName;
	private String contactPhone;
	private String contactMail;



	public GroupType(int typeNum, String typeName,
			String contactName, String contactPhone, String contactMail) {
		this.typeNum = typeNum;
		this.typeName = typeName;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactMail = contactMail;
	}

	public GroupType(int typeNum, String typeName) {
		this.typeNum = typeNum;
		this.typeName = typeName;
	}

	public int getTypeNum() {
		return typeNum;
	}

	public void setTypeNum(int typeNum) {
		this.typeNum = typeNum;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	@Override
	public String toString() {
		return "GroupType [typeNum=" + typeNum + ", typeName="
				+ typeName + ", contactName=" + contactName
				+ ", contactPhone=" + contactPhone + "]";
	}

}
