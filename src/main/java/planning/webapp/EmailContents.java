package planning.webapp;

public class EmailContents {
	
	private String firstName;
	private String lastName;
	private String emailSubject;
	private String emailBody;
	private String sender;
	private String country;
	private String companyName;
	
	public void EmailContent() {
		
	}
	public String getFirstName() {
		return firstName;
	}
	public void setUserName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String emailSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender=sender;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String country() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String setSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject= emailSubject;
	}
	public String emailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody=emailBody;
	}
	
}
