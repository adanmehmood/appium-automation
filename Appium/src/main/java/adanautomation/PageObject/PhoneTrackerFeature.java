package adanautomation.PageObject;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PhoneTrackerFeature {
	
	
	
	private AndroidDriver driver;
    private WebDriverWait wait;

   
    public PhoneTrackerFeature(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    
    // choose Device Type Screen
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/osm_map_view")
    private WebElement osmMap;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/cl_tracker")
	private WebElement trackAnyDeiveBtn;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/cl_located")
	private WebElement shareMyLocation;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/iv_location")
	private WebElement myLocationBtn;
    
    
    // track any phone screen
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/ed_username")
	private WebElement enterUserName;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/btn_done")
	private WebElement dialogDoneBtn;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/relativeLayout")
	private WebElement congDialog;
  
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/tv_add_empty")
	private WebElement addDevicebtn;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/iv_up")
	private WebElement sheetUpBtn;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/iv_switch")
	private WebElement chooseDeviceScreenbtn;
    
    // be located screen
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/cl_generate")
	private WebElement generatCodeBtn;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/btn_copy")
	private WebElement copyCodeBtn;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/btn_share")
	private WebElement shareCodeBtn;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/view10")
	private WebElement trackrtListUp;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/bottomSheet")
    private WebElement bottomSheet;
    
    
    // Geo fence 
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/tv_geo_fencing")
    private WebElement geoFenceBtn;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/iv_empty")
    private WebElement emptyBox;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/btn_add")
    private WebElement addFence;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/constraintLayout9")
    private WebElement createFence;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/ed_username")
    private WebElement enterFenceName;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/btn_done")
    private WebElement doneFenceBtn;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/seekBarRadius")
    private WebElement seekBar;
    
    @AndroidFindBy(xpath = "//android.view.View[@resource-id=\"com.mobile.number.location.call.number.locator.call.tracker:id/mapView\"]")
    private WebElement mapBoxMap;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/btn_done")
    private WebElement saveFenceBtn;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/tv_radius")
    private WebElement fenceRadius;
    
    @AndroidFindBy(id = "com.mobile.number.location.call.number.locator.call.tracker:id/btn_exit")
    private WebElement exitBtn;


    
    
    
    
    
    
    
    
    
    
    
   

}
