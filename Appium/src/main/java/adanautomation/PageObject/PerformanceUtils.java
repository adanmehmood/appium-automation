package adanautomation.PageObject;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PerformanceUtils {

    private AndroidDriver driver;
    private WebDriverWait wait;

    // Constructor
    public PerformanceUtils(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Creates the arguments for the getPerformanceData command.
     * 
     * @param packageName The package name of the app.
     * @param dataType The type of performance data.
     * @return A map containing the arguments.
     */
    private Map<String, Object> createPerformanceArgs(String packageName, String dataType) {
        Map<String, Object> args = new HashMap<>();
        args.put("packageName", packageName);
        args.put("dataType", dataType);
        return args;
    }

    /**
     * Executes the mobile:getPerformanceData command.
     * 
     * @param args The arguments for the command.
     * @return Performance data as a list of rows.
     */
    private List<List<Object>> executePerformanceCommand(Map<String, Object> args) {
        return (List<List<Object>>) driver.executeScript("mobile: getPerformanceData", args);
    }

    /**
     * Fetches performance data for the specified package and subsystem.
     * 
     * @param packageName The package name of the app.
     * @param dataType The type of performance data.
     * @return Performance data as a list of rows.
     */
    public List<List<Object>> getPerformance(String packageName, String dataType) {
        try {
            // Create arguments
            Map<String, Object> args = createPerformanceArgs(packageName, dataType);

            // Execute the performance command
            List<List<Object>> performanceData = executePerformanceCommand(args);

            // Log the performance data
            logPerformanceData(dataType, performanceData);

            return performanceData;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch performance data: " + e.getMessage());
        }
    }

    /**
     * Logs performance data to the console.
     * 
     * @param dataType The type of performance data.
     * @param performanceData The performance data to log.
     */
    private void logPerformanceData(String dataType, List<List<Object>> performanceData) {
        System.out.println("Performance Data (" + dataType + "): " + performanceData);
    }

    /**
     * Fetches and logs performance data for CPU, memory, and battery.
     */
    public void getPerformanceData() {
        String packageName = "com.mobile.number.location.call.number.locator.call.tracker"; // Replace with your app's package name

        // Fetch CPU data
        String cpuDataType = "cpuinfo";
        List<List<Object>> cpuPerformanceData = getPerformance(packageName, cpuDataType);
       // System.out.println("CPU Performance Data: " + cpuPerformanceData);

        // Fetch memory data
        String memoryDataType = "memoryinfo";
        List<List<Object>> memoryPerformanceData = getPerformance(packageName, memoryDataType);
       // System.out.println("Memory Performance Data: " + memoryPerformanceData);

        // Fetch battery data
        String batteryDataType = "batteryinfo";
        List<List<Object>> batteryPerformanceData = getPerformance(packageName, batteryDataType);
        //System.out.println("Battery Performance Data: " + batteryPerformanceData);
    }
}
