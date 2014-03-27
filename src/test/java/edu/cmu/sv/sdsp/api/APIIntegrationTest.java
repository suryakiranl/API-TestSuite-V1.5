package edu.cmu.sv.sdsp.api;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import edu.cmu.sv.sdsp.api.helper.APIHelper;
import edu.cmu.sv.sdsp.api.json.SensorCategory;
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
		SensorCategory sc = new SensorCategory("JUnit-Test-SC",
				"For Integration Testing");
		try {
			String resp = APIHelper.addSensorCategory(sc);
			Assert.assertTrue("Response should contain the word success",
					resp.contains("Sensor category saved"));

			resp = APIHelper.deleteSensorCategory(sc);
			Assert.assertTrue(
					"Response should contain the word 'Sensor category is deleted'",
					resp.contains("Sensor category is deleted"));
		} catch (IOException e) {
			log.error(e);
			Assert.fail();
		}
	}
}
