package edu.cmu.sv.sdsp.api;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import edu.cmu.sv.sdsp.api.helper.APIHelper;
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
}
