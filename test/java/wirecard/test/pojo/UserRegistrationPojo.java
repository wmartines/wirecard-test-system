package wirecard.test.pojo;

public class UserRegistrationPojo {
	
	/**
	 * Login name
	 */
	private String loginName;
	
	/**
	 * status
	 */
	private String status;
	
	/**
	 * pan card number
	 */
	private String pan;
	
	/**
	 * Gets the login name
	 * @return String loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	
	/**
	 * Sets the login name
	 * @param String loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	/**
	 * Gets the status
	 * @return String status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets sets the status
	 * @param String status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the pan card number
	 * @return
	 */
	public String getPan() {
		return pan;
	}
	
	/**
	 * Sets the pan card number
	 * @param String pan card number
	 */
	public void setPan(String pan) {
		this.pan = pan;
	}
}
