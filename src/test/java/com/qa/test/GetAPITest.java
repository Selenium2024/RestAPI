package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest extends TestBase {

	TestBase testbase;
	String ServiceURL;
	String APIURL;
	String ActualUrl;
	RestClient restClient;
	
	CloseableHttpResponse CloseableHttpResponse;

	@BeforeMethod
	public void setup() throws IOException {

		testbase = new TestBase();
		ServiceURL = prop.getProperty("url");
		APIURL = prop.getProperty("serviceUrl");

		ActualUrl = ServiceURL + APIURL;

	}

	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		CloseableHttpResponse=restClient.get(ActualUrl);
		
		
		
		//a status code
		int statusCode = CloseableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("statusCode----------->" + statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");

		//b Json String
		String responseString = EntityUtils.toString(CloseableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("responseJson---------------->" + responseJson);

		
		//c All headers
		Header[] headersArray = CloseableHttpResponse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers array---------->" + allHeaders);

	}

}