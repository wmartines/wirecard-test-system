package wirecard.test.java;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import wirecard.test.pojo.ApiReturnPojo;
import wirecard.test.type.CardLockKeyType;
import wirecard.test.type.UrlType;

/**
 * Class responsible for testing the card lock request api
 * @author wilson
 *
 */
public class CardLockTest {
	
	/**
	 * Test responsible to perform a card lock with success
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void performCardLockWithValidInformations() throws ClientProtocolException, IOException {
		
		// Url parameters
		String loginName = "login-name=wirecard";
		String pan = "&pan=123456789012345";
		String lockRefId = "&lock-ref-id=123246457698686765375864588364657";

		// Mock url
		HttpPut request = new HttpPut(UrlType.CARD_LOCK_URL.getUrl()+loginName+pan+lockRefId);

		// Json string
		String json = "{\n" + 
				"\n" + 
				"  \"locking-reason\":\"card lost\"\n" + 
				"  \n" + 
				"}";

		// Sets json entity
		StringEntity entity = new StringEntity(json);
		request.setEntity(entity);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		// Execute request
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Converts json to string
		String JsonToString = EntityUtils.toString(httpResponse.getEntity());

		// Converts json to object
		ApiReturnPojo result = new ObjectMapper().readValue(JsonToString, ApiReturnPojo.class);

		// Assert result request
		Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK);
		Assert.assertTrue(result.getErrorMessage().equals(CardLockKeyType.OK.getDescription()));
		Assert.assertTrue(!StringUtils.isEmpty(result.getErrorMessage()));
	}
	
	/**
	 * Test responsible to perform a card lock with pan number empty
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void performCardLockMissingPanNumber() throws ClientProtocolException, IOException {
		
		// Url parameters
		String loginName = "login-name=wirecard";
		String pan = "&pan=";
		String lockRefId = "&lock-ref-id=123246457698686765375864588364657";

		// Mock url
		HttpPut request = new HttpPut(UrlType.CARD_LOCK_URL.getUrl()+loginName+pan+lockRefId);

		// Json string
		String json = "{\n" + 
				"\n" + 
				"  \"locking-reason\":\"card lost\"\n" + 
				"  \n" + 
				"}";

		// Sets json entity
		StringEntity entity = new StringEntity(json);
		request.setEntity(entity);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		// Execute request
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Converts json to string
		String JsonToString = EntityUtils.toString(httpResponse.getEntity());

		// Converts json to object
		ApiReturnPojo result = new ObjectMapper().readValue(JsonToString, ApiReturnPojo.class);

		// Assert result request
		Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_BAD_REQUEST);
		Assert.assertTrue(result.getErrorMessage().equals(CardLockKeyType.MISSING_PARAMETER.getDescription()));
		Assert.assertTrue(result.getErrorKey().equals(CardLockKeyType.MISSING_PARAMETER.toString()));
	}
	
	/**
	 * Test responsible to perform a card lock with alread used lock ref id
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void performCardLocWithAlreadyUsedLockRefId() throws ClientProtocolException, IOException {
		
		// Url parameters
		String loginName = "wirecard";
		String pan = "123456789012345";
		String lockRefId = "123246457698686765375864588364657";
		String url = UrlType.CARD_LOCK_URL.getUrl()+"login-name="+loginName+"&pan="+pan+"&lock-ref-id="+lockRefId;

		// Mock url
		HttpPut request = new HttpPut(url);

		// Json string
		String json = "{\n" + 
				"\n" + 
				"  \"locking-reason\":\"card lost\"\n" + 
				"  \n" + 
				"}";

		// Sets json entity
		StringEntity entity = new StringEntity(json);
		request.setEntity(entity);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		// Execute request
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Converts json to string
		String JsonToString = EntityUtils.toString(httpResponse.getEntity());

		// Converts json to object
		ApiReturnPojo result = new ObjectMapper().readValue(JsonToString, ApiReturnPojo.class);
		
		// Assert result request
		Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN);
		Assert.assertTrue(result.getErrorMessage().toLowerCase().indexOf(pan) != -1);
		Assert.assertTrue(result.getErrorKey().equals(CardLockKeyType.TRANSACTION_ALREADY_USED.toString()));
	}
	
	/**
	 * Test responsible to perform a card lock with pan number already locked
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void performCardLocWithPanCardAlreadyLocked() throws ClientProtocolException, IOException {
		
		// Url parameters
		String loginName = "wirecard";
		String pan = "123456789012345";
		String lockRefId = "123246457698686765375864588364657";
		String url = UrlType.CARD_LOCK_URL.getUrl()+"login-name="+loginName+"&pan="+pan+"&lock-ref-id="+lockRefId;

		// Mock url
		HttpPut request = new HttpPut(url);

		// Json string
		String json = "{\n" + 
				"\n" + 
				"  \"locking-reason\":\"card lost\"\n" + 
				"  \n" + 
				"}";

		// Sets json entity
		StringEntity entity = new StringEntity(json);
		request.setEntity(entity);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		// Execute request
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Converts json to string
		String JsonToString = EntityUtils.toString(httpResponse.getEntity());

		// Converts json to object
		ApiReturnPojo result = new ObjectMapper().readValue(JsonToString, ApiReturnPojo.class);
		
		// Assert result request
		Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN);
		Assert.assertTrue(result.getErrorMessage().equals(CardLockKeyType.CARD_ALREADY_LOCKED.getDescription()));
		Assert.assertTrue(result.getErrorKey().equals(CardLockKeyType.CARD_ALREADY_LOCKED.toString()));
	}
	
	/**
	 * Test responsible to perform a card lock with invalid pan card number
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void performCardLocWithInvalidPanCardNumber() throws ClientProtocolException, IOException {
		
		// Url parameters
		String loginName = "wirecard";
		String pan = "123456789012345";
		String lockRefId = "123246457698686765375864588364657";
		String url = UrlType.CARD_LOCK_URL.getUrl()+"login-name="+loginName+"&pan="+pan+"&lock-ref-id="+lockRefId;

		// Mock url
		HttpPut request = new HttpPut(url);

		// Json string
		String json = "{\n" + 
				"\n" + 
				"  \"locking-reason\":\"card lost\"\n" + 
				"  \n" + 
				"}";

		// Sets json entity
		StringEntity entity = new StringEntity(json);
		request.setEntity(entity);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		// Execute request
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Converts json to string
		String JsonToString = EntityUtils.toString(httpResponse.getEntity());

		// Converts json to object
		ApiReturnPojo result = new ObjectMapper().readValue(JsonToString, ApiReturnPojo.class);
		
		// Assert result request
		Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND);
		Assert.assertTrue(result.getErrorMessage().equals(CardLockKeyType.NO_CARD_FOUND.getDescription()));
		Assert.assertTrue(result.getErrorKey().equals(CardLockKeyType.NO_CARD_FOUND.toString()));
	}
}
