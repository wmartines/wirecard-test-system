package wirecard.test.pojo;

/**
 * Class created to map the api return 
 * @author wilson
 *
 */
public class ApiReturnPojo {
	
	/**
	 * The error code
	 */
	private Integer errorCode;
	
	/**
	 *  The error key response from api request
	 */
	private String errorKey;
	
	/**
	 *  The description returned from api request
	 */
	private String errorMessage;
	
	/**
	 *  Gets the error key
	 * @return String errorKey
	 */
	public String getErrorKey() {
		return errorKey;
	}
	
	/**
	 * Sets the error key
	 * @param String errorKey
	 */
	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
