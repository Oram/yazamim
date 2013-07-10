package yazamimDB;

public class Company {

	private int companyNum;
	private String companyName;

	public Company(int companyNum, String companyName) {
		this.companyNum = companyNum;
		this.companyName = companyName;
	}

	public int getCompanyNum() {
		return companyNum;
	}

	public void setCompanyNum(int companyNum) {
		this.companyNum = companyNum;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "Company [companyNum=" + companyNum + ", companyName="
				+ companyName + "]";
	}

}
