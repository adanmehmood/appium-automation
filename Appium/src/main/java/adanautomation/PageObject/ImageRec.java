package adanautomation.PageObject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSTouchAction;
import nu.pattern.OpenCV;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opencv.core.*;
import org.opencv.features2d.BFMatcher;
import org.opencv.features2d.ORB;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.apache.log4j.Logger;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageRec {
    private static final Logger logger = Logger.getLogger(ImageRec.class);

    private AndroidDriver driver;
    private WebDriverWait wait;
    	// openCV driver and library
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // OpenCV initialization
        
        System.load("C:\\Users\\INDUS\\Desktop\\opencv\\build\\java\\x64\\opencv_java490.dll");

        
    }

    public ImageRec(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
    }
    
    
 // Function to perform a tap action at given coordinates
  


    // Capture screenshot of the current screen
    public void captureScreenshot(String screenshotPath) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
            FileUtils.copyFile(screenshot, new File(screenshotPath));
        } catch (Exception e) {
            logger.error("Error capturing screenshot: ", e);
        }
    }

    // Perform ORB image matching and tapping
    private void performImageMatchingAndTap(String imagePath, String screenshotPath) {
        try {
            // Load the screenshot and template images
            Mat screenMat = Imgcodecs.imread(screenshotPath);
            Mat template = Imgcodecs.imread(imagePath);

            if (screenMat.empty() || template.empty()) {
                logger.error("Error: One of the images could not be loaded. Check paths.");
                return;
            }

            // Convert images to grayscale
            if (screenMat.channels() > 1) {
                Imgproc.cvtColor(screenMat, screenMat, Imgproc.COLOR_BGR2GRAY);
            }
            if (template.channels() > 1) {
                Imgproc.cvtColor(template, template, Imgproc.COLOR_BGR2GRAY);
            }

            // Feature Matching with ORB
            ORB orb = ORB.create();
            MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
            MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
            Mat descriptors1 = new Mat();
            Mat descriptors2 = new Mat();

            orb.detectAndCompute(screenMat, new Mat(), keypoints1, descriptors1);
            orb.detectAndCompute(template, new Mat(), keypoints2, descriptors2);

            BFMatcher matcher = BFMatcher.create(BFMatcher.BRUTEFORCE_HAMMING);
            MatOfDMatch matOfMatches = new MatOfDMatch();
            matcher.match(descriptors1, descriptors2, matOfMatches);

            // Filter good matches
            List<DMatch> matches = matOfMatches.toList();
            double minDist = 100;
            for (DMatch match : matches) {
                double dist = match.distance;
                if (dist < minDist) minDist = dist;
            }

            List<DMatch> goodMatches = new ArrayList<>();
            for (DMatch match : matches) {
                if (match.distance <= Math.max(2 * minDist, 30.0)) {
                    goodMatches.add(match);
                }
            }

            // Check if good matches were found
            if (goodMatches.isEmpty()) {
                logger.error("No good matches found.");
                return;
            }

            // Calculate average position of good matches
            int x = 0, y = 0;
            for (DMatch match : goodMatches) {
                Point pt = keypoints1.toList().get(match.queryIdx).pt;
                x += pt.x;
                y += pt.y;
            }
            x /= goodMatches.size();
            y /= goodMatches.size();

            logger.info("Tap coordinates: x=" + x + ", y=" + y);

            // Perform tap action
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tap = new Sequence(finger, 1);
            tap.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), x, y));
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(new Pause(finger, Duration.ofMillis(100)));
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Arrays.asList(tap));

        } catch (Exception e) {
            logger.error("Error performing image matching and tap: ", e);
        }
    }

    // Function to find and tap on an image (with ORB feature matching)
    public void findAndTapImageWithORB(String imagePath) {
        String screenshotPath = "src/test/resources/screenshots/screenshot.png";
        captureScreenshot(screenshotPath);
        performImageMatchingAndTap(imagePath, screenshotPath);
    }

    // Function to find and tap a "Dismiss" button with text detection
    public void findAndTapButtonWithText(String imagePath) {
        String screenshotPath = "src/test/resources/screenshots/screenshot.png";
        captureScreenshot(screenshotPath);
        performImageMatchingAndTap(imagePath, screenshotPath);

        // OCR for text detection (if needed for "Dismiss" button)
        try {
            Mat screenMat = Imgcodecs.imread(screenshotPath);
            String detectedText = performOCR(screenMat);

            if (detectedText.contains("Dismiss")) {
                logger.info("'Dismiss' button detected.");
                
                
                
                // Tap logic is already handled by ORB image matching, so no need to duplicate
            } else {
                logger.info("No 'Dismiss' text detected.");
            }
        } catch (Exception e) {
            logger.error("Error performing OCR for 'Dismiss' detection: ", e);
        }
    }

    // Perform OCR using Tesseract (optional step for text detection)
    private String performOCR(Mat image) {
        // You would need to integrate Tesseract OCR here
        // Return a dummy string for now
        return "Dismiss";
    }

/// this using for dismiss button
    public void performImageMatchingAndCenterTap(String imagePath, String screenshotPath) {
        try {
        	
        	captureScreenshot(screenshotPath);
        	
        	
            // Load the screenshot and template images
            Mat screenMat = Imgcodecs.imread(screenshotPath);
            Mat template = Imgcodecs.imread(imagePath);

            if (screenMat.empty() || template.empty()) {
                logger.error("Error: One of the images could not be loaded. Check paths.");
                return;
            }

            // Convert images to grayscale
            if (screenMat.channels() > 1) {
                Imgproc.cvtColor(screenMat, screenMat, Imgproc.COLOR_BGR2GRAY);
            }
            if (template.channels() > 1) {
                Imgproc.cvtColor(template, template, Imgproc.COLOR_BGR2GRAY);
            }

            // Match the template in the screenshot
            Mat result = new Mat();
            Imgproc.matchTemplate(screenMat, template, result, Imgproc.TM_CCOEFF_NORMED);

            // Find the best match location
            Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
            Point matchLoc = mmr.maxLoc;

            // Calculate the center of the matched area
            int centerX = (int) (matchLoc.x + template.width() / 2);
            int centerY = (int) (matchLoc.y + template.height() / 2);

            logger.info("Detected button at coordinates: x=" + centerX + ", y=" + centerY);

            // Draw a rectangle around the matched area (optional)
            Imgproc.rectangle(screenMat, matchLoc, new Point(matchLoc.x + template.width(), matchLoc.y + template.height()), new Scalar(0, 255, 0), 2);

            // Save the match result as a new image
            String resultImagePath = "src/test/resources/screenshots/match_result.png";  // Path to save the result image
            Imgcodecs.imwrite(resultImagePath, screenMat);
            logger.info("Match result saved at: " + resultImagePath);

            // Perform tap action at the center of the matched button
            performTap(centerX, centerY);

        } catch (Exception e) {
            logger.error("Error performing image matching and tap: ", e);
        }
    }
    
    
    public void dismissVal(double percentage) throws InterruptedException {
    	Thread.sleep(3000);
    	
    	String imagePath = "src\\test\\java\\resources\\images\\gggggj.PNG";
  	    String ssPath = "src/test/resources/screenshots/screenshot.png";
  	  performImageMatchingAndCenterByPer(imagePath ,ssPath ,percentage ); 
    }

 private void performTap(int x, int y) {
    try {
        logger.info("Performing tap at coordinates: x=" + x + ", y=" + y);

        // Create a PointerInput instance for simulating touch
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Create a sequence of actions to perform a tap
        Sequence tap = new Sequence(finger, 1);

        // Move the pointer to the specified coordinates
        tap.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), x, y));

        // Press down the pointer (simulate touch down)
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // Short pause before releasing the touch (simulate click duration)
        tap.addAction(new Pause(finger, Duration.ofMillis(100)));

        // Release the pointer (simulate touch up)
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the actions
        driver.perform(Arrays.asList(tap));

        logger.info("Tap performed at coordinates: x=" + x + ", y=" + y);
    } catch (Exception e) {
        logger.error("Error while performing tap: ", e);
    }
}




 /// this using for the top 25% of screen
 public void performImageMatchingAndCenterByPer(String imagePath, String screenshotPath, double percentage) {
    try {
        // Capture a screenshot
        captureScreenshot(screenshotPath);

        // Load the screenshot and template images
        Mat screenMat = Imgcodecs.imread(screenshotPath);
        Mat template = Imgcodecs.imread(imagePath);

        if (screenMat.empty() || template.empty()) {
            logger.error("Error: One of the images could not be loaded. Check paths.");
            return;
        }

        // Crop the screenshot to the top portion (e.g., top 25% of the height)
        int cropHeight = (int) (screenMat.rows() * percentage); // Adjust the percentage as needed
        Rect roi = new Rect(0, 0, screenMat.cols(), cropHeight);
        Mat croppedScreenMat = new Mat(screenMat, roi);

        // Convert images to grayscale
        if (croppedScreenMat.channels() > 1) {
            Imgproc.cvtColor(croppedScreenMat, croppedScreenMat, Imgproc.COLOR_BGR2GRAY);
        }
        if (template.channels() > 1) {
            Imgproc.cvtColor(template, template, Imgproc.COLOR_BGR2GRAY);
        }

        // Match the template in the cropped screenshot
        Mat result = new Mat();
        Imgproc.matchTemplate(croppedScreenMat, template, result, Imgproc.TM_CCOEFF_NORMED);

        // Find the best match location
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
        Point matchLoc = mmr.maxLoc;
        double matchValue = mmr.maxVal; // Similarity score of the match

        // Define the threshold for a valid match (e.g., 0.8)
        double threshold = 0.1;
        if (matchValue < threshold) {
            logger.error("No sufficient match found at the top of the screen. Max match value: " + matchValue);
            return;
        }

        // Calculate the center of the matched area in the original screen
        int centerX = (int) (matchLoc.x + template.width() / 2);
        int centerY = (int) (matchLoc.y + template.height() / 2);

        logger.info("Detected button at coordinates: x=" + centerX + ", y=" + centerY);

        // Draw a rectangle around the matched area (optional)
        Imgproc.rectangle(screenMat, matchLoc, new Point(matchLoc.x + template.width(), matchLoc.y + template.height()), new Scalar(0, 255, 0), 2);

        // Save the match result as a new image
        String resultImagePath = "src/test/resources/screenshots/match_result.png"; // Path to save the result image
        Imgcodecs.imwrite(resultImagePath, screenMat);
        logger.info("Match result saved at: " + resultImagePath);

        // Perform tap action at the center of the matched button
        performTap(centerX, centerY);

    } catch (Exception e) {
        logger.error("Error performing image matching and tap: ", e);
    }
}

 
 public void colapseAd(double percentage) throws InterruptedException {
	 
	    Thread.sleep(5000);
	    
	    String imagePath = "src/test/resources/screenshots/coladClose.PNG";
	    String screenshotPath = "src/test/resources/screenshots/screenshot.png";
	    performImageMatchingAndCenterByPer(imagePath, screenshotPath, percentage);
	}
 
}
