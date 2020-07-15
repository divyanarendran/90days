package days;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/*14/07/2020
1.Goto https://autoportal.com/  
2.Select Location as Chennai  
3.Select Car Brand as Renault  
4.Select Model as Arkana  
5.Search for the car  
6.Print the expected price*/
public class week3_day10_AutoPortal {
	//public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions builder;


	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
    System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
    ChromeOptions options= new ChromeOptions();
    options.addArguments("--disableNotifications");
    ChromeDriver driver= new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get("https://autoportal.com/");
	driver.manage().deleteAllCookies();
    
    //click on change and select chennai
	Thread.sleep(2000);
    WebElement location = driver.findElementByXPath("//div[@class='maincity-in field']");
    location.click();
    
    //Enter Chennai
    Thread.sleep(2000);
    WebDriverWait wait=new WebDriverWait(driver,15);
    WebElement chennai= driver.findElementById("ac_user_city");
    wait.until(ExpectedConditions.visibilityOf(chennai)).click();
    Thread.sleep(1000);
    chennai.sendKeys("Chennai");
    Thread.sleep(2000);
    chennai.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
    
    //click on confirm city
    driver.findElementByXPath("//span[text()='Confirm City']").click();
    Thread.sleep(2000);
    
    
    //Select car brand as Renault
    WebElement brand= driver.findElementByXPath("(//select[@data-placeholder='select'])[1]");
    wait.until(ExpectedConditions.visibilityOf(brand)).click();
   Select brandName= new Select(brand);
   brandName.selectByVisibleText("Renault");
   
   //Select model as Arkana
   WebElement arkana= driver.findElementByXPath("//select[@name='model']");
   wait.until(ExpectedConditions.visibilityOf(arkana)).click();
   Select modelName= new Select(arkana);
   modelName.selectByVisibleText("Arkana");
   
   //click on find cars
   driver.findElementByXPath("(//input[@type='submit'])[1]").click();
   Thread.sleep(2000);
   
   //to print minimum and maximum price
   WebElement price= driver.findElementByXPath("//span[@class='WebRupee']");
   
   System.out.println("Expected Miniumum Price is: "+price.getAttribute("data-gtm-item-price-min"));
	System.out.println("Expected Maxiumum Price is: "+price.getAttribute("data-gtm-item-price-max"));
   driver.quit();
    
    }

}
