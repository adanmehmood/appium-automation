package adanautomation.PageObject;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PremiumScreen {
	
	AndroidDriver  driver;
    WebDriverWait wait;


    public PremiumScreen (AndroidDriver driver) {
	
	this.driver = driver;
	PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	
	this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	 
}
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/tv_continue_ads")
    private WebElement continuePremiumWithAdBtn;

    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/iv_close")
    private WebElement closePremiumDialogBtn;
    
    
    public void clickContinuePremiumWithAd() {
        wait.until(ExpectedConditions.visibilityOf(continuePremiumWithAdBtn));
        continuePremiumWithAdBtn.click();
    }

    public void closePremiumDialog() {
        wait.until(ExpectedConditions.visibilityOf(closePremiumDialogBtn));
        closePremiumDialogBtn.click();
    }


}
