package type;

public enum RegistrationStatusType {
	
	/**Registration with success*/
	ACTIVE("ACTIVE"),
	
	/**Waiting additional information*/
	IN_REGISTRATION("IN_REGISTRATION");
	
	/** The registration status descripion*/
	private final String description;
	
	RegistrationStatusType(String description){
		
		this.description = description;
	}
	
	/** Gets the registration status descripion*/
	public String getDescription() {
		return description;
	}
}
