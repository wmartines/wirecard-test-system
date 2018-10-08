package wirecard.test.java;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import wirecard.test.pojo.ApiReturnPojo;
import wirecard.test.pojo.UserRegistrationPojo;
import wirecard.test.type.RegistrationKeyType;
import wirecard.test.type.RegistrationStatusType;
import wirecard.test.type.UrlType;

/**
 * Class responsible for testing the registration api
 * @author wilson
 *
 */
public class RegistrationTest {
	
	/**
	 * Test responsible to perform a registration with valid parameters and check pan number
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void performValidRegistrationAndCheckPanNumber ()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPut request = new HttpPut(UrlType.REGISTRATION_URL.getUrl());
	   
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
	
	/**
	 * Test responsible to perform a registration with valid parameters and check login name
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void verifyLoginNameAfterRegistration ()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPost request = new HttpPost(UrlType.REGISTRATION_URL.getUrl());
	   
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
	
	/**
	 * Test responsible to perform a registration with valid parameters and check status
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void verifyRegistrationStatus ()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPost request = new HttpPost(UrlType.REGISTRATION_URL.getUrl());
	   
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
	
	/**
	 * Test responsible to perform a registration with missing parameters not mandatory
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void verifyRegistrationStatusMissingInformations ()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPost request = new HttpPost(UrlType.REGISTRATION_URL.getUrl());
	   
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
	
	/**
	 * Test responsible to perform a registration with missing mandatory parameters
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void verifyRegistrationStatusMissingFirstName ()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPut request = new HttpPut(UrlType.REGISTRATION_URL.getUrl());
	   
	   // Json string
	   String json = "{\n" + 
	   		"\"salutation\":\"MR\", \n" + 
	   		"\"firstName\":\"\",\n" + 
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
	   ApiReturnPojo result = new ObjectMapper().readValue(JsonToString, ApiReturnPojo.class);
	   
	   // Assert result request
	   Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_BAD_REQUEST);
	   Assert.assertTrue(result.getDescription().equals(RegistrationKeyType.MISSING_MANDATORY_FIELD.getDescription()));
	   Assert.assertTrue(result.getErrorKey().equals(RegistrationKeyType.MISSING_MANDATORY_FIELD.toString())); 
	}
	
	/**
	 * Test responsible to perform a registration with missing mandatory parameters
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void verifyRegistrationStatusMissingBirthDate ()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPut request = new HttpPut(UrlType.REGISTRATION_URL.getUrl());
	   
	   // Json string
	   String json = "{\n" + 
	   		"\"salutation\":\"MR\", \n" + 
	   		"\"firstName\":\"wirecard\",\n" + 
	   		"\"lastName\":\"wirecard\",\n" + 
	   		"\"birthDate\":\"\", \n" + 
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
	   ApiReturnPojo result = new ObjectMapper().readValue(JsonToString, ApiReturnPojo.class);
	   
	   // Assert result request
	   Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_BAD_REQUEST);
	   Assert.assertTrue(result.getDescription().equals(RegistrationKeyType.MISSING_MANDATORY_FIELD.getDescription()));
	   Assert.assertTrue(result.getErrorKey().equals(RegistrationKeyType.MISSING_MANDATORY_FIELD.toString())); 
	}
	
	/**
	 * Test responsible to perform a registration with missing mandatory parameters
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void verifyRegistrationStatusMissingMobileNumber ()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPut request = new HttpPut(UrlType.REGISTRATION_URL.getUrl());
	   
	   // Json string
	   String json = "{\n" + 
	   		"\"salutation\":\"MR\", \n" + 
	   		"\"firstName\":\"wirecard\",\n" + 
	   		"\"lastName\":\"wirecard\",\n" + 
	   		"\"birthDate\":\"1987-05-25\", \n" + 
	   		"\"mobileNumber\":\"\",\n" + 
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
	   ApiReturnPojo result = new ObjectMapper().readValue(JsonToString, ApiReturnPojo.class);
	   
	   // Assert result request
	   Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_BAD_REQUEST);
	   Assert.assertTrue(result.getDescription().equals(RegistrationKeyType.MISSING_MANDATORY_FIELD.getDescription()));
	   Assert.assertTrue(result.getErrorKey().equals(RegistrationKeyType.MISSING_MANDATORY_FIELD.toString())); 
	}
	
	/**
	 * Test responsible to perform a registration with missing mandatory parameters
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void verifyRegistrationStatusMissingPassword ()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPut request = new HttpPut(UrlType.REGISTRATION_URL.getUrl());
	   
	   // Json string
	   String json = "{\n" + 
	   		"\"salutation\":\"MR\", \n" + 
	   		"\"firstName\":\"wirecard\",\n" + 
	   		"\"lastName\":\"wirecard\",\n" + 
	   		"\"birthDate\":\"1987-05-25\", \n" + 
	   		"\"mobileNumber\":\"+35383972\",\n" + 
	   		"\"email\":\"test@wirecard.com\", \n" + 
	   		"\"loginName\":\"wirecard\", \n" + 
	   		"\"password\":\"\"\n" + 
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
	   Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_BAD_REQUEST);
	   Assert.assertTrue(result.getDescription().equals(RegistrationKeyType.MISSING_MANDATORY_FIELD.getDescription()));
	   Assert.assertTrue(result.getErrorKey().equals(RegistrationKeyType.MISSING_MANDATORY_FIELD.toString())); 
	}
	
	/**
	 * Test responsible to perform a registration with missing mandatory parameters
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void verifyRegistrationStatusMissingSalutation ()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPut request = new HttpPut(UrlType.REGISTRATION_URL.getUrl());
	   
	   // Json string
	   String json = "{\n" + 
	   		"\"salutation\":\"\", \n" + 
	   		"\"firstName\":\"wirecard\",\n" + 
	   		"\"lastName\":\"wirecard\",\n" + 
	   		"\"birthDate\":\"1987-05-25\", \n" + 
	   		"\"mobileNumber\":\"+35383972\",\n" + 
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
	   ApiReturnPojo result = new ObjectMapper().readValue(JsonToString, ApiReturnPojo.class);
	   
	   // Assert result request
	   Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_BAD_REQUEST);
	   Assert.assertTrue(result.getDescription().equals(RegistrationKeyType.MISSING_MANDATORY_FIELD.getDescription()));
	   Assert.assertTrue(result.getErrorKey().equals(RegistrationKeyType.MISSING_MANDATORY_FIELD.toString())); 
	}
	
	/**
	 * Test responsible to perform a registration with mobile number already registered
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void verifyRegistrationStatusMobileNumberAlreadyRegistered()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPut request = new HttpPut(UrlType.REGISTRATION_URL.getUrl());
	   
	   // Json string
	   String json = "{\n" + 
	   		"\"salutation\":\"MR\", \n" + 
	   		"\"firstName\":\"wirecard\",\n" + 
	   		"\"lastName\":\"wirecard\",\n" + 
	   		"\"birthDate\":\"1987-05-25\", \n" + 
	   		"\"mobileNumber\":\"+35383972\",\n" + 
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
	   ApiReturnPojo result = new ObjectMapper().readValue(JsonToString, ApiReturnPojo.class);
	   
	   // Assert result request
	   Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_FORBIDDEN);
	   Assert.assertTrue(result.getDescription().equals(RegistrationKeyType.USER_WITH_THIS_PHONE_NUMBER_EXISTS.getDescription()));
	   Assert.assertTrue(result.getErrorKey().equals(RegistrationKeyType.USER_WITH_THIS_PHONE_NUMBER_EXISTS.toString())); 
	}
	
	/**
	 * Test responsible to perform a registration with login name already registered
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void verifyRegistrationStatusLoginNameAlreadyRegistered()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPut request = new HttpPut(UrlType.REGISTRATION_URL.getUrl());
	   
	   // Json string
	   String json = "{\n" + 
	   		"\"salutation\":\"MR\", \n" + 
	   		"\"firstName\":\"wirecard\",\n" + 
	   		"\"lastName\":\"wirecard\",\n" + 
	   		"\"birthDate\":\"1987-05-25\", \n" + 
	   		"\"mobileNumber\":\"+35383972\",\n" + 
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
	   ApiReturnPojo result = new ObjectMapper().readValue(JsonToString, ApiReturnPojo.class);
	   
	   // Assert result request
	   Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_FORBIDDEN);
	   Assert.assertTrue(result.getDescription().equals(RegistrationKeyType.USER_WITH_THIS_LOGIN_NAME_EXISTS.getDescription()));
	   Assert.assertTrue(result.getErrorKey().equals(RegistrationKeyType.USER_WITH_THIS_LOGIN_NAME_EXISTS.toString())); 
	}
	
	/**
	 * Test responsible to perform a registration with email already registered
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void verifyRegistrationStatusEmailAlreadyRegistered()
	  throws ClientProtocolException, IOException {
	  
	   // Mock url
	   HttpPut request = new HttpPut(UrlType.REGISTRATION_URL.getUrl());
	   
	   // Json string
	   String json = "{\n" + 
	   		"\"salutation\":\"MR\", \n" + 
	   		"\"firstName\":\"wirecard\",\n" + 
	   		"\"lastName\":\"wirecard\",\n" + 
	   		"\"birthDate\":\"1987-05-25\", \n" + 
	   		"\"mobileNumber\":\"+35383972\",\n" + 
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
	   ApiReturnPojo result = new ObjectMapper().readValue(JsonToString, ApiReturnPojo.class);
	   
	   // Assert result request
	   Assert.assertTrue(httpResponse.getStatusLine().getStatusCode() ==  HttpStatus.SC_FORBIDDEN);
	   Assert.assertTrue(result.getDescription().equals(RegistrationKeyType.USER_WITH_THIS_EMAIL_EXISTS.getDescription()));
	   Assert.assertTrue(result.getErrorKey().equals(RegistrationKeyType.USER_WITH_THIS_EMAIL_EXISTS.toString())); 
	}
}
