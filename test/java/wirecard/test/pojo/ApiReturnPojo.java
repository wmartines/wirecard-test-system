package wirecard.test.pojo;

/**
 * Class created to map the api return 
 * @author wilson
 *
 */
public class ApiReturnPojo {
	
	/**
	 *  The error key response from api request
	 */
	private String errorKey;
	
	/**
	 *  The description returned from api request
	 */
	private String description;
	
	/**
	 *  Gets the description
	 * @return String description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets description
	 * @param String description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
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
}
