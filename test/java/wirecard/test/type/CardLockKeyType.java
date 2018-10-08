package wirecard.test.type;

/**
 * Class create to map the card lock api return
 * @author wilson
 *
 */
public enum CardLockKeyType {
	
	/** Card lock with success */
	OK("OK - The card was successfully locked"),
	
	/** Missing mandatory parameter */
	MISSING_PARAMETER("Bad Request, missing card PAN"),
	
	/** Lock ref id already used */
	TRANSACTION_ALREADY_USED ("Transaction with the same{lock-ref-id} already exists on the server."),
	
	/** Card already locked */
	CARD_ALREADY_LOCKED("Card is already in LOCKED state."),
	
	/** Card not found s*/
	NO_CARD_FOUND("No card found for the given PAN.");
	
	/** The registration  descripion*/
	private final String description;
	
	/** Sets the status descripion*/
	CardLockKeyType(String description){
		
		this.description = description;
	}
	
	/** Gets the status descripion*/
	public String getDescription() {
		return description;
	}
}
