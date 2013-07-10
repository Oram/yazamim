package yazamimDB;

import java.sql.Date;

public class BankDetails {

	private Date openAccountDate;
	private String branchNum;
	private String branchName;
	private String accountNum;

	public BankDetails(Date openAccountDate, String branchNum, String branchName,
			String accountNum) {
		this.openAccountDate = openAccountDate;
		this.branchNum = branchNum;
		this.branchName = branchName;
		this.accountNum = accountNum;
	}

	public Date getOpenAccountDate() {
		return openAccountDate;
	}

	public void setOpenAccountDate(Date openAccountDate) {
		this.openAccountDate = openAccountDate;
	}

	public String getBranchNum() {
		return branchNum;
	}

	public void setBranchNum(String branchNum) {
		this.branchNum = branchNum;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	@Override
	public String toString() {
		return "BankDetails [openAccountDate=" + openAccountDate
				+ ", branchNum=" + branchNum + ", branchName=" + branchName
				+ ", accountNum=" + accountNum + "]";
	}

}
