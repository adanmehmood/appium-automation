package adanautomation.PageObject;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class SplashScreen {
	
		AndroidDriver  driver;
		WebDriverWait wait;
	
	
	
	    public SplashScreen (AndroidDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
		
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		 
	}
	
	
		
		@AndroidFindBy(id="com.mobile.number.location.call.number.locator.call.tracker:id/progressBar")
		private WebElement progressBar;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text=\"Continue to app\"]")
		private WebElement continueBtn;
		
		@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/btn_next")
		private WebElement nextButton;
		


	
			public void waitForProgressBar() {
				wait.until(ExpectedConditions.invisibilityOf(progressBar)); // Wait for progress bar to disappear
				
			}
			
			 
			public boolean continueToApp() {
			    try {
			        System.out.println("Checking if Continue button is visible..."); // Log check
			        wait.until(ExpectedConditions.visibilityOf(continueBtn)); // Wait for visibility
			        System.out.println("Continue button is visible, clicking now."); // Log successful visibility
			        continueBtn.click(); // Click to continue
			        return true; // Button was clicked
			    } catch (TimeoutException e) {
			        System.out.println("Continue button did not appear within the wait time."); // Log failure
			    } catch (Exception e) {
			        System.out.println("An unexpected error occurred: " + e.getMessage()); // Log unexpected error
			    }
			    return false; // Button not clicked
			}
			
//			public void dississButton() {
//				wait.until(ExpectedConditions.visibilityOf(dismissButton));
//				dismissButton.click();
//				
//			}
			
			
			public void dismissAdByCoordinates() {
			    // Wait for 2 seconds for the ad to appear
			    try {
			        Thread.sleep(2000);
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }

			    // Create a new finger input for touch actions
			    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			    Sequence tap = new Sequence(finger, 1);

			    // Specify the coordinates where you want to tap
			    int x = 740;
			    int y = 1053;

			    // Perform the tap action at the specified coordinates
			    tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
			    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			    // Execute the tap action
			    driver.perform(Collections.singletonList(tap));
			}



			
			public void clickNextButton() {
			    wait.until(ExpectedConditions.visibilityOf(nextButton));
			    nextButton.click();
			}




}
