package yazamimDB;

import java.sql.Date;

public class Payment {
	private int paymentNum;
	private int schoolNum;
	private int groupNum;
	private int bindingYear;
	private int sponsorNum;
	private Date receiveDate;
	private Date obligationDate;
	private double amount;
	private String paymentContact;
	private String phone;
	private String address;
	private String comments;
	private boolean _destroy; // using KO naming convention to track deletions
	
	public Payment(int paymentNum, int schoolNum, int groupNum, int bindingYear,
			int sponsorNum, Date receiveDate, Date obligationDate,
			double amount, String paymentContact, String phone, String address,
			String comments) {
		super();
		this.paymentNum = paymentNum;
		this.schoolNum = schoolNum;
		this.groupNum = groupNum;
		this.bindingYear = bindingYear;
		this.sponsorNum = sponsorNum;
		this.receiveDate = receiveDate;
		this.obligationDate = obligationDate;
		this.amount = amount;
		this.paymentContact = paymentContact;
		this.phone = phone;
		this.address = address;
		this.comments = comments;
	}

	public Payment(int paymentNum, int schoolNum, int groupNum,
			int bindingYear, Date obligationDate, Date receiveDate, double amount, String comments) {
		super();
		this.paymentNum = paymentNum;
		this.schoolNum = schoolNum;
		this.groupNum = groupNum;
		this.bindingYear = bindingYear;
		this.obligationDate = obligationDate;
		this.receiveDate = receiveDate;
		this.amount = amount;
		this.comments = comments;
	}

	public Payment(int paymentNum, int schoolNum, int groupNum, int bindingYear, int sponsorNum,
			Date receiveDate, Date obligationDate, double amount, String comments) {
		this.paymentNum = paymentNum;
		this.schoolNum = schoolNum;
		this.groupNum = groupNum;
		this.bindingYear = bindingYear;
		this.sponsorNum = sponsorNum;
		this.receiveDate = receiveDate;
		this.obligationDate = obligationDate;
		this.amount = amount;
		this.comments = comments;
	}

	public int getPaymentNum() {
		return paymentNum;
	}

	public void setPaymentNum(int paymentNum) {
		this.paymentNum = paymentNum;
	}

	public int getSchoolNum() {
		return schoolNum;
	}

	public void setSchoolNum(int schoolNum) {
		this.schoolNum = schoolNum;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	public int getBindingYear() {
		return bindingYear;
	}

	public void setBindingYear(int bindingYear) {
		this.bindingYear = bindingYear;
	}

	public int getSponsorNum() {
		return sponsorNum;
	}

	public void setSponsorNum(int sponsorNum) {
		this.sponsorNum = sponsorNum;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Date getObligationDate() {
		return obligationDate;
	}

	public void setObligationDate(Date obligationDate) {
		this.obligationDate = obligationDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentContact() {
		return paymentContact;
	}

	public void setPaymentContact(String paymentContact) {
		this.paymentContact = paymentContact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean is_destroy() {
		return _destroy;
	}

	public void set_destroy(boolean _destroy) {
		this._destroy = _destroy;
	}

	@Override
	public String toString() {
		return "Payment [paymentNum=" + paymentNum + ", schoolNum=" + schoolNum
				+ ", bindingYear=" + bindingYear + ", sponsorNum=" + sponsorNum
				+ ", receiveDate=" + receiveDate + ", obligationDate="
				+ obligationDate + ", amount=" + amount + ", paymentContact="
				+ paymentContact + ", phone=" + phone + ", address=" + address
				+ ", comments=" + comments + "]";
	}
	
	
	
	
}
