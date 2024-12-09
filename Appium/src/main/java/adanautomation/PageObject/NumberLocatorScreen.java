package adanautomation.PageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.netty.handler.timeout.TimeoutException;

public class NumberLocatorScreen { 
	
		
	    AndroidDriver driver;
		
		WebDriverWait wait; 
		
		List<String[]> countries = new ArrayList<>();

		private AdsClass adsClass;

		private ImageRec imageRec;

		private PerformanceUtils performanceUtils;
		
		public NumberLocatorScreen(AndroidDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        this.adsClass = new AdsClass(driver); 
	        this.imageRec = new ImageRec(driver);
	        this.performanceUtils = new PerformanceUtils(driver);
	        
	        countries.addAll(Arrays.asList(
	            new String[]{"Pakistan", "3165299499"},
	            new String[]{"Oman", "95322261"},
	            new String[]{"Sri Lanka", "525023214"},
	            new String[]{"Thailand", "76042325"},
	            new String[]{"Indonesia", "273523615"},
	            new String[]{"Philippines", "551944582"}
	        ));
	    }
		
		@AndroidFindBy(id="com.mobile.number.location.call.number.locator.call.tracker:id/rlClickConsumer")
		
		private WebElement countrySe;
				
		@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.mobile.number.location.call.number.locator.call.tracker:id/textView_countryName' and @text='Pakistan (PK)']")
		private WebElement searchCountry;


		
		@AndroidFindBy(id="com.mobile.number.location.call.number.locator.call.tracker:id/editText_search")
		
		private WebElement enterCountryName;
		
		@AndroidFindBy(id="com.mobile.number.location.call.number.locator.call.tracker:id/textView_countryName")
		
		private WebElement selectCountry;
		
		@AndroidFindBy(id="com.mobile.number.location.call.number.locator.call.tracker:id/ed_number")
		
		private WebElement enterNumber;
		
		@AndroidFindBy(id="com.mobile.number.location.call.number.locator.call.tracker:id/btn_search")
		private WebElement searchButton ;
		
		@AndroidFindBy(id="com.mobile.number.location.call.number.locator.call.tracker:id/btn_ads")
		private WebElement rewardDialog;
		
		@AndroidFindBy(id="com.mobile.number.location.call.number.locator.call.tracker:id/progressBar")
		private WebElement progressBar ;
		
		@AndroidFindBy(xpath = "//*[@resource-id='com.mobile.number.location.call.number.locator.call.tracker:id/iv_back' or @resource-id='com.mobile.number.location.call.number.locator.call.tracker:id/iv_close' or @content-desc='Navigate up']")
		private WebElement backBtn;
		
		@AndroidFindBy(xpath = "")
		private WebElement contactListBtn;
		
	

		
		public WebElement getSearchCountry() {
	 		return searchCountry;
	 	}

		public void clickCountryFlag() {
			wait.until(ExpectedConditions.visibilityOf(countrySe));
			countrySe.click();

		}
		
		public void enterCountry() {
			
			wait.until(ExpectedConditions.visibilityOf(enterCountryName));
			enterCountryName.sendKeys("Pakistan");
	
		}
		
		public void clickCountry() {
			wait.until(ExpectedConditions.visibilityOf(selectCountry));
			selectCountry.click();

		}
		
		public void enterMobNumber() {
			wait.until(ExpectedConditions.visibilityOf(enterNumber));
			enterNumber.sendKeys("3165299499");
			
		}
		
		public void waitForBarGone() {
		    try {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		        wait.until(ExpectedConditions.invisibilityOf(progressBar)); 
		    } catch (TimeoutException e) {
		        System.out.println("Timeout: Progress bar is still visible after waiting.");
		    }
		}
		
		public void clickSearchBtn() {
			wait.until(ExpectedConditions.visibilityOf(searchButton));
			searchButton.click();
			
		}
		
		public void contineWithAd() {
			wait.until(ExpectedConditions.visibilityOf(rewardDialog));
			rewardDialog.click();
		}
		
		public void clickBack() {
			wait.until(ExpectedConditions.visibilityOf(backBtn));
			backBtn.click();
		}
		
	
		public void enterCountry(String countryName) {
		    wait.until(ExpectedConditions.visibilityOf(enterCountryName));
		    enterCountryName.sendKeys(countryName);  // Use the passed country name
		}

		public void enterMobNumber(String phoneNumber) {
		    wait.until(ExpectedConditions.visibilityOf(enterNumber));
		    enterNumber.sendKeys(phoneNumber);  // Use the passed phone number
		}
		
		
		public void processCountries() throws InterruptedException {
		    // Loop through each country in the list
		    for (String[] countryData : countries) {
		        String countryName = countryData[0];  // The country name
		        String phoneNumber = countryData[1];  // The phone number
		        
		      //  performanceUtils.logPerformanceMetrics("com.mobile.number.location.call.number.locator.call.tracker");
		        
		        
		        // Perform actions for each country
		        clickCountryFlag();
		        enterCountry(countryName);   // Pass country name dynamically
		        clickCountry();
		        enterMobNumber(phoneNumber); // Pass phone number dynamically
		        clickSearchBtn();
		        contineWithAd(); // Continue with any ad if present
		        waitForBarGone();  // Wait for progress bar to disappear
		        clickSearchBtn();
		        adsClass.dismissAdIfPresent();
		        imageRec.colapseAd(0.15);
		        clickBack();
		    }
		}



	
	
	

}
