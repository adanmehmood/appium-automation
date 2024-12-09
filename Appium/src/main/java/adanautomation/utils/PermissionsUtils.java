package adanautomation.utils;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PermissionsUtils {
	
	private AndroidDriver driver;
    private WebDriverWait wait;

   
    public PermissionsUtils(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/btn_allow")
	private WebElement customContactAllowBtn;
    
    @AndroidFindBy(id= "com.android.packageinstaller:id/permission_allow_button")
    private WebElement androidAllowBtn;
    
    
    
    public void allowContactDialogPermission() {
    	
    	wait.until(ExpectedConditions.visibilityOf(customContactAllowBtn));
    	customContactAllowBtn.click();
    }
    
public void allowContactAndroidPermission() {
    	
    	wait.until(ExpectedConditions.visibilityOf(androidAllowBtn));
    	androidAllowBtn.click();
    }
    
    
    
    
    

}
