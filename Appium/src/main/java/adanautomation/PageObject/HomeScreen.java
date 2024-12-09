package adanautomation.PageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindAll;



public class HomeScreen {
	
	    AndroidDriver  driver;
	    WebDriverWait wait;
	
	
	    public HomeScreen (AndroidDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this); 
		
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
		 
	}
	
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/cl_locator")
			private WebElement numberLocatorBtn;
		
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/cl_tracker")
			private WebElement phoneTrackerBtn;
		
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/cl_ip")
			private WebElement ipTrackerBtn;
		
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/cl_contacts")
			private WebElement contactsBtn;
		
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/cl_isd")
			private WebElement isdCodeBtn;
		
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/cl_std")
			private WebElement stdCodeBtn;
		
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/cl_caller_id")
			private WebElement callerIdBtn;
		
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/cl_blocker")
			private WebElement blockContactBtn;
		
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/ll_history")
			private WebElement historyBtn;
		
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/ll_backup")
			private WebElement backupBtn;
		
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/ll_settings")
			private WebElement settingsBtn;
		
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/iv_premium")
			private WebElement premiumIconBtn;
		
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/btn_upgrade")
			private WebElement premiumCardBtn;
		
			@AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/iv_offer")
			private WebElement giftCardBtn;
		
			@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Interstitial close button\"] | //android.widget.ImageView[@resource-id=\"com.mobile.number.location.call.number.locator.call.tracker:id/iv_close\"]")
			private WebElement interAdBtn;

			
			@AndroidFindBy(xpath = "//*[@resource-id='com.mobile.number.location.call.number.locator.call.tracker:id/iv_back' or @resource-id='com.mobile.number.location.call.number.locator.call.tracker:id/iv_close' or @content-desc='Navigate up']")
			private WebElement backBtn;

			@AndroidFindBy(xpath = "//*[@clickable='true']")
			private List<WebElement> checkUp;
			
			
			
			 public void checkScreenBtn() {
			        // Wait until the clickable elements are visible (waits for at least one clickable element)
//			        List<MobileElement> clickableElements = driver.findAndroidBy("//*[@clickable='true']");
			        wait.until(ExpectedConditions.visibilityOfAllElements(checkUp));

			        // Iterate through each clickable element to check if it's displayed and enabled
			        for (WebElement element : checkUp) {
			            if (element.isEnabled() && element.isDisplayed()) {
			                System.out.println("Element is clickable: " + element.getAttribute("resource-id"));
			            } else {
			                System.out.println("Element is not clickable or not visible: " + element.getAttribute("resource-id"));
			            }
			        }
			    }
			
			
			
			
			 	public WebElement getNumberLocatorBtn() {
			 		return numberLocatorBtn;
			 	}
			 	
			 	
				public void homeNumberLoctorBtn() {
				    wait.until(ExpectedConditions.visibilityOf(numberLocatorBtn));
				    numberLocatorBtn.click();
				}
				
			
				public WebElement getPhoneTrackerBtn() {
				    return phoneTrackerBtn;
				}
				public void homePhoneTrackerBtn() {
				    wait.until(ExpectedConditions.visibilityOf(phoneTrackerBtn));
				    phoneTrackerBtn.click();
				}

				
				public WebElement getHomeIpTrackerBtn() {
				    return ipTrackerBtn;
				}
				public void homeIpTrackerBtn() {
				    wait.until(ExpectedConditions.visibilityOf(ipTrackerBtn));
				    ipTrackerBtn.click();
				}
				

				public WebElement getHomeContactsBtn() {
				    return contactsBtn;
				}
				public void homeContactsBtn() {
				    wait.until(ExpectedConditions.visibilityOf(contactsBtn));
				    contactsBtn.click();
				}
				

				public WebElement getHomeIsdCodeBtn() {
				    return isdCodeBtn;
				}
				public void homeIsdCodeBtn() {
				    wait.until(ExpectedConditions.visibilityOf(isdCodeBtn));
				    isdCodeBtn.click();
				}
				

				public WebElement getHomeStdCodeBtn() {
				    return stdCodeBtn;
				}
				public void homeStdCodeBtn() {
				    wait.until(ExpectedConditions.visibilityOf(stdCodeBtn));
				    stdCodeBtn.click();
				}

				
				public WebElement getHomeCallerIdBtn() {
				    return callerIdBtn;
				}
				public void homeCallerIdBtn() {
				    wait.until(ExpectedConditions.visibilityOf(callerIdBtn));
				    callerIdBtn.click();
				}
				

				public WebElement getHomeBlockContactBtn() {
				    return blockContactBtn;
				}
				public void homeBlockContactBtn() {
				    wait.until(ExpectedConditions.visibilityOf(blockContactBtn));
				    blockContactBtn.click();
				}

			
				public void homeHistoryBtn() {
				    wait.until(ExpectedConditions.visibilityOf(historyBtn));
				    historyBtn.click();
				}
			
				public void homeBackupBtn() {
				    wait.until(ExpectedConditions.visibilityOf(backupBtn));
				    backupBtn.click();
				}
			
				public void homeSettingsBtn() {
				    wait.until(ExpectedConditions.visibilityOf(settingsBtn));
				    settingsBtn.click();
				}
			
				public void homePremiumIconBtn() {
				    wait.until(ExpectedConditions.visibilityOf(premiumIconBtn));
				    premiumIconBtn.click();
				}
			
				public void homePremiumCardBtn() {
				    wait.until(ExpectedConditions.visibilityOf(premiumCardBtn));
				    premiumCardBtn.click();
				}
				
			// later adjust with dates 
				public void homeGiftCardBtn() {
					try {
				    wait.until(ExpectedConditions.visibilityOf(giftCardBtn));
				    giftCardBtn.click();
				}catch (Exception e) {
					System.out.println("Gift icon is not available");
				}}

				
				
				public void pressBackBtn() {
				    wait.until(ExpectedConditions.visibilityOf(backBtn));
				    backBtn.click();
				}




				



	

	
	

}
