package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestClient {

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(url);// http get request
		CloseableHttpResponse CloseableHttpResponse = httpclient.execute(httpget);// hit the url
		
		return CloseableHttpResponse;
		

	}

}
