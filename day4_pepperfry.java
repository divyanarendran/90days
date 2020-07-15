package days;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

	/*1) Go to https://www.pepperfry.com/
2) Mouseover on Furniture and click Office Chairs under Chairs
3) click Executive Chairs
4) Change the minimum Height as 50 in under Dimensions
5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
6) Mouseover on Furniture and Click Office tables
7) Select Executive Desks
8) Select Price between 20000 to 40000 rs
9) Add "Executive Office Table in Brown Color" to Wishlist
10) Verify the number of items in Wishlist
11) Navigate to Wishlist
12) Get the offer Price and Coupon Code for Executive Office Table in Brown Color
13) Move Executive Office Table in Brown Color only to Cart from Wishlist
14) Check for the availability for Pincode 600128
15) Click on PROCEED TO PAY SECURELY from My Cart
16) Enter the Coupon code and click Apply
17) Click Proceed to Pay
18) Capture the screenshot of the item under ORDER SUMMARY
19) Close the browser*/

public class day4_pepperfry {

	public static void takeScreenshot(WebElement element,String eleName) throws IOException{

		File source = element.getScreenshotAs(OutputType.FILE);

		File target = new File("./snaps/" +eleName + ".png");

		FileUtils.copyFile(source, target);


	}
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
	JavascriptExecutor js;
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disableNotifications");
		ChromeDriver driver= new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.pepperfry.com/");
		driver.manage().deleteAllCookies();
		driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
		driver.findElementById("webklipper-publisher-widget-container-notification-close-div").click();
		driver.switchTo().defaultContent();
		driver.findElementByXPath("(//a[@class = 'popup-close'])[6]").click();
		Actions builder = new Actions(driver);
		
		//Mouseover on Furniture and click Office Chairs under Chairs
		WebElement furniture= driver.findElementByXPath("//a[text()='Furniture']");
		builder.moveToElement(furniture).click().perform();
		driver.findElementByXPath("//a[text()='Office Chairs']").click();
		Thread.sleep(1000);
		
		// click Executive Chairs
		driver.findElementByXPath("//h5[text()='Executive Chairs']").click();
		
		//Change the minimum Height as 50 in under Dimensions
		WebElement minheight = driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]");
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false);",minheight);
		minheight.clear();
		minheight.sendKeys("50",Keys.TAB);
		Thread.sleep(2000);
		
		
		//Add "Poise Executive Chair in Black Colour" chair to Wishlist
		driver.findElementByXPath("//a[@data-productname='Poise Executive Chair in Black Colour']").click();
		Thread.sleep(2000);
		
		/*
		 * Set <String> firstwindow= driver.getWindowHandles(); List<String> listHandle
		 * =new ArrayList<String>(firstwindow); String secondwindow= listHandle.get(1);
		 * driver.switchTo().window(secondwindow); driver.manage().deleteAllCookies();
		 * driver.navigate().refresh(); System.out.println(driver.getTitle());
		 */
		//Mouseover on Furniture and Click Office tables
	    WebElement furniture1= driver.findElementByXPath("//a[text()='Furniture']");
	    builder.moveToElement(furniture1).click().perform();
	    driver.findElementByXPath("//a[text()='Office Tables']").click();
	    Thread.sleep(1000);
	    
	    //Select Executive Desks
	    driver.findElementByXPath("//h5[text()='Executive Desks']").click();
	    Thread.sleep(1000);
	    
	    //Select Price between 20000 to 40000 rs
	    driver.findElementByXPath("//label[@for='price20000-40000']").click();
	    Thread.sleep(1000);
	    
	    //Add "Executive Office Table in Brown Color" to Wishlist
	    driver.findElementByXPath("//a[@data-productname='Executive Office Table in Brown Color']").click();
	    Thread.sleep(1000);

	    // Verify the number of items in Wishlist
	    String noOfItems=driver.findElementByXPath("(//span[@class='count_alert'])[2]").getText();
	    System.out.println("The number of items in wishlist "+noOfItems);
	    
	    //Navigate to Wishlist
	    driver.findElementByXPath("//a[@data-tooltip='Wishlist']").click();
	    
	    //Get the offer Price and Coupon Code for Executive Office Table in Brown Color
	    String offerPrice= driver.findElementByXPath("//p[@class='oprice']/span[2]").getText();
	    System.out.println("OfferPrice is "+offerPrice);
	    String couponCode= driver.findElementByXPath("//p[@class='pf-border pf-border-lighter-grey-50 pf-border-style-dashed pf-center pf-padding-tiny font-14 pf-italic-txt pf-text-grey pf-margin-top use-coupon']/strong").getText();
	    System.out.println("Coupon code is "+couponCode);
	    Thread.sleep(1000);
	    driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
	    driver.findElementById("webklipper-publisher-widget-container-notification-close-div").click();
	    driver.switchTo().defaultContent();
	    
	    //Move Executive Office Table in Brown Color only to Cart from Wishlist
	    driver.findElementByXPath("(//a[@data-tooltip='Add to Cart'])[1]").click();
	    
	    // Check for the availability for Pincode 600128
	    driver.findElementByXPath("//input [@class='srvc_pin_text']").sendKeys("600128");
	    driver.findElementByXPath("//a[@class='check_available']").click();
	    
	    //Click on PROCEED TO PAY SECURELY from My Cart
	    driver.findElementByClassName("proceed_cta").click();
		Thread.sleep(3000);

	    //Enter the Coupon code and click Apply
	    driver.findElementByXPath("//input[@class='inputcoupon']").sendKeys(couponCode);
	    driver.findElementById("cpn_check_btn").click();
	    Thread.sleep(1000);
	    //Click Proceed to Pay
	    driver.findElementByXPath("(//a[text()='PLACE ORDER'])[1]").click();
	    
	    //Capture the screenshot of the item under ORDER SUMMARY
	    
	    driver.findElementByXPath("(//div[@class='nCheckout__accrodian-header-right']//span)[1]").click();

		Thread.sleep(2000);

		WebElement orderimage = driver.findElementByXPath("//li[@role='option']//figure//img ");

		takeScreenshot(orderimage, "orderimage");

		Thread.sleep(2000);
		driver.quit();
		/*
		 * WebElement capture =
		 * driver.findElementByXPath("//li[contains(@class , 'onepge_ordersummary ')]");
		 * File src = ((TakesScreenshot)capture).getScreenshotAs(OutputType.FILE);
		 * FileUtils.copyFile(src, new
		 * File("C:\\Eclipse\\Selenium Workspace\\90days_SeleniumProgram\\screenshot"));
		 * driver.quit();
		 */
	}
	

	}


