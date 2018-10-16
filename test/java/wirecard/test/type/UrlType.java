package wirecard.test.type;

/**
 * Class created to map the url addres
 * @author wilson
 *
 */
public enum UrlType {
	
	/** Registration URL*/
	REGISTRATION_URL("http://localhost:8080/user/save"),
	
	/** Card detail URL*/
	CARD_DETAIL_URL("http://xvyqk.mocklab.io/card/user"),
	
	/** Card lock URL*/
	CARD_LOCK_URL("http://xvyqk.mocklab.io/card/user?");

	
	/** The url*/
	private final String url;
	
	UrlType(String url){
		
		this.url = url;
	}
	
	/** Gets the url*/
	public String getUrl() {
		return url;
	}
}
