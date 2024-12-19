package adanautomation.PageObject;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ContactBackUpScreen {
	
	
	private AndroidDriver driver;
    private WebDriverWait wait;

   
    public ContactBackUpScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
    }
    
    @AndroidFindBy(id ="com.mobile.number.location.call.number.locator.call.tracker:id/cv_backup")
    private WebElement backUpBtn;
    
    @AndroidFindBy(id ="com.mobile.number.location.call.number.locator.call.tracker:id/cv_restore")
    private WebElement restoreBtn;
    
    @AndroidFindBy(id ="com.mobile.number.location.call.number.locator.call.tracker:id/iv_premium")
    private WebElement premiumBtn;
    
    @AndroidFindBy(id ="com.mobile.number.location.call.number.locator.call.tracker:id/tv_count")
    private WebElement totalContacts;
    
    @AndroidFindBy(id ="com.mobile.number.location.call.number.locator.call.tracker:id/tv_synced")
    private WebElement syncedContact;
    
    @AndroidFindBy(id ="com.mobile.number.location.call.number.locator.call.tracker:id/tv_unsynced")
    private WebElement unSyncedContact;
    
    @AndroidFindBy(id ="com.mobile.number.location.call.number.locator.call.tracker:id/tv_percentage")
    private WebElement percentage;
    
    @AndroidFindBy(id ="com.mobile.number.location.call.number.locator.call.tracker:id/progress_circular")
    private WebElement percentageBar;
    
    @AndroidFindBy(id ="com.mobile.number.location.call.number.locator.call.tracker:id/btn_go")
    private WebElement goPremiumBtn;
    
    @AndroidFindBy(id ="com.mobile.number.location.call.number.locator.call.tracker:id/tv_exit")
    private WebElement noThanksBtn;
    
    @AndroidFindBy (id ="com.mobile.number.location.call.number.locator.call.tracker:id/iv_close")
    private WebElement closePremiumScreen;

}
