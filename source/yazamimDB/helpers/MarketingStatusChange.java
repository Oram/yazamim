package yazamimDB.helpers;

public class MarketingStatusChange {

	int schoolNum;
	String oldStatus;
	String newStatus;

	public MarketingStatusChange() {
		super();
	}

	public MarketingStatusChange(int schoolNum, int bindingNum,
			String oldStatus, String newStatus) {
		this.schoolNum = schoolNum;
		this.oldStatus = oldStatus;
		this.newStatus = newStatus;
	}

	public int getSchoolNum() {
		return schoolNum;
	}

	public void setSchoolNum(int schoolNum) {
		this.schoolNum = schoolNum;
	}

	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	public String getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}

	@Override
	public String toString() {
		return "MarketingStatusChange [schoolNum=" + schoolNum + ", oldStatus="
				+ oldStatus + ", newStatus=" + newStatus + "]";
	}

}
