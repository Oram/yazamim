package yazamimDB;

import java.sql.Date;

public class GroupStatus {
	private int statusNum;
	private int schoolNum;
	private int groupNum;
	private Date statusDate;
	private String details;
	private boolean _destroy; // using KO naming convention to track deletions
	
	
	public boolean is_destroy() {
		return _destroy;
	}

	public void set_destroy(boolean _destroy) {
		this._destroy = _destroy;
	}

	public GroupStatus(int statusNum, int schoolNum, int groupNum, Date statusDate, String details) {
		super();
		this.statusNum = statusNum; 
		this.schoolNum = schoolNum;
		this.groupNum = groupNum;
		this.statusDate = statusDate;
		this.details = details;
	}
	
	public int getStatusNum() {
		return statusNum;
	}

	public void setStatusNum(int statusNum) {
		this.statusNum = statusNum;
	}

	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	
	public int getSchoolNum() {
		return schoolNum;
	}
	public void setSchoolNum(int schoolNum) {
		this.schoolNum = schoolNum;
	} 
	
	public Date getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
