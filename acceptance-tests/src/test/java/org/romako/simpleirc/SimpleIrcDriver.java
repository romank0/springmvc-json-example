package org.romako.simpleirc;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

public class SimpleIrcDriver {

	private ObjectMapper mapper = new ObjectMapper();
	
	private String baseUrl;
	
	public SimpleIrcDriver(final String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public <T> T get(String url, Class<T> clazz) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(baseUrl + url);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
		    return mapper.readValue(entity.getContent(), clazz);
		} else {
			throw new RuntimeException("Not http entity in response");
		}
	}

	public void post(String url, Object object) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(baseUrl + url);
		httppost.setHeader("Content-type", "application/json");
		httppost.setHeader("Accept", "application/json");
		StringEntity entity = new StringEntity(mapper.writeValueAsString(object), 
			    "UTF-8");
		httppost.setEntity(entity);
		HttpResponse response = httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Error executing post " + response.getStatusLine());
		}
		
	}
}
