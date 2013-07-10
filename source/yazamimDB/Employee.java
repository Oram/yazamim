package yazamimDB;

public class Employee {

	private String username;
	private String name;
	private String phone;
	private String email;
	private String password;
	private Area area;
	private String role;
	private boolean _destroy; // using KO naming convention to track deletions 

	public Employee(String username, String name, String phone, String email,
			String password, Area area, String role) {
		this.username = username;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.area = area;
		this.role = role;
	}

	public Employee(String username, String name, String phone, String email,
			String password, Area area) {
		this.username = username;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.area = area;
	}
	
	public Employee(String username, String name, Area area) {
		this.username = username;
		this.name = name;
		this.area = area;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean is_destroy() {
		return _destroy;
	}

	public void set_destroy(boolean _destroy) {
		this._destroy = _destroy;
	}

	@Override
	public String toString() {
		return "Employee [username=" + username + ", name=" + name + ", phone="
				+ phone + ", email=" + email + ", password=" + password
				+ ", area=" + area + ", role=" + role + "]";
	}

}
