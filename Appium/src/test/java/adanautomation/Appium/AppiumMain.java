package adanautomation.Appium;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class AppiumMain {
	
	
	public AndroidDriver driver;
	
	
	public void appiumTest() throws MalformedURLException { 
		
		UiAutomator2Options options = new UiAutomator2Options() ;
		
		options.setDeviceName("SH-M05");
        options.setPlatformName("Android");
        options.setAppPackage("com.mobile.number.location.call.number.locator.call.tracker");
        options.setAppActivity("com.number.tracker.numbertracker.activities.LauncherActivity");
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
        options.setAutomationName("UIAutomator2"); // Default for Android
        options.setPlatformVersion("9"); // Specify Android version explicitly (if necessary)
        options.setCapability("chromedriverExecutable", "C:\\Users\\INDUS\\Downloads\\chromedriver-win64\\chromedriver.exe");
        
        options.setCapability("enablePerformanceStats", true);

		
//        options.setCapability("appium:enableImageComparison", true);
//        options.setCapability("appium:visualMatchThreshold", 0.8);
		
		 driver = new AndroidDriver(new URL ("http://127.0.0.1:4723/"), options);
		 
		// driver.findElement(By.id("com.example.app:id/button")).click();
		 
		// driver.quit();
		 
		
	}
	


}

