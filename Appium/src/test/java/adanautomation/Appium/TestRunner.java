
package adanautomation.Appium;

import adanautomation.PageObject.ImageRec;
import adanautomation.PageObject.PageManager;
import adanautomation.PageObject.PerformanceUtils;
import adanautomation.utils.MobileGestures;

import java.net.MalformedURLException;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestRunner extends BaseTest {
	
//    private PageManager pageManager;
//    private MobileGestures mobileGestures;
    
    
   

//    @BeforeClass
//    public void setUp() throws MalformedURLException {
//    	
//    	if (driver == null) {
//            throw new IllegalStateException("Driver is not initialized. Check AppiumMain setup.");
//        }
//        // Initialize PageManager with the driver
//        pageManager = new PageManager(driver);
//        mobileGestures = new MobileGestures(driver);
//       // ImageRec imageRec = new ImageRec(driver);
//        
//        
//    }
    
    private ThreadLocal<PageManager> pageManager = new ThreadLocal<>();
    private ThreadLocal<MobileGestures> mobileGestures = new ThreadLocal<>();

    @BeforeMethod
    public void setUpPageObjects() {
        pageManager.set(new PageManager(AppiumMain.getDriver()));
        mobileGestures.set(new MobileGestures(AppiumMain.getDriver()));
    }
    
    @Test
    public void NumberSearchFeature() throws InterruptedException {
    	
    	  mobileGestures.get().scrollAndClick(() ->
    	  pageManager.get().getHomeScreen().getNumberLocatorBtn());
          pageManager.get().getAdsClass().dismissInterstitialAdIfPresent();
          pageManager.get().getNumberLocatorScreen().processCountries();
	
//    	mobileGestures.scrollAndClick(() -> 
//    	pageManager.getHomeScreen().getNumberLocatorBtn());
//        pageManager.getAdsClass().dismissInterstitialAdIfPresent(); 
//        pageManager.getNumberLocatorScreen().processCountries();
//        pageManager.getPerformanceUtils().getPerformanceData();
// 
    } 
    
  /*  @Test
    public void Example() throws InterruptedException {
       pageManager.getPermissionsUtils().allowContactAndroidPermission();
       pageManager.getAdsClass().monitorLogcat();
    }

    
    
    @Test
    public void TrackerAndGeoFence() throws InterruptedException {
    	mobileGestures.scrollAndClick(() -> 
    	pageManager.getHomeScreen().getPhoneTrackerBtn());
    	pageManager.getPermissionsUtils().allowContactAndroidPermission();
        pageManager.getAdsClass().handleAdLoadingWithProgressBar();
        pageManager.getImageRec().colapseAd(0.25);
        
        pageManager.getHomeScreen().pressBackBtn();
         
    } 
    
     @Test
    public void NumberSearchFeature() throws InterruptedException {
	
    	mobileGestures.scrollAndClick(() -> 
    	pageManager.getHomeScreen().getNumberLocatorBtn());
        pageManager.getAdsClass().dismissInterstitialAdIfPresent(); 
        pageManager.getNumberLocatorScreen().processCountries();
        pageManager.getPerformanceUtils().getPerformanceData();
 
    } 
   
    

    @Test
    public void SplashTest() {
//         Access SplashScreen via PageManager
        pageManager.getSplashScreen().waitForProgressBar();
        pageManager.getSplashScreen().continueToApp();
       
        pageManager.getLanguageScreen().confirmLanguage();
       // pageManager.getSplashScreen().dismissAdByCoordinates();
        pageManager.getGestureManager().swipeLeft(5);
       // pageManager.getImageRec().dismissVal(0.45);
//        pageManager.getSplashScreen().dismissAdByCoordinates(); // AD VALIDATOR
        pageManager.getSplashScreen().clickNextButton();
        pageManager.getPremiumScreen().clickContinuePremiumWithAd();
            
}
    @Test
    public void HomeScreenTest() {
        //pageManager.getPremiumScreen().closePremiumDialog();
    	// this is for the phone tracker feature....
    	mobileGestures.scrollAndClick(() -> 
    	pageManager.getHomeScreen().getPhoneTrackerBtn());
        pageManager.getAdsClass().dismissInterstitialAdIfPresent();
        //pageManager.getImageRec().colapseAd(0.25);
        pageManager.getHomeScreen().pressBackBtn();
    	
        //this is for Number Locator Feature.....
    	mobileGestures.scrollAndClick(() -> 
    	pageManager.getHomeScreen().getNumberLocatorBtn());
    	pageManager.getAdsClass().dismissInterstitialAdIfPresent();
        pageManager.getHomeScreen().pressBackBtn();
        
        //this is for IP tracker Feature......
        mobileGestures.scrollAndClick(() ->
        pageManager.getHomeScreen().getHomeIpTrackerBtn()); 
        pageManager.getAdsClass().dismissInterstitialAdIfPresent();
        pageManager.getHomeScreen().pressBackBtn();
        
        
        mobileGestures.scrollAndClick(() ->
        pageManager.getHomeScreen().getHomeContactsBtn());
        pageManager.getAdsClass().dismissInterstitialAdIfPresent();
        pageManager.getHomeScreen().pressBackBtn();
        
        mobileGestures.scrollAndClick(() ->
        pageManager.getHomeScreen().getHomeIsdCodeBtn());
        pageManager.getAdsClass().dismissInterstitialAdIfPresent();
        pageManager.getHomeScreen().pressBackBtn();
        
        mobileGestures.scrollAndClick(() ->
        pageManager.getHomeScreen().getHomeStdCodeBtn());
        pageManager.getAdsClass().dismissInterstitialAdIfPresent();
        pageManager.getHomeScreen().pressBackBtn();
        
        mobileGestures.scrollAndClick(() ->
        pageManager.getHomeScreen().getHomeCallerIdBtn());
        pageManager.getHomeScreen().pressBackBtn();
        
        mobileGestures.scrollAndClick(() ->
        pageManager.getHomeScreen().getHomeBlockContactBtn());
        pageManager.getHomeScreen().pressBackBtn();
        
        pageManager.getHomeScreen().homeHistoryBtn();
        pageManager.getHomeScreen().pressBackBtn();
        
        pageManager.getHomeScreen().homeBackupBtn();
        pageManager.getHomeScreen().pressBackBtn();
        
        pageManager.getHomeScreen().homeSettingsBtn();
        pageManager.getHomeScreen().pressBackBtn();
        
//        pageManager.getHomeScreen().homePremiumIconBtn();
//        pageManager.getHomeScreen().pressBackBtn();
        
//        pageManager.getHomeScreen().homePremiumCardBtn();
//        pageManager.getHomeScreen().pressBackBtn();
        
//        pageManager.getHomeScreen().homeGiftCardBtn();
//        pageManager.getHomeScreen().pressBackBtn();
//        
      
        
        
    }*/

}

