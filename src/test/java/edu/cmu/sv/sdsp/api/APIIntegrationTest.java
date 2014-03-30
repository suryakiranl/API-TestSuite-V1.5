package edu.cmu.sv.sdsp.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import edu.cmu.sv.sdsp.api.helper.APIHelper;
import edu.cmu.sv.sdsp.api.json.DeviceType;
import edu.cmu.sv.sdsp.api.json.Sensor;
import edu.cmu.sv.sdsp.api.json.SensorCategory;
import edu.cmu.sv.sdsp.api.json.SensorType;
import edu.cmu.sv.sdsp.utils.Logger;

/**
 * Class to perform integration tests on the API to verify it.
 * 
 * @author Surya Kiran
 *
 */
@RunWith(JUnit4.class)
public class APIIntegrationTest extends BaseTest {
	private static final Logger log = Logger.get();

	@Test
	public void addAndRemoveSensorCategory() {
		SensorCategory sc = new SensorCategory("JUnit-Test-SC1",
				"For Integration Testing");
		try {
			addSensorCategory(sc);
			deleteSensorCategory(sc);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}

	@Test
	public void addAndRemoveSensorType() {
		try {
			SensorCategory sc = new SensorCategory("JUnit-Test-SC2",
					"For Integration Testing");
			SensorType st = new SensorType("JUnit-Test-ST1",
					sc.getSensorCategoryName());

			addSensorCategory(sc);
			addSensorType(st);
			
			// Clean up
			deleteSensorType(st);
			deleteSensorCategory(sc);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test 
	public void addAndRemoveDeviceType() {
		try {
			SensorCategory sc = new SensorCategory("JUnit-Test-SC3",
					"For Integration Testing");
			SensorType st1 = new SensorType("JUnit-Test-ST2",
					sc.getSensorCategoryName());
			SensorType st2 = new SensorType("JUnit-Test-ST3",
					sc.getSensorCategoryName());
			List<String> sensorTypeNames = new ArrayList<String>();
			sensorTypeNames.add(st1.getSensorTypeName());
			sensorTypeNames.add(st2.getSensorTypeName());
			DeviceType dt = new DeviceType("JUnit-Test-DT1", sensorTypeNames);

			addSensorCategory(sc);
			addSensorType(st1);
			addSensorType(st2);
			addDeviceType(dt);
			
			// Clean up
			deleteDeviceType(dt);
			deleteSensorType(st2);
			deleteSensorType(st1);
			deleteSensorCategory(sc);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}

	@Test
	public void addAndRemoveSensor() {
		// TODO Not fully implemented
		try {
			SensorCategory sc = new SensorCategory("JUnit-Test-SC3",
					"For Integration Testing");
			SensorType st = new SensorType("JUnit-Test-ST2",
					sc.getSensorCategoryName());
			Sensor s = new Sensor("JUnit-Test-Sensor1", st.getSensorTypeName(),
					"JUnit-Test-Sensor1-URI");

			addSensorCategory(sc);
			addSensorType(st);
			addSensor(s);
			
			// Clean up
			deleteSensor(s);
			deleteSensorType(st);
			deleteSensorCategory(sc);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}

	private String addDeviceType(DeviceType dt) throws HttpException, IOException {
		String resp = APIHelper.addDeviceType(dt);
		 Assert.assertTrue(
		 "Response should contain the word 'device type saved'",
		 resp.contains("device type saved"));

		return resp;
	}

	private String deleteDeviceType(DeviceType dt) throws HttpException, IOException {
		String resp = APIHelper.deleteDeviceType(dt);
		 Assert.assertTrue(
		 "Response should contain the word 'Device type deleted'",
		 resp.contains("Device type deleted"));

		return resp;
	}
	
	private String addSensor(Sensor s) throws HttpException, IOException {
		String resp = APIHelper.addSensor(s);
		// Assert.assertTrue(
		// "Response should contain the word 'Sensor saved'",
		// resp.contains("Sensor saved"));

		return resp;
	}

	private String deleteSensor(Sensor s) throws HttpException, IOException {
		String resp = APIHelper.deleteSensor(s);
		 Assert.assertTrue(
		 "Response should contain the word 'Sensor deleted'",
		 resp.contains("Sensor deleted"));

		return resp;
	}

	private String addSensorType(SensorType st) throws HttpException,
			IOException {
		String resp = APIHelper.addSensorType(st);
		Assert.assertTrue(
				"Response should contain the word 'Sensor type saved'",
				resp.contains("Sensor type saved"));

		return resp;
	}

	private String deleteSensorType(SensorType st) throws HttpException,
			IOException {
		String resp = APIHelper.deleteSensorType(st);
		Assert.assertTrue(
				"Response should contain the word 'Sensor type deleted'",
				resp.contains("Sensor type deleted"));

		return resp;
	}

	private String addSensorCategory(SensorCategory sc) throws HttpException,
			IOException {
		String resp = APIHelper.addSensorCategory(sc);
		Assert.assertTrue(
				"Response should contain the word 'Sensor category saved'",
				resp.contains("Sensor category saved"));

		return resp;
	}

	private String deleteSensorCategory(SensorCategory sc)
			throws HttpException, IOException {
		String resp = APIHelper.deleteSensorCategory(sc);
		Assert.assertTrue(
				"Response should contain the word 'Sensor category is deleted'",
				resp.contains("Sensor category is deleted"));

		return resp;
	}
}
