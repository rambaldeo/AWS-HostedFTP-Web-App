package planning.webapp;

public class UserAccounts {

	private String username;
	private String password;
	private String firstname;
	private String lastName;
	private String verifyPassword;
	private int id;
	private String description;
	private String saltKey;

	public void UserAccount() {

	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserId() {
		return id;
	}

	public void setUserId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSaltKey() {
		return saltKey;
	}

	public void setSaltKey(String saltKey) {
		this.saltKey = saltKey;
	}
	public String getVerifyPassword() {
		return verifyPassword;
	}
	public void setverifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

}
