package adanautomation.Appium;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


import java.io.FileReader;

public class AppiumMain {
	
	
	public AndroidDriver driver;
	
	//new keep if works
	 @BeforeClass
	    public void setup() throws Exception {
	        // Load the JSON file from the resources folder
	        JSONParser parser = new JSONParser();
	        ClassLoader classLoader = getClass().getClassLoader();
	        FileReader fileReader = new FileReader(classLoader.getResource("devices.json").getFile());
	        JSONObject jsonObject = (JSONObject) parser.parse(fileReader);
	        JSONArray devices = (JSONArray) jsonObject.get("devices");

	        // Use the first device configuration from JSON (Modify as needed for multiple devices)
	        JSONObject deviceConfig = (JSONObject) devices.get(0);
	        initializeDriver(deviceConfig);
	    }
	
	
	 @SuppressWarnings("deprecation")
	public void initializeDriver(JSONObject deviceConfig) throws MalformedURLException { 
		
		UiAutomator2Options options = new UiAutomator2Options() ;
		
		options.setDeviceName((String) deviceConfig.get("deviceName"));
        options.setPlatformName((String) deviceConfig.get("PlatformName"));
        options.setAppPackage("com.mobile.number.location.call.number.locator.call.tracker");
        options.setAppActivity("com.number.tracker.numbertracker.activities.LauncherActivity");
        options.setAutomationName("UIAutomator2"); // Default for Android
        options.setPlatformVersion((String) deviceConfig.get("PlatformVersion"));
        options.setNoReset(true);  // Prevent from restarting
        
        
     // Enable WebView context handling
        options.setCapability("chromedriver_autodownload", true); // Automatically download the appropriate Chromedriver

        // Optionally specify Chromedriver path if you want to manage it manually
        // options.setCapability("chromedriverExecutable", "/path/to/chromedriver");

        // Allow switching between native and WebView contexts
        options.setCapability("autoGrantPermissions", true); // Automatically grant required app permissions
        options.setCapability("autoAcceptAlerts", true); // Automatically handle pop-ups and alerts

        // Enable logging for debugging purposes
        options.setCapability("enablePerformanceLogging", true); // Enable performance logging
        options.setCapability("loggingPrefs", Map.of("browser", "ALL", "driver", "ALL")); // Log browser and driver actions

        // Ensure Appium can detect hybrid apps
        options.setCapability("webviewConnectTimeout", 10000); // Timeout for connecting to WebView
        options.setCapability("ensureWebviewsHavePages", true); // Ensure WebView has loaded content before switching

        // Set automation name and platform version explicitly if needed
         // Specify Android version explicitly (if necessary)
        options.setCapability("chromedriverExecutable", "C:\\Users\\INDUS\\Downloads\\chromedriver-win64\\chromedriver.exe");
        
        options.setCapability("enablePerformanceStats", true);

		
//        options.setCapability("appium:enableImageComparison", true);
//        options.setCapability("appium:visualMatchThreshold", 0.8);
		
		 driver = new AndroidDriver(new URL ("http://127.0.0.1:"+ deviceConfig.get("port")), options);
		 
		// driver.findElement(By.id("com.example.app:id/button")).click();
		 
		// driver.quit();
		 
		
	}
	


}

