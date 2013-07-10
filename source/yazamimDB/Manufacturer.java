package yazamimDB;

public class Manufacturer {
	
	private int manuNum;
	private String manuName;
	private String address;
	private String contactName;
	private String contactPhone;
	private String contactMail;
	
	public Manufacturer(int manuNum, String manuName, String address,
			String contactName, String contactPhone, String contactMail) {
		super();
		this.manuNum = manuNum;
		this.manuName = manuName;
		this.address = address;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactMail = contactMail;
	}
	
	public Manufacturer(String manuName, String address,
			String contactName, String contactPhone, String contactMail) {
		super();
		this.manuName = manuName;
		this.address = address;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactMail = contactMail;
	}

	public Manufacturer(int manuNum)
	{
		this.manuNum = manuNum;
	}
	
	public Manufacturer(int manuNum, String manuName)
	{
		this.manuNum = manuNum;
		this.manuName = manuName;
	}
	
	public int getManuNum() {
		return manuNum;
	}

	public void setManuNum(int manuNum) {
		this.manuNum = manuNum;
	}

	public String getManuName() {
		return manuName;
	}

	public void setManuName(String manuName) {
		this.manuName = manuName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return "Manufacturer [manuNum=" + manuNum + ", manuName=" + manuName
				+ ", address=" + address + ", contactName=" + contactName
				+ ", contactPhone=" + contactPhone + ", contactMail="
				+ contactMail + "]";
	}
	
	
	
	
}
