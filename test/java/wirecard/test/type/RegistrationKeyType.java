package wirecard.test.type;

/**
 * Class created to map the the registration api return
 * @author wilson
 *
 */
public enum RegistrationKeyType {
	
	/** Registration with success*/
	MISSING_MANDATORY_FIELD("One or several mandatory fields are missing"),
	
	/** User with this mobile phone number already exists*/
	USER_WITH_THIS_PHONE_NUMBER_EXISTS("User with this mobile phone number already exists"),
	
	/** User with this login name already exists*/
	USER_WITH_THIS_LOGIN_NAME_EXISTS ("User with this login name already exists"),
	
	/** User with this email address already exists*/
	USER_WITH_THIS_EMAIL_EXISTS("User with this email address already exists");
	
	/** The registration status descripion*/
	private final String errorMessage;
	
	RegistrationKeyType(String errorMessage){
		
		this.errorMessage = errorMessage;
	}
	
	/** Gets the registration status descripion*/
	public String getErrorMessage() {
		return errorMessage;
	}
}
