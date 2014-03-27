package edu.cmu.sv.sdsp.api.helper;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import edu.cmu.sv.sdsp.utils.HttpHelper;
import edu.cmu.sv.sdsp.utils.Logger;

/**
 * Helper class which has utility methods to perform GET and POST operations on
 * all operations supported by the Sensor Data Platform Service API.
 * 
 * @author Surya Kiran
 *
 */
public class APIHelper {
	private static final Logger log = Logger.get();

	/**
	 * This is the host this project tests on.
	 */
	public static final String HOST_NAME = "http://einstein.sv.cmu.edu:9000";

	/**
	 * URL used for GET operation on API to get Devices
	 */
	public static final String GET_ALL_DEVICES = HOST_NAME + "/getAllDevices";

	/**
	 * Supported result types
	 */
	public static enum ResultType {
		JSON, CSV
	}

	public static String processGetAllDevices(ResultType type)
			throws HttpException, IOException {
		String url = (type == null) ? GET_ALL_DEVICES : GET_ALL_DEVICES + "/"
				+ type.toString().toLowerCase();

		return invokeHttpGet(url);
	}

	/**
	 * Utility function to invoke the HTTP GET operation.
	 * 
	 * @param url
	 *            - URL to request for.
	 * 
	 * @return - Response from server.
	 * 
	 * @throws HttpException
	 * @throws IOException
	 */
	private static final String invokeHttpGet(String url) throws HttpException,
			IOException {
		log.trace("Invoking a GET request for URL: " + url);

		String response = null;
		try {
			response = HttpHelper.performHttpGet(url);
		} finally {
			if (response != null && response.length() > 200) {
				response = response.substring(0, 200)
						+ " <<< String truncated to 200 chars";
			}
			log.trace("Response String: " + response);
		}

		return response;
	}
}