package yazamimDB;

public class School {

	private int schoolNum;
	private String schoolName;
	private City city;
	private String address;
	private String principleName;
	private String phone;
	private String fax;
	private String email;
	private String contactName;
	private String contactPhone;
	private String contactMail;
	private int netId;
	private int typeId;
	private String comments;
	private Binding binding;

	public School(int schoolNum, String schoolName, City city, String address,
			String principleName, String phone, String fax, String email,
			String contactName, String contactPhone, String contactMail,
			int netId, int typeId, String comments) {
		this.schoolNum = schoolNum;
		this.schoolName = schoolName;
		this.city = city;
		this.address = address;
		this.principleName = principleName;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactMail = contactMail;
		this.netId = netId;
		this.typeId = typeId;
		this.comments = comments;
	}

	public School(int schoolNum, String schoolName, City city, String address,
			String principleName, String phone, String fax, String email,
			String contactName, String contactPhone, String contactMail,
			int netId, int typeId, String comments, Binding binding) {
		this(schoolNum, schoolName, city, address, principleName, phone, fax,
				email, contactName, contactPhone, contactMail, netId, typeId,
				comments);
		this.binding = binding;
	}

	public School(int schoolNum, String schoolName, City city) {
		this.schoolNum = schoolNum;
		this.schoolName = schoolName;
		this.city = city;
	}

	public School(int schoolNum, String schoolName) {
		this.schoolNum = schoolNum;
		this.schoolName = schoolName;
	}

	public School(int schoolNum) {
		this.schoolNum = schoolNum;
	}

	public int getSchoolNum() {
		return schoolNum;
	}

	public void setSchoolNum(int schoolNum) {
		this.schoolNum = schoolNum;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrincipleName() {
		return principleName;
	}

	public void setPrincipleName(String principleName) {
		this.principleName = principleName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getNetId() {
		return netId;
	}

	public void setNetId(int netId) {
		this.netId = netId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Binding getBinding() {
		return binding;
	}

	public void setBinding(Binding binding) {
		this.binding = binding;
	}

	@Override
	public String toString() {
		return "School [schoolNum=" + schoolNum + ", schoolName=" + schoolName
				+ ", city=" + city + ", address=" + address
				+ ", principleName=" + principleName + ", phone=" + phone
				+ ", fax=" + fax + ", email=" + email + ", contactName="
				+ contactName + ", contactPhone=" + contactPhone
				+ ", contactMail=" + contactMail + ", netId=" + netId
				+ ", typeId=" + typeId + ", comments=" + getComments()
				+ ", binding=" + binding + "]";
	}

}
