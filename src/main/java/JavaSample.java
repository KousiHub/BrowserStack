// Sample test in Java to run Automate session.
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.net.URL;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class JavaSample {
    public static final String AUTOMATE_USERNAME = "kousalyasarathy1";
    public static final String AUTOMATE_ACCESS_KEY = "djZh2ghLVGQAjreuvt1Q";
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static void main(String[] args) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "chrome");
        //caps.setCapability("device", "iPhone 11");
        //caps.setCapability("realMobile", "true");
        //caps.setCapability("os_version", "14.0");
        caps.setCapability("name", "Sample Test"); // test name
        caps.setCapability("build", "Build Number 1"); // CI/CD job or build name
        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        driver.get("https://spree-vapasi.herokuapp.com/");
        Thread.sleep(1000);
        //wait.until(ExpectedConditions.elementToBeClickable(By.id("link-to-login")));
        driver.findElement(By.id("link-to-login")).click();
        driver.findElement(By.id("spree_user_email")).sendKeys("kousi@gmail.com");
        driver.findElement(By.id("spree_user_password")).sendKeys("Samsung@1");
        driver.findElement(By.cssSelector("input[name ='commit']")).click();
        //element.sendKeys("BrowserStack");
        //element.submit();
        // Setting the status of test as 'passed' or 'failed' based on the condition; if title of the web page contains 'BrowserStack'
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            //driver.findElement(By.name("commit")).click();
            wait.until(ExpectedConditions.titleContains("Spree Demo Site"));
            markTestStatus("passed","Yaay Logged In!",driver);
        }
        catch(Exception e) {
            markTestStatus("failed","Naay Better luck next time!",driver);
        }
        //System.out.println(driver.getTitle());
        driver.quit();
    }
    // This method accepts the status, reason and WebDriver instance and marks the test on BrowserStack
    public static void markTestStatus(String status, String reason, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+status+"\", \"reason\": \""+reason+"\"}}");
    }
} 