package adanautomation.PageObject;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v112.dom.DOM;
import org.openqa.selenium.devtools.v112.page.Page;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.devtools.DevTools;


public class AdsClass {

    AndroidDriver driver; 
    WebDriverWait wait;

    public AdsClass(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Interstitial Ad Close Button
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Interstitial close button'] | //android.widget.ImageView[@resource-id='com.mobile.number.location.call.number.locator.call.tracker:id/iv_close']")
    private WebElement interAdBtn;

    // Collapsible Ad (with a button to expand/collapse)
    @AndroidFindBy(id = "collapse-expand-button")
    private WebElement collapseExpandButton;

    // Native Ad Dismiss Button
    @AndroidFindBy(id = "success-validator-dismiss-btn")
    private WebElement dismissNativeAdBtn;
    
    @AndroidFindBy(xpath = "//android.widget.Button[@index='0' and @clickable='true' and @enabled='true']")
    private WebElement rewardDismiss;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/progressBar")
    private WebElement waitAdLoading;


    // Method to switch to WebView context
    private void switchToWebView() {
    	// Get all available contexts
    	Set<String> contextNames = driver.getContextHandles();
    	System.out.println("Available Contexts: " + contextNames);

    	// Loop through available contexts and switch to WebView if available
    	for (String contextName : contextNames) {
    	    if (contextName.contains("WEBVIEW_com.mobile.number.location.call.number.locator.call.tracker")) {  // Check if it's a WebView context
    	        driver.context("WEBVIEW_com.mobile.number.location.call.number.locator.call.tracker");  // Switch to WebView
    	        System.out.println("Switched to context: " + contextName);
    	        break;
    	    }
    	}

    	// Verify current context
    	System.out.println("Current Context: " + driver.getContext());

    }

    // Method to switch back to the native context
    private void switchToNative() {
        driver.context("NATIVE_APP"); // Switch back to the native context
    }
    
    
    public void monitorLogcat() {
        LogEntries logs = driver.manage().logs().get("logcat");

        for (LogEntry entry : logs) {
            String logMessage = entry.getMessage();
            if (logMessage.contains("Interstitial ad loaded")) {
                System.out.println("Ad is loaded");
            } else if (logMessage.contains("Ad shown")) {
                System.out.println("Ad displayed.");
                break;  // Exit loop once loading completes
            }
        }
    }
    
    public void handleAdLoadingWithProgressBar() {
        try {
            System.out.println("Waiting for 7 seconds to monitor logcat for ad loading...");

            // Wait for 7 seconds (progress bar delay)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
            boolean isProgressBarVisible = wait.until(ExpectedConditions.visibilityOf(waitAdLoading)) != null;

            if (isProgressBarVisible) {
                System.out.println("Progress bar is displayed, monitoring logcat...");

                // Monitor logcat for ad-related messages
                LogEntries logs = driver.manage().logs().get("logcat");
                boolean isAdLoadedFromLogcat = false;

                for (LogEntry entry : logs) {
                    String logMessage = entry.getMessage();
                    if (logMessage.contains("Interstitial ad loaded")) {
                        System.out.println("Logcat: Ad is loaded.");
                        
                        isAdLoadedFromLogcat = true;
                        break; // Ad is loaded, no need to check further
                    } else if (logMessage.contains("onAdFailedToLoad")) {
                        System.out.println("Logcat: Ad failed to load.");
                        break; // Ad failed to load, exit loop
                    }
                }

                // Check if progress bar disappears (indicating ad loading completion)
                boolean isAdLoadedFromProgressBar = wait.until(ExpectedConditions.invisibilityOf(waitAdLoading));

                if (isAdLoadedFromLogcat || isAdLoadedFromProgressBar) {
                    System.out.println("Ad loaded successfully.");
                    dismissInterstitialAdIfPresent();
                } else {
                    System.out.println("Ad did not load within 7 seconds. Proceeding to the next activity...");
                }
            } else {
                System.out.println("Progress bar is not displayed. No ad loading.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    
    
    

    // Method to dismiss the interstitial ad if present
    public void dismissInterstitialAdIfPresent() {
        try {
            WebDriverWait adWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            adWait.until(ExpectedConditions.visibilityOf(interAdBtn));
            interAdBtn.click();
            System.out.println("dismissing.");
        } catch (Exception e) {
            System.out.println("Interstitial ad was not displayed, proceeding without dismissing.");
        }
    }
    
    public void dismissReward() {
    	
    	try {
            WebDriverWait adWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            adWait.until(ExpectedConditions.visibilityOf(rewardDismiss));
            rewardDismiss.click();
        } catch (Exception e) {
            System.out.println("reward ad was not displayed, proceeding without dismissing.");
        }
	}
    
    public void dismissAdIfPresent() {
        try {
            WebDriverWait adWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            
            // Check for interstitial ad
            if (isElementVisible(adWait, interAdBtn)) {
                System.out.println("Interstitial ad is displayed. Attempting to dismiss...");
                interAdBtn.click();
            }
            // Check for reward ad
            else if (isElementVisible(adWait, rewardDismiss)) {
                System.out.println("Reward ad is displayed. Attempting to dismiss...");
                rewardDismiss.click();
            } else {
                System.out.println("No ad was displayed.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while dismissing the ad: " + e.getMessage());
        }
    }

    // Helper method to check element visibility
    private boolean isElementVisible(WebDriverWait wait, WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
   
     
    public void logWebViewElements() {
        switchToWebView();

        try {
            // Fetch all elements in the WebView
            List<WebElement> elements = driver.findElements(new By.ByCssSelector("#success-validator-dismiss-btn"));
            for (WebElement element : elements) {
                System.out.println("Tag Name: " + element.getTagName() + " | Text: " + element.getText());
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch elements: " + e.getMessage());
        } finally {
            // Switch back to native context to ensure cleanup
            switchToNative();
        }
    }

    // Method to dismiss the collapsible ad if present
//    public void dismissCollapsibleAdIfPresent() {
//    	AndroidElement dismissButton = driver.findElementByAndroidUIAutomator(
//                "new UiSelector().textContains(\"Dismiss\")");
//    }


    // Method to dismiss the native ad if present
    public void dismissNativeAdIfPresent() {
        try {
            switchToWebView(); // Switch to WebView context
            WebDriverWait adWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            adWait.until(ExpectedConditions.visibilityOf(dismissNativeAdBtn));
            dismissNativeAdBtn.click(); // Dismiss the native ad
        } catch (Exception e) {
            System.out.println("Native ad was not displayed, proceeding without dismissing.");
        } finally {
            switchToNative(); // Switch back to native context
        }
    }
}
