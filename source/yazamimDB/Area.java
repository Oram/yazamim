package yazamimDB;

public class Area {

	private int areaNum;
	private String areaName;

	public Area(int areaNum, String areaName) {
		this.areaNum = areaNum;
		this.areaName = areaName;
	}

	public int getAreaNum() {
		return areaNum;
	}

	public void setAreaNum(int areaNum) {
		this.areaNum = areaNum;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String toString() {
		return "Area [areaNum=" + areaNum + ", areaName=" + areaName + "]";
	}

	
}
