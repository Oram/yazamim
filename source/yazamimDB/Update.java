package yazamimDB;

import java.sql.Date;

public class Update {
	private int updateNum;

	private Date up_date;
	private String description;
	private String link;
	private Employee employee;
	private boolean _destroy; // using KO naming convention to track deletions 
	
	public Update(int updateNum, Date up_date, String description, String link, Employee employee) {
		super();
		this.updateNum = updateNum;
		this.up_date = up_date;
		this.description = description;
		this.link = link;
		this.employee = employee;
	}

	public int getUpdateNum() {
		return updateNum;
	}
	
	public void setUpdateNum(int updateNum) {
		this.updateNum = updateNum;
	}
	
	public Date getUp_date() {
		return up_date;
	}

	public void setUp_date(Date update) {
		this.up_date = update;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public boolean is_destroy() {
		return _destroy;
	}

	public void set_destroy(boolean _destroy) {
		this._destroy = _destroy;
	}
	
	@Override
	public String toString() {
		return "Update [update=" + up_date + ", description=" + description
				+ ", link=" + link + ", ]";
	}
	
	
}
