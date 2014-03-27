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
	 * Supported result types
	 */
	public static enum ResultType {
		JSON, CSV
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

	public static String processGetAllDevices(ResultType type)
			throws HttpException, IOException {
		String url = (type == null) ? GET_ALL_DEVICES : GET_ALL_DEVICES + "/"
				+ type.toString().toLowerCase();

		return invokeHttpGet(url);
	}

	public static String processGetAllDeviceTypes(ResultType type)
			throws HttpException, IOException {
		String url = (type == null) ? GET_ALL_DEVICE_TYPES
				: GET_ALL_DEVICE_TYPES + "/" + type.toString().toLowerCase();

		return invokeHttpGet(url);
	}

	public static String processGetAllSensorTypes(ResultType type)
			throws HttpException, IOException {
		String url = (type == null) ? GET_ALL_SENSOR_TYPES
				: GET_ALL_SENSOR_TYPES + "/" + type.toString().toLowerCase();

		return invokeHttpGet(url);
	}

	public static String processGetAllSensors(ResultType type)
			throws HttpException, IOException {
		String url = (type == null) ? GET_ALL_SENSORS : GET_ALL_SENSORS + "/"
				+ type.toString().toLowerCase();

		return invokeHttpGet(url);
	}

	public static String processGetAllSensorCategories(ResultType type)
			throws HttpException, IOException {
		String url = (type == null) ? GET_ALL_SENSOR_CATEGORIES
				: GET_ALL_SENSOR_CATEGORIES + "/"
						+ type.toString().toLowerCase();

		return invokeHttpGet(url);
	}
}
