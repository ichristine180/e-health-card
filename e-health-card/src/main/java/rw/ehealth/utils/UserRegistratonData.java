package rw.ehealth.utils;

public class UserRegistratonData {

	private String password;
	private String username;
	private String roleName;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
