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
import edu.cmu.sv.sdsp.api.helper.APIHelper.ResultType;
import edu.cmu.sv.sdsp.api.json.Device;
import edu.cmu.sv.sdsp.api.json.DeviceType;
import edu.cmu.sv.sdsp.api.json.Location;
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
	public void addAndRemoveDevice() {
		try {
			SensorCategory sc = new SensorCategory("JUnit-Test-SC4",
					"For Integration Testing");
			SensorType st4 = new SensorType("JUnit-Test-ST4",
					sc.getSensorCategoryName());
			SensorType st5 = new SensorType("JUnit-Test-ST5",
					sc.getSensorCategoryName());
			List<String> sensorTypeNames = new ArrayList<String>();
			sensorTypeNames.add(st4.getSensorTypeName());
			sensorTypeNames.add(st5.getSensorTypeName());
			DeviceType dt = new DeviceType("JUnit-Test-DT2", sensorTypeNames);
			Location l = new Location("JUnit-Test-Location1");
			Device d = new Device(dt.getDeviceTypeName(), "JUnit-Test-Device1",
					l);

			addSensorCategory(sc);
			addSensorType(st4);
			addSensorType(st5);
			addDeviceType(dt);
			addDevice(d);

			// Clean up
			deleteDevice(d);
			deleteDeviceType(dt);
			deleteSensorType(st5);
			deleteSensorType(st4);
			deleteSensorCategory(sc);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}

	@Test
	public void addAndRemoveSensor() {
		try {
			SensorCategory sc = new SensorCategory("JUnit-Test-SC5",
					"For Integration Testing");
			SensorType st6 = new SensorType("JUnit-Test-ST6",
					sc.getSensorCategoryName());
			SensorType st7 = new SensorType("JUnit-Test-ST7",
					sc.getSensorCategoryName());
			List<String> sensorTypeNames = new ArrayList<String>();
			sensorTypeNames.add(st6.getSensorTypeName());
			sensorTypeNames.add(st7.getSensorTypeName());
			DeviceType dt = new DeviceType("JUnit-Test-DT3", sensorTypeNames);
			Location l = new Location("JUnit-Test-Location2");
			Device d = new Device(dt.getDeviceTypeName(), "JUnit-Test-Device2",
					l);
			Sensor s = new Sensor("JUnit-Test-Sensor1",
					st6.getSensorTypeName(), d.getUri());

			addSensorCategory(sc);
			addSensorType(st6);
			addSensorType(st7);
			addDeviceType(dt);
			addDevice(d);
			addSensor(s);

			// Clean up
			deleteSensor(s);
			deleteDevice(d);
			deleteDeviceType(dt);
			deleteSensorType(st7);
			deleteSensorType(st6);
			deleteSensorCategory(sc);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void updateSensorType() {
		try {
			SensorCategory sc = new SensorCategory("JUnit-Test-SC6",
					"For Integration Testing");
			SensorType st = new SensorType("JUnit-Test-ST8",
					sc.getSensorCategoryName());

			addSensorCategory(sc);
			addSensorType(st);
			
			// Update
			st.setSensorTypeUserDefinedFields("Updated value: good");
			updateSensorType(st);

			// Clean up
			deleteSensorType(st);
			deleteSensorCategory(sc);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void updateSensorCategory() {
		try {
			SensorCategory sc = new SensorCategory("JUnit-Test-SC7",
					"For Integration Testing");

			addSensorCategory(sc);
			
			// Update
			sc.setPurpose("Purpose changed to Production testing");
			updateSensorCategory(sc);
			
			// Clean up
			deleteSensorCategory(sc);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void updateSensor() {
		try {
			SensorCategory sc = new SensorCategory("JUnit-Test-SC8",
					"For Integration Testing");
			SensorType stA = new SensorType("JUnit-Test-ST9",
					sc.getSensorCategoryName());
			SensorType stB = new SensorType("JUnit-Test-ST10",
					sc.getSensorCategoryName());
			List<String> sensorTypeNames = new ArrayList<String>();
			sensorTypeNames.add(stA.getSensorTypeName());
			sensorTypeNames.add(stB.getSensorTypeName());
			DeviceType dt = new DeviceType("JUnit-Test-DT4", sensorTypeNames);
			Location l = new Location("JUnit-Test-Location3");
			Device d = new Device(dt.getDeviceTypeName(), "JUnit-Test-Device3",
					l);
			Sensor s = new Sensor("JUnit-Test-Sensor2",
					stA.getSensorTypeName(), d.getUri());

			addSensorCategory(sc);
			addSensorType(stA);
			addSensorType(stB);
			addDeviceType(dt);
			addDevice(d);
			addSensor(s);
			
			// Update 
			s.setSensorUserDefinedFields("Updated value: good");
			updateSensor(s);

			// Clean up
			deleteSensor(s);
			deleteDevice(d);
			deleteDeviceType(dt);
			deleteSensorType(stB);
			deleteSensorType(stA);
			deleteSensorCategory(sc);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void updateDeviceType() {
		try {
			SensorCategory sc = new SensorCategory("JUnit-Test-SC9",
					"For Integration Testing");
			SensorType stA = new SensorType("JUnit-Test-ST11",
					sc.getSensorCategoryName());
			SensorType stB = new SensorType("JUnit-Test-ST12",
					sc.getSensorCategoryName());
			List<String> sensorTypeNames = new ArrayList<String>();
			sensorTypeNames.add(stA.getSensorTypeName());
			sensorTypeNames.add(stB.getSensorTypeName());
			DeviceType dt = new DeviceType("JUnit-Test-DT5", sensorTypeNames);

			addSensorCategory(sc);
			addSensorType(stA);
			addSensorType(stB);
			addDeviceType(dt);
			
			// Update
			dt.setDeviceTypeUserDefinedFields("Updated value: good");
			updateDeviceType(dt);

			// Clean up
			deleteDeviceType(dt);
			deleteSensorType(stB);
			deleteSensorType(stA);
			deleteSensorCategory(sc);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void updateDevice() {
		try {
			SensorCategory sc = new SensorCategory("JUnit-Test-SC10",
					"For Integration Testing");
			SensorType st4 = new SensorType("JUnit-Test-ST13",
					sc.getSensorCategoryName());
			SensorType st5 = new SensorType("JUnit-Test-ST14",
					sc.getSensorCategoryName());
			List<String> sensorTypeNames = new ArrayList<String>();
			sensorTypeNames.add(st4.getSensorTypeName());
			sensorTypeNames.add(st5.getSensorTypeName());
			DeviceType dt = new DeviceType("JUnit-Test-DT6", sensorTypeNames);
			Location l = new Location("JUnit-Test-Location4");
			Device d = new Device(dt.getDeviceTypeName(), "JUnit-Test-Device4",
					l);

			addSensorCategory(sc);
			addSensorType(st4);
			addSensorType(st5);
			addDeviceType(dt);
			addDevice(d);

			// Update
			Location lMoved = new Location("JUnit-Test-Location5");
			d.setLocation(lMoved);
			updateDevice(d);
			
			// Clean up
			deleteDevice(d);
			deleteDeviceType(dt);
			deleteSensorType(st5);
			deleteSensorType(st4);
			deleteSensorCategory(sc);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	@Test
	public void getSensorCategory() {
		SensorCategory sc = new SensorCategory("JUnit-Test-SC1",
				"For Integration Testing");
		try {
			addSensorCategory(sc);
			
			getSensorCategory(sc, null);
			
			deleteSensorCategory(sc);
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
	
	private String getSensorCategory(SensorCategory sc, ResultType type) throws HttpException, IOException {
		String resp = APIHelper.getSensorCategory(sc, type);
		assertReponseNotNull(resp);
		
		return resp;
	}
	
	private String updateDevice(Device d)
			throws HttpException, IOException {
		String resp = APIHelper.updateDevice(d);
		Assert.assertTrue(
				"Response should contain the content: " + d.getUri(),
				resp.contains(d.getUri()));

		return resp;
	}
	
	private String updateDeviceType(DeviceType dt)
			throws HttpException, IOException {
		String resp = APIHelper.updateDeviceType(dt);
		Assert.assertTrue(
				"Response should contain the content: " + dt.getDeviceTypeUserDefinedFields(),
				resp.contains(dt.getDeviceTypeUserDefinedFields()));

		return resp;
	}
	
	private String updateSensor(Sensor s)
			throws HttpException, IOException {
		String resp = APIHelper.updateSensor(s);
		Assert.assertTrue(
				"Response should contain the word 'Sensor updated'",
				resp.contains("Sensor updated"));

		return resp;
	}
	
	private String updateSensorCategory(SensorCategory sc) throws HttpException,
			IOException {
		String resp = APIHelper.updateSensorCategory(sc);
		Assert.assertTrue(
				"Response should contain the word 'Sensor category updated'",
				resp.contains("Sensor category updated"));

		return resp;
	}
	
	private String updateSensorType(SensorType st) throws HttpException,
			IOException {
		String resp = APIHelper.updateSensorType(st);
		Assert.assertTrue(
				"Response should contain the word 'Sensor type updated'",
				resp.contains("Sensor type updated"));

		return resp;
	}

	private String addSensor(Sensor s) throws HttpException, IOException {
		String resp = APIHelper.addSensor(s);
		Assert.assertTrue("Response should contain the word 'Sensor saved'",
				resp.contains("Sensor saved"));

		return resp;
	}

	private String deleteSensor(Sensor s) throws HttpException, IOException {
		String resp = APIHelper.deleteSensor(s);
		Assert.assertTrue("Response should contain the word 'Sensor deleted'",
				resp.contains("Sensor deleted"));

		return resp;
	}

	private String addDevice(Device d) throws HttpException, IOException {
		String resp = APIHelper.addDevice(d);
		Assert.assertTrue("Response should contain the word 'device saved'",
				resp.contains("device saved"));

		return resp;
	}

	private String deleteDevice(Device d) throws HttpException, IOException {
		String resp = APIHelper.deleteDevice(d);
		Assert.assertTrue("Response should contain the word 'Device deleted'",
				resp.contains("Device deleted"));

		return resp;
	}

	private String addDeviceType(DeviceType dt) throws HttpException,
			IOException {
		String resp = APIHelper.addDeviceType(dt);
		Assert.assertTrue(
				"Response should contain the word 'device type saved'",
				resp.contains("device type saved"));

		return resp;
	}

	private String deleteDeviceType(DeviceType dt) throws HttpException,
			IOException {
		String resp = APIHelper.deleteDeviceType(dt);
		Assert.assertTrue(
				"Response should contain the word 'Device type deleted'",
				resp.contains("Device type deleted"));

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
