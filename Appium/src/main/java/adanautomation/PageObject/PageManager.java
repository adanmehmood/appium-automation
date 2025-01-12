//package adanautomation.PageObject;
//
//import io.appium.java_client.android.AndroidDriver;
//
//public class PageManager {
//    private AndroidDriver driver;
//    
//    private LanguageScreen languageScreen;
//    private SplashScreen splashScreen;
//
//    public PageManager(AndroidDriver driver) {
//        this.driver = driver;
//    }
//
//    public LanguageScreen getLanguageScreen() {
//        if (languageScreen == null) {
//            languageScreen = new LanguageScreen(driver);
//        }
//        return languageScreen;
//    }
//
//    public SplashScreen getSplashScreen() {
//        if (splashScreen == null) {
//            splashScreen = new SplashScreen(driver);
//        }
//        return splashScreen;
//    }
//}

package adanautomation.PageObject;

import adanautomation.utils.MobileGestures;
import adanautomation.utils.PermissionsUtils;
import io.appium.java_client.android.AndroidDriver;

public class PageManager {
    private AndroidDriver driver;

    private LanguageScreen languageScreen;
    private SplashScreen splashScreen;
    private MobileGestures mobileGesture;
    private HomeScreen homeScreen;
    private PremiumScreen premiumScreen;
    private AdsClass adsClass;
    private ImageRec imageRec;
    private NumberLocatorScreen numberLocatorScreen;
    private PerformanceUtils performanceUtils;
    private PermissionsUtils permissionsUtils;
   

    public PageManager(AndroidDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null"); 
        }
        this.driver = driver;
        
   
    }
    
    public PermissionsUtils getPermissionsUtils() {
    	if (permissionsUtils == null) {
    		permissionsUtils = new PermissionsUtils(driver);
    	}
    	return permissionsUtils;
    }
    
    
    public PerformanceUtils getPerformanceUtils() {
    	if (performanceUtils == null) {
    		performanceUtils = new PerformanceUtils(driver);
    	}
    	return performanceUtils;
    }
    
    public NumberLocatorScreen getNumberLocatorScreen() {
    	if (numberLocatorScreen == null) {
    		numberLocatorScreen = new NumberLocatorScreen(driver);
    	}
		return numberLocatorScreen;
    }
    
    
    public ImageRec getImageRec() {
    	if (imageRec == null) {
    		imageRec = new ImageRec(driver);
    	}
		return imageRec;
    }
    
    
    public PremiumScreen getPremiumScreen() {
    	if (premiumScreen == null) {
    		premiumScreen = new PremiumScreen(driver);
    	}
		return premiumScreen;
    }
    
    public AdsClass getAdsClass() {
    	if (adsClass == null) {
    		adsClass = new AdsClass(driver);
    	}
		return adsClass;
    }
    
    public HomeScreen getHomeScreen() {
    	if (homeScreen == null) {
    		homeScreen = new HomeScreen(driver);
    	}
		return homeScreen;
    }

    public LanguageScreen getLanguageScreen() {
        if (languageScreen == null) {
            languageScreen = new LanguageScreen(driver);
        }
        return languageScreen;
    }

    public SplashScreen getSplashScreen() {
        if (splashScreen == null) {
            splashScreen = new SplashScreen(driver);
        }
        return splashScreen;
    }
    
    public MobileGestures getGestureManager() {
        if (mobileGesture == null) {
        	mobileGesture = new MobileGestures(driver);
        }
        return mobileGesture;
    }
    
    public void clearScreens() {
        languageScreen = null;
        splashScreen = null;
        mobileGesture = null; // Clear the gesture manager if needed
        homeScreen = null;
    }
}

