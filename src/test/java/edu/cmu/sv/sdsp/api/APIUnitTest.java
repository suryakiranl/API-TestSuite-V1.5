package edu.cmu.sv.sdsp.api;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import edu.cmu.sv.sdsp.api.helper.APIHelper;
import edu.cmu.sv.sdsp.api.helper.APIHelper.ResultType;
import edu.cmu.sv.sdsp.utils.Constants;
import edu.cmu.sv.sdsp.utils.Logger;

/**
 * Class to perform individual unit testing on API to check if the API
 * operations work independently.
 * 
 * @author Surya Kiran
 */
@RunWith(JUnit4.class)
public class APIUnitTest extends BaseTest {
	private static final Logger log = Logger.get();
	/**
	 *  Add a rule that all JUnit test cases run for a maximum of 2 minutes.
	 */
	@Rule
	public Timeout testCaseTimeOut = new Timeout(Constants.JUNIT_TIMEOUT);
	
	@Test
	public void testGetAllDevices() {
		try {
			String resp = APIHelper.processGetAllDevices(null);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllDevicesCSV() {
		try {
			String resp = APIHelper.processGetAllDevices(ResultType.CSV);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllDevicesJSON() {
		try {
			String resp = APIHelper.processGetAllDevices(ResultType.JSON);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllDeviceTypes() {
		try {
			String resp = APIHelper.processGetAllDeviceTypes(null);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllDeviceTypesCSV() {
		try {
			String resp = APIHelper.processGetAllDeviceTypes(ResultType.CSV);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllDeviceTypesJSON() {
		try {
			String resp = APIHelper.processGetAllDeviceTypes(ResultType.JSON);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllSensorTypes() {
		try {
			String resp = APIHelper.processGetAllSensorTypes(null);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllSensorTypesCSV() {
		try {
			String resp = APIHelper.processGetAllSensorTypes(ResultType.CSV);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllSensorTypesJSON() {
		try {
			String resp = APIHelper.processGetAllSensorTypes(ResultType.JSON);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllSensors() {
		try {
			String resp = APIHelper.processGetAllSensors(null);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllSensorsCSV() {
		try {
			String resp = APIHelper.processGetAllSensors(ResultType.CSV);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllSensorsJSON() {
		try {
			String resp = APIHelper.processGetAllSensors(ResultType.JSON);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllSensorCategories() {
		try {
			String resp = APIHelper.processGetAllSensorCategories(null);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllSensorCategoriesCSV() {
		try {
			String resp = APIHelper.processGetAllSensorCategories(ResultType.CSV);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void testGetAllSensorCategoriesJSON() {
		try {
			String resp = APIHelper.processGetAllSensorCategories(ResultType.JSON);
			assertReponseNotNull(resp);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
}
