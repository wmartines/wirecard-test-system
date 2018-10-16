package wirecard.test.java;

import java.io.IOException;
import java.util.UUID;

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
import wirecard.test.type.RegistrationKeyType;
import wirecard.test.type.UrlType;

/**
 * Class created to create, edit and execute a single test
 * @author wilson
 *
 */
public class TestFactory {
	
	/**
	 * Test responsible to perform a registration with mobile number already registered
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void verifyRegistrationStatusMobileNumberAlreadyRegistered()
	  throws ClientProtocolException, IOException {
	  
		String randomID = UUID.randomUUID().toString().replaceAll("[^\\d]", "");
		
	   // Mock url
	   HttpPut request = new HttpPut(UrlType.REGISTRATION_URL.getUrl());
	   
	// Json string
	   String json = "{\n" + 
	   		"	\n" + 
	   		"	\"salutation\":\"MR\",\n" + 
	   		"	\"firsName\":\"Mike\",\n" + 
	   		"	\"lastName\":\""+randomID+"\",\n" + 
	   		"	\"birthDate\":\"1987-05-25\",\n" + 
	   		"	\"mobileNumber\":\"+35383972\",\n" + 
	   		"	\"email\":\""+randomID+"@gmail.com\",\n" + 
	   		"	\"loginName\":\""+randomID+"\",\n" + 
	   		"	\"password\":\"wirecard123\"\n" + 
	   		"	\n" + 
	   		"}";
	   
	    // Sets json entity
	    StringEntity entity = new StringEntity(json);
	    request.setEntity(entity);
	    request.setHeader("Accept", "application/json");
	    request.setHeader("Content-type", "application/json");
	 
	   // Execute request
	   HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
	   
	   // Converts json to string
	   String JsonToString = EntityUtils.toString(httpResponse.getEntity());
	   
	   // Converts json to object
	   ApiReturnPojo result = new ObjectMapper().readValue(JsonToString, ApiReturnPojo.class);
	   
	   // Assert result request
	   Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_FORBIDDEN);
	   Assert.assertTrue(result.getErrorMessage().equals(RegistrationKeyType.USER_WITH_THIS_PHONE_NUMBER_EXISTS.getErrorMessage()));
	   Assert.assertTrue(result.getErrorKey().equals(RegistrationKeyType.USER_WITH_THIS_PHONE_NUMBER_EXISTS.toString())); 
	}
}
