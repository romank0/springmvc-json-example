package org.romako.simpleirc;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
	
	private static List<Integer> SUCCESS_POST_HTTP_CODES = Arrays.asList(200, 204);
	
	public SimpleIrcDriver(final String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public <T> T get(String url, Class<T> clazz) throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(baseUrl + encodeURI(url));
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
		HttpPost httppost = new HttpPost(baseUrl+encodeURI(url));
		httppost.setHeader("Content-type", "application/json");
		httppost.setHeader("Accept", "application/json");
		if (object != null) {
			StringEntity entity = new StringEntity(mapper.writeValueAsString(object), 
					"UTF-8");
			httppost.setEntity(entity);
		}
		HttpResponse response = httpclient.execute(httppost);
		if (!SUCCESS_POST_HTTP_CODES.contains(response.getStatusLine().getStatusCode())) {
			throw new RuntimeException("Error executing post " + response.getStatusLine());
		}
		
	}
	
    private static String mark = "-_.!~*'()\"";
    
    public static String encodeURI(String argString) {
        StringBuilder uri = new StringBuilder();
 
        char[] chars = argString.toCharArray();
        for(int i = 0; i<chars.length; i++) {
            char c = chars[i];
            if((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z') || mark.indexOf(c) != -1) {
                uri.append(c);
            }
            else {
                uri.append("%");
                uri.append(Integer.toHexString((int)c));
            }
        }
        return uri.toString();
    }
}
