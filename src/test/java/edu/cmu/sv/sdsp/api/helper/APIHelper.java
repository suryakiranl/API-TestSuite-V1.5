package edu.cmu.sv.sdsp.api.helper;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import com.google.gson.Gson;

import edu.cmu.sv.sdsp.api.json.SensorCategory;
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
	 * Supported result types
	 */
	public static enum ResultType {
		JSON, CSV
	}

	private static enum RequestType {
		GET, POST, DELETE
	}

	/**
	 * This is the host this project tests on.
	 */
	public static final String HOST_NAME = "http://einstein.sv.cmu.edu:9000";

	/**
	 * URL used for GET operation on API to get all "Devices"
	 */
	public static final String GET_ALL_DEVICES = HOST_NAME + "/getAllDevices";

	/**
	 * URL used for GET operation on API to get all "Device Types"
	 */
	public static final String GET_ALL_DEVICE_TYPES = HOST_NAME
			+ "/getAllDeviceTypes";

	/**
	 * URL used for GET operation on API to get all "Sensor Types"
	 */
	public static final String GET_ALL_SENSOR_TYPES = HOST_NAME
			+ "/getAllSensorTypes";

	/**
	 * URL used for GET operation on API to get all "Sensors"
	 */
	public static final String GET_ALL_SENSORS = HOST_NAME + "/getAllSensors";

	/**
	 * URL used for GET operation on API to get all "Sensor Categories"
	 */
	public static final String GET_ALL_SENSOR_CATEGORIES = HOST_NAME
			+ "/getAllSensorCategories";

	/**
	 * URL to add a "Sensor Category" using POST
	 */
	public static final String ADD_SENSOR_CATEGORY = HOST_NAME
			+ "/addSensorCategory";
	
	/**
	 * URI to delete a "Sensor Category" using DELETE
	 */
	public static final String DELETE_SENSOR_CATEGORY = HOST_NAME + "/deleteSensorCategory";

	/**
	 * Utility function to invoke a HTTP operation.
	 * 
	 * @param type
	 *            - Is it a GET request or POST request
	 * @param url
	 *            - URL to invoke
	 * @param content
	 *            - Content to send for a POST request. Note that this is not
	 *            used for GET or DELETE requests.
	 * 
	 * @return - Response from server
	 * 
	 * @throws HttpException
	 * @throws IOException
	 */
	private static final String invokeHttpOperation(RequestType type,
			String url, String content) throws HttpException, IOException {
		log.trace("Invoking a GET request for URL: " + url);

		String response = null;
		try {
			switch (type) {
			case GET: response = HttpHelper.performHttpGet(url); break;
			case POST: response = HttpHelper.performHttpPost(url, content); break;
			case DELETE: response = HttpHelper.performHttpDelete(url); break;
			}
		} finally {
			logResponse(response);
		}

		return response;
	}

	private static void logResponse(String response) {
		if (response != null && response.length() > 200) {
			response = response.substring(0, 200)
					+ " <<< String truncated to 200 chars";
		}
		log.trace("Response String: " + response);
	}

	public static String getAllDevices(ResultType type) throws HttpException,
			IOException {
		String url = (type == null) ? GET_ALL_DEVICES : GET_ALL_DEVICES + "/"
				+ type.toString().toLowerCase();

		return invokeHttpOperation(RequestType.GET, url, null);
	}

	public static String getAllDeviceTypes(ResultType type)
			throws HttpException, IOException {
		String url = (type == null) ? GET_ALL_DEVICE_TYPES
				: GET_ALL_DEVICE_TYPES + "/" + type.toString().toLowerCase();

		return invokeHttpOperation(RequestType.GET, url, null);
	}

	public static String getAllSensorTypes(ResultType type)
			throws HttpException, IOException {
		String url = (type == null) ? GET_ALL_SENSOR_TYPES
				: GET_ALL_SENSOR_TYPES + "/" + type.toString().toLowerCase();

		return invokeHttpOperation(RequestType.GET, url, null);
	}

	public static String getAllSensors(ResultType type) throws HttpException,
			IOException {
		String url = (type == null) ? GET_ALL_SENSORS : GET_ALL_SENSORS + "/"
				+ type.toString().toLowerCase();

		return invokeHttpOperation(RequestType.GET, url, null);
	}

	public static String getAllSensorCategories(ResultType type)
			throws HttpException, IOException {
		String url = (type == null) ? GET_ALL_SENSOR_CATEGORIES
				: GET_ALL_SENSOR_CATEGORIES + "/"
						+ type.toString().toLowerCase();

		return invokeHttpOperation(RequestType.GET, url, null);
	}

	public static String addSensorCategory(SensorCategory sc)
			throws HttpException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(sc);

		return invokeHttpOperation(RequestType.POST, ADD_SENSOR_CATEGORY, json);
	}
	
	public static String deleteSensorCategory(SensorCategory sc) throws HttpException, IOException {
		String url = DELETE_SENSOR_CATEGORY + "/" + sc.getSensorCategoryName();
		
		return invokeHttpOperation(RequestType.DELETE, url, null);
	}
}
