package yazamimDB;

public class Sponsor {
	private int sponsorNum;
	private String sponsorName;
	private String contactName;
	private String contactPhone;
	private String contactMail;
	private String description;
	
	public Sponsor(int sponsorNum, String sponsorName, String contactName,
			String contactPhone, String contactMail, String description) {
		super();
		this.sponsorNum = sponsorNum;
		this.sponsorName = sponsorName;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactMail = contactMail;
		this.description = description;
	}
	
	public Sponsor(String sponsorName, String contactName,
			String contactPhone, String contactMail, String description) {
		super();
		this.sponsorName = sponsorName;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactMail = contactMail;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSponsorNum() {
		return sponsorNum;
	}

	public void setSponsorNum(int sponsorNum) {
		this.sponsorNum = sponsorNum;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
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
		return "Sponsor [sponsorNum=" + sponsorNum + ", sponsorName="
				+ sponsorName + ", contactName=" + contactName
				+ ", contactPhone=" + contactPhone + ", contactMail="
				+ contactMail + ", description=" + description + "]";
	}
	
	
	
}
