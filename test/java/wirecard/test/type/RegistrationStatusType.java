package wirecard.test.type;

/**
 * Class created to map the registration status type api return
 * @author wilson
 *
 */
public enum RegistrationStatusType {
	
	/**Registration with success*/
	ACTIVE("ACTIVE"),
	
	/**Waiting additional information*/
	IN_REGISTRATION("IN_REGISTRATION");
	
	/** The registration descripion*/
	private final String description;
	
	RegistrationStatusType(String description){
		
		this.description = description;
	}
	
	/** Gets the registration descripion*/
	public String getDescription() {
		return description;
	}
}
