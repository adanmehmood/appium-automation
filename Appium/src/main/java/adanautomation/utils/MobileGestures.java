package adanautomation.utils;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import com.google.common.base.Supplier;


public class MobileGestures {
        private AndroidDriver driver;

        public MobileGestures(AndroidDriver driver) {
        this.driver = driver;
    }

		    public void swipeLeft(int repeat) {
		    	
		        int width = driver.manage().window().getSize().getWidth(); 
		        int height = driver.manage().window().getSize().getHeight();
		        
		        
		        for(int i = 0; i < repeat; i++) {
		        	
		        	 PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				        Sequence swipe = new Sequence(finger, 1);
				        
				        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), (int) (width * 0.9), height / 3));
				        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), (int) (width * 0.1), height / 3));
				        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				
				        driver.perform(java.util.Collections.singletonList(swipe));
		        	
		        }
		       
		    }
		    
		    
		    public void swipeRight() {
		        int width = driver.manage().window().getSize().getWidth();
		        int height = driver.manage().window().getSize().getHeight();
		
		        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		        Sequence swipe = new Sequence(finger, 1);
		
		        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), (int) (width * 0.1), height / 2));
		        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), (int) (width * 0.9), height / 2));
		        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		        driver.perform(java.util.Collections.singletonList(swipe));
		    }
		    
		    public void swipeUp() {
		        int width = driver.manage().window().getSize().getWidth();
		        int height = driver.manage().window().getSize().getHeight();
		
		        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		        Sequence swipe = new Sequence(finger, 1);
		
		        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), width / 2, (int) (height * 0.9)));
		        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), width / 2, (int) (height * 0.1)));
		        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		        driver.perform(java.util.Collections.singletonList(swipe));
		    }
		
		    public void swipeDown() {
		        int width = driver.manage().window().getSize().getWidth();
		        int height = driver.manage().window().getSize().getHeight();
		
		        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		        Sequence swipe = new Sequence(finger, 1);
		
		        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), width / 2, (int) (height * 0.1)));
		        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), width / 2, (int) (height * 0.9)));
		        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		        driver.perform(java.util.Collections.singletonList(swipe));
		    }
		    
		    
		    
		    
		    public void scrollAndClick(Supplier<WebElement> elementSupplier) {
		        int width = driver.manage().window().getSize().getWidth();
		        int height = driver.manage().window().getSize().getHeight();
		        
		        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		        int maxAttempts = 5; // Total attempts for swiping (up + down)
		        int attempts = 0;
		        boolean swipeDown = true; // Start with swipe down

		        while (attempts < maxAttempts) {
		            try {
		                // Try to get the element using the supplier
		                WebElement element = elementSupplier.get();
		                if (element.isDisplayed()) {
		                    element.click();
		                    return; // Exit once the element is found and clicked
		                }
		            } catch (NoSuchElementException e) {
		                // Element not found, perform a swipe
		                Sequence swipe = new Sequence(finger, 1);

		                if (swipeDown) {
		                    // Swipe Down: from 30% to 70% of screen height
		                    swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), width / 2, (int) (height * 0.3)));
		                    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		                    swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), width / 2, (int) (height * 0.7)));
		                    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		                } else {
		                    // Swipe Up: from 70% to 30% of screen height
		                    swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), width / 2, (int) (height * 0.7)));
		                    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		                    swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), width / 2, (int) (height * 0.3)));
		                    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		                }

		                driver.perform(java.util.Collections.singletonList(swipe));
		                attempts++;
		                swipeDown = !swipeDown; // Toggle swipe direction
		            }
		        }
		        throw new NoSuchElementException("Element not found after " + maxAttempts + " swipes.");
		    }


}
