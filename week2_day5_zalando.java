package days;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class week2_day5_zalando {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverWait wait;
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disableNotifications");
		ChromeDriver driver= new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.zalando.com/");
		Thread.sleep(1000);
		//Get the Alert text and print it 
		  Alert alert = driver.switchTo().alert();
		  Thread.sleep(1000);
		  System.out.println(alert.getText());
		  alert.accept(); System.out.println("alert done");
		  
		  //Close the Alert box and click on Zalando.uk
		
		  driver.findElementByXPath("//a[text()='Zalando.uk']").click();
		  Thread.sleep(2000);
		
		/*
		 * driver.findElementByXPath("//button[@class='uc-btn uc-btn-primary']").click()
		 * ; Thread.sleep(1000);
		 */
		  try
			{
				driver.findElementByXPath("//button[@class='uc-btn uc-btn-primary']").click();
			}
			catch(Exception e)
			{
				System.out.println("No such preference found");
			}
			try
			{
				driver.findElementByXPath("//button[@class='uc-btn uc-btn-primary']").click();
			}
			catch(Exception e)
			{
				System.out.println("No such preference found");
			}
		 
		  //Click Women--> Clothing and click Coats 
		  driver.findElementByXPath("//span[text()='Women']").click();
		  Thread.sleep(1000);
		  driver.findElementByXPath("//span[text()='Clothing']").click();
		  Thread.sleep(1000);
		  driver.findElementByXPath("(//a[text()='Coats'])[3]").click();
		  Thread.sleep(1000);
		  	  
		  //Choose Material as cotton (100%) and Length as thigh-length
		  driver.findElementByXPath("(//button[@class='cat_head-3QSpK cat_brd-4-27afw'])[6]").click();
		  driver.findElementByXPath("//span[text()='cotton (100%)']/parent::li").click();
		  driver.findElementByXPath("//button[text()='Save']").click();
		  Thread.sleep(2000);
	 
		  driver.findElementByXPath("(//button[@class='cat_head-3QSpK cat_brd-4-27afw'])[8]/span[2]").click();
		  driver.findElementByXPath("//span[text()='thigh-length']/parent::li").click();
		  driver.findElementByXPath("//button[text()='Save']").click();
		  Thread.sleep(1000);
		  
		//Click on JUNAROSE - by VERO MODA
		  driver.findElementByXPath("(//a[@class='cat_imageLink-OPGGa'])[1]").click();
		  Thread.sleep(2000);
		  
		  //Click Color as Black and Size as 'M' Under Manufacture Sizes
		  driver.findElementByXPath("(//img[@class='Q8HVfj oMyDaX hsKyRV _8Nfi4s BQJRnm uijqg-'])[13]").click();
		  Thread.sleep(1000);
		  driver.findElementByXPath("//button[@aria-label='Choose your size']").click();
		  driver.findElementByXPath("(//div[@class='UyCaZm _2TPICz mAhwAe FIoNYa'])[3]").click();
		  Thread.sleep(1000);
		  
		  //Add to bag only if Standard Delivery is free
		  String delieveryFree= driver.findElementByXPath("(//span[text()='Free'])[1]").getText();
		  System.out.println(delieveryFree);
		  if(delieveryFree.contains("Free")) {
			  driver.findElementByXPath("(//span[text()='Add to bag'])").click();
		  }
		  else {
			  System.out.println("not free of cost");
		  }
		  
		  //goto bag
		  WebElement bag = driver.findElementByXPath("//span[text()='Your bag']"); 
		  Actions builder = new Actions(driver);
		  builder.moveToElement(bag).click().perform();
		 // driver.findElementByXPath("//div[text()='Go to bag']").click();
		  Thread.sleep(1000);
		  
		  //Read the Estimated Deliver Date and print
		  String estimatedDeliver = driver.findElementByXPath("//div[@data-id='delivery-estimation']/span").getText();
		  System.out.println("Estimated delievery:" +estimatedDeliver);
		  
		  //Click on 'Go To Checkout'
		  driver.findElementByXPath("(//div[text()='Go to checkout'])[1]").click();
		  Thread.sleep(2000);
		  
		  //Enter your email
		  driver.findElementById("login.email").sendKeys("divyanarendran05@gmail.com");
		  
		  //Click on Login button
		  driver.findElementByXPath("//span[text()='Login']").click();
		  
		  //Print the error message
          String errorMessage= driver.findElementByXPath("//span[text()='This field is required']").getText();
          System.out.println("Error messsage:  "+errorMessage);
          
          driver.quit();
		  
		 
		
		
	}

}
