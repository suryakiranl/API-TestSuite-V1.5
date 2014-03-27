package edu.cmu.sv.sdsp.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * Utility Helper class to make GET and POST requests to a URL. This class also
 * handles parameters you would like to send as part of the POST request. Note
 * that responses from the server are returned to the caller of this API for 
 * every request.
 * 
 * @author Surya Kiran
 *
 */
public class HttpHelper {
	private static final Logger log = Logger.get();
	
	/**
	 * Utility method which performs GET operation on a URL
	 * and returns the response from server. 
	 * 
	 * @param urlStr - URL to perform a GET on.
	 * 
	 * @return - String representation of the response.
	 * 
	 * @throws HttpException
	 * @throws IOException
	 */
	public String performHttpGet(String urlStr) throws HttpException, IOException {
		log.enter(urlStr);
		
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(Constants.CONNECTION_TIMEOUT);
				
		GetMethod get = new GetMethod(urlStr);
		get.setFollowRedirects(true);

		String response = null;
		try {
			int responseCode = client.executeMethod(get);
			log.debug("Response code from server :: " + responseCode);
			response = get.getResponseBodyAsString();	
		} finally {
			get.releaseConnection();
		}
		
		log.exit(response);
		return response;
	}
}
