package yazamimDB;

public class AcademicInstitution {

	private int institutionNum;
	private String institutionName;

	public AcademicInstitution(int institutionNum, String institutionName) {
		this.institutionNum = institutionNum;
		this.institutionName = institutionName;
	}

	public int getInstitutionNum() {
		return institutionNum;
	}

	public void setInstitutionNum(int institutionNum) {
		this.institutionNum = institutionNum;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	@Override
	public String toString() {
		return "AcademicInstitution [institutionNum=" + institutionNum
				+ ", institutionName=" + institutionName + "]";
	}
}
