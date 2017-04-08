package config;

import java.util.concurrent.TimeUnit;
import static executionEngine.DriverScript.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import executionEngine.DriverScript;
import utility.Log;

public class ActionKeywords {
	public static WebDriver driver;
	// This block of code will decide which browser type to open
	public static void openBrowser(String object, String data) {
		try {
		Log.info("Openning Browser");
		if (data.equals("Mozilla")) {
			driver = new FirefoxDriver();
			Log.info("Mozilla browser started");
		}
		else if (data.equals("IE")) {
			driver = new InternetExplorerDriver();
			Log.info("IE browser started");
		}
		else if (data.equals("Chrome")) {
			driver = new ChromeDriver();
			Log.info("Chrome browser started");
		}
		} catch (Exception e) {
			Log.info("Failed to open Browser --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	public static void  navigate(String object, String data) {
		try {
		Log.info("Navigating to URL: " + Constants.URL);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(Constants.URL);
		} catch  (Exception e) {
			Log.info("Failed to navigate --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	public static void click(String object, String data) {
		try {
		Log.info("Clicking on web element: " + object);
		// This is to fetch using the css selector of the element from the Object Repository property file
		driver.findElement(By.cssSelector(OR.getProperty(object))).click();
		} catch (Exception e) {
			Log.info("Unable to click --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	public static void input(String object, String data) {
		try {
		Log.info("Entering the text in " + object);
		WebElement we = driver.findElement(By.cssSelector(OR.getProperty(object)));
		we.clear();
		we.sendKeys(data);
		} catch (Exception e) {
			Log.info("Failed to enter text in " + object + " --- " + e.getMessage());
			DriverScript.bResult = false;
			System.out.println(e.getMessage());
		}
	}
	
	public static void waitFor(String object, String data) throws InterruptedException {
		try {
		Log.info("Wait for 5 seconds");
		Thread.sleep(5000);
		} catch (Exception e) {
			Log.info("Unable to wait --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	public static void waitUntil(String object, String data) throws Exception {
		try {
		Log.info("Wait until element " + object + " is to be clickable");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(data)));
		} catch (Exception e) {
			Log.info("Exception occurred in waiting --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	public static void checkCheckbox(String object, String data) throws NoSuchElementException {
		try {
		Log.info("make sure checkbox - Same as billing address - is checked");
        WebElement chkbx = driver.findElement(By.cssSelector(OR.getProperty(object)));
        if (!chkbx.isSelected()) {
        	chkbx.click();
        }
		} catch (Exception e) {
			Log.info("Failed to check same as billing checkbox --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	public static void select(String object, String data) throws Exception {
		try {
		Log.info("select a country from checkout page");
        Select select = new Select(driver.findElement(By.cssSelector(OR.getProperty(object))));
        select.selectByVisibleText(data);
		} catch (Exception e) {
			Log.info("Failed to select from drop down list --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	public static void submitForm(String object, String data) throws Exception {
		try {
		Log.info("pick the first form and submit - i.e. Click Add to Cart button");
		driver.findElements(By.cssSelector(data)).get(0).submit();
		} catch (Exception e) {
			Log.info("Failed to add to cart --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
	
	public static void confirmOrder(String object, String data) throws Exception {
		Log.info("confirm if order was placed successfully");
		// wait for 7 seconds so as to load confirmation page		
		Thread.sleep(8000);
		if (! driver.getPageSource().contains(data)) {
			Log.info("Failed to place the order --- ");
			DriverScript.bResult = false;
		}
	}
	
	public static void closeBrowser(String object, String data) {
		try {
		Log.info("Closing the browser");
		driver.quit();
		} catch (Exception e) {
			Log.info("Failed to close browser --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}

}
