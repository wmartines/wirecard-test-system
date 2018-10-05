package wirecard.test.java;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.UserRegistrationPojo;
import type.RegistrationStatusType;


public class RegistrationTest {
	
	@Test
	public void verifyCardPanOnRegistration ()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPost request = new HttpPost( "http://xvyqk.mocklab.io/register" );
	   
	   // Json string
	   String json = "{\n" + 
	   		"  \"salutation\":\"wirecard\",\n" + 
	   		"  \"firstName\":\"mocktest\",\n" + 
	   		"  \"lastName\":\"wirecard\",\n" + 
	   		"  \"birthDate\":\"1987-05-25\",\n" + 
	   		"  \"mobileNumber\":\"+3538395234\",\n" + 
	   		"  \"email\":\"test@wirecard.com\",\n" + 
	   		"  \"loginName\":\"wirecard\",\n" + 
	   		"  \"password\":\"wirecard123\"\n" + 
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
	   UserRegistrationPojo result = new ObjectMapper().readValue(JsonToString, UserRegistrationPojo.class);
	   
	   // Assert result request
	   Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_OK);
	   Assert.assertTrue(StringUtils.isNotEmpty(result.getPan())) ;  
	}
	
	@Test
	public void checkLoginNameAfterRegistration ()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPost request = new HttpPost( "http://xvyqk.mocklab.io/register" );
	   
	   // Json string
	   String json = "{\n" + 
	   		"  \"salutation\":\"wirecard\",\n" + 
	   		"  \"firstName\":\"mocktest\",\n" + 
	   		"  \"lastName\":\"wirecard\",\n" + 
	   		"  \"birthDate\":\"1987-05-25\",\n" + 
	   		"  \"mobileNumber\":\"+3538395234\",\n" + 
	   		"  \"email\":\"test@wirecard.com\",\n" + 
	   		"  \"loginName\":\"wirecard\",\n" + 
	   		"  \"password\":\"wirecard123\"\n" + 
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
	   UserRegistrationPojo result = new ObjectMapper().readValue(JsonToString, UserRegistrationPojo.class);
	   
	   // Assert result request
	   Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_OK);
	   Assert.assertTrue(StringUtils.isNotEmpty(result.getLoginName())) ; 
	}

	@Test
	public void verifyRegistrationStatus ()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPost request = new HttpPost( "http://xvyqk.mocklab.io/register" );
	   
	   // Json string
	   String json = "{\n" + 
	   		"\"salutation\":\"MR\", \n" + 
	   		"\"firstName\":\"mocktest\",\n" + 
	   		"\"lastName\":\"wirecard\",\n" + 
	   		"\"birthDate\":\"1987-05-25\", \n" + 
	   		"\"mobileNumber\":\"+3538395234\",\n" + 
	   		"\"email\":\"test@wirecard.com\", \n" + 
	   		"\"loginName\":\"wirecard\", \n" + 
	   		"\"password\":\"wirecard123\"\n" + 
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
	   UserRegistrationPojo result = new ObjectMapper().readValue(JsonToString, UserRegistrationPojo.class);
	   
	   // Assert result request
	   Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_OK);
	   Assert.assertTrue(result.getStatus().equals(RegistrationStatusType.ACTIVE.getDescription()));  
	}
	
	@Test
	public void verifyRegistrationStatusMissingInformations ()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPost request = new HttpPost( "http://xvyqk.mocklab.io/register" );
	   
	   // Json string
	   String json = "{\n" + 
	   		"\"salutation\":\"MR\", \n" + 
	   		"\"firstName\":\"mocktest\",\n" + 
	   		"\"lastName\":\"wirecard\",\n" + 
	   		"\"birthDate\":\"1987-05-25\", \n" + 
	   		"\"mobileNumber\":\"+3538395234\",\n" + 
	   		"\"email\":\"\", \n" + 
	   		"\"loginName\":\"\", \n" + 
	   		"\"password\":\"wirecard123\"\n" + 
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
	   UserRegistrationPojo result = new ObjectMapper().readValue(JsonToString, UserRegistrationPojo.class);
	   
	   // Assert result request
	   Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_OK);
	   Assert.assertTrue(result.getStatus().equals(RegistrationStatusType.IN_REGISTRATION.getDescription()));  
	}
}
