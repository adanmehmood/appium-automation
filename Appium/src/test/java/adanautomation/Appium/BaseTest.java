package adanautomation.Appium;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



	public class BaseTest {
	    @BeforeClass(alwaysRun = true)
	    @Parameters({"deviceName", "udid", "platformVersion","serverUrl"})
	    public void setup(String deviceName, String udid, String platformVersion, String serverUrl) throws Exception {
	    	
	        AppiumMain.setDriver(deviceName, udid, platformVersion,serverUrl);
	    }

	    @AfterClass(alwaysRun = true)
	    public void teardown() {
	        AppiumMain.quitDriver();
	    }
	}



