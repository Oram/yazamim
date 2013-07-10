package yazamimDB;

public class UserRole {
	
	private String username;
	private String rolename;
	
	public UserRole(String username, String rolename) {
		super();
		this.username = username;
		this.rolename = rolename;
	}

	@Override
	public String toString() {
		return "UserRole [username=" + username + ", rolename=" + rolename
				+ "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	
	
}
