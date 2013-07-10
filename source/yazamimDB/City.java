package yazamimDB;

public class City {
	private int cityNum;
	private String cityName;
	private Area area;

	public City(int cityNum, String cityName, Area area) {
		this.cityNum = cityNum;
		this.cityName = cityName;
		this.area = area;
	}

	public City(int cityNum, String cityName) {
		this(cityNum, cityName, null);
	}

	public int getCityNum() {
		return cityNum;
	}

	public void setCityNum(int cityNum) {
		this.cityNum = cityNum;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "City [cityNum=" + cityNum + ", cityName=" + cityName
				+ ", area=" + area + "]";
	}

}
