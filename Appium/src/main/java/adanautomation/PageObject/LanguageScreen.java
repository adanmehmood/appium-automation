package adanautomation.PageObject;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LanguageScreen {
	AndroidDriver driver;
	
	WebDriverWait wait; 
	
	public LanguageScreen(AndroidDriver driver) {
		
		
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
	}
	
	@AndroidFindBy(id="com.mobile.number.location.call.number.locator.call.tracker:id/confirmButton")
	
	private WebElement languageConfirm;
	
	
	public void confirmLanguage(){
		wait.until(ExpectedConditions.visibilityOf(languageConfirm));
		languageConfirm.click();
	}
	
	
}

