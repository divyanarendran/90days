package days;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*  1) Go to https://store.hp.com/in-en/
2) Mouse over on Laptops menu and click on Pavilion
3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i5
4) Hard Drive Capacity -->500GB to 1TB
5) Identify the first product which is having in Stock
6) Print the Product Name and Price
7) Click on Add to Cart
8) Click on Shopping Cart icon --> Click on View and Edit Cart
9) Check the Shipping Option --> Check availability at Pincode
10) Verify the order Total against the product price
11) Proceed to Checkout if Order Total and Product Price matches
12) Click on Place Order
13) Verify that all the mandatory fields are getting error message and confirm
14) Close Browser
*/  

public class week3_day11_HP {
	public static WebDriverWait wait;

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
    System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
    ChromeOptions options= new ChromeOptions();
    options.addArguments("--disableNotifications");
    ChromeDriver driver= new ChromeDriver(options);
    driver.get("https://store.hp.com/in-en/default");
    driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    Thread.sleep(6000);
    
    //close cookies
    try {
		if(driver.findElementByXPath("//button[text()='Accept Cookies']").isDisplayed()) {
			driver.findElementByXPath("//button[text()='Accept Cookies']").click();
		}
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
   
    
   	
    //Mouse over on Laptops menu and click on Pavilion 
    Actions builder= new Actions(driver);
    WebElement laptops=driver.findElementByXPath("(//a[@id='ui-id-2'])[2]");
    builder.moveToElement(laptops).perform();
    driver.findElementByXPath("(//span[text()='Pavilion'])[1]").click();
    Thread.sleep(2000);
    try 
	{
		if(driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").isDisplayed())
		{
			driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").click();
		}
		
	} 
	catch (Exception e) 
	{
		System.out.println("notpresent");
	}
    
    	
		/*
		 * try { wait= new WebDriverWait(driver,10); WebElement signupclose= driver.
		 * findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']"
		 * ); wait.until(ExpectedConditions.visibilityOf(signupclose)).click(); } catch
		 * (Exception e) { // TODO Auto-generated catch block
		 * System.out.println("signup close"); }
		 */
		 
    wait= new WebDriverWait(driver,40);
    
    if(driver.findElementByXPath("(//span[text()='Processor'])[2]").isDisplayed()) {
    WebElement processor1=driver.findElementByXPath("(//span[text()='Processor'])[1]");
    WebElement processor2=driver.findElementByXPath("(//span[text()='Processor'])[2]");
    wait.until(ExpectedConditions.elementToBeClickable(processor2)).click();
    driver.findElementByXPath("//span[text()='Intel Core i5']").click();
    }
    else
    {
    	WebElement processor1=driver.findElementByXPath("(//span[text()='Processor'])[1]");
    	wait.until(ExpectedConditions.elementToBeClickable(processor1)).click();
    	Thread.sleep(3000);
    	 WebElement processor2=driver.findElementByXPath("(//span[text()='Processor'])[2]");
    	 wait.until(ExpectedConditions.elementToBeClickable(processor2)).click();
    	 driver.findElementByXPath("//span[text()='Intel Core i5']").click();
    	
    }
    try 
	{
		if(driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").isDisplayed())
		{
			driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").click();
		}
		
	} 
	catch (Exception e) 
	{
		System.out.println("no sign up");
	}
    Thread.sleep(2000);
    // Hard Drive Capacity -->500GB to 1TB 
		WebElement driverCapacity= driver.findElementByXPath("//span[text()='Hard Drive Capacity']");
		wait.until(ExpectedConditions.elementToBeClickable(driverCapacity)).click();
		Thread.sleep(12000);
		WebElement capacity=driver.findElementByXPath("//span[text()='500 GB to 1 TB']");
		wait.until(ExpectedConditions.elementToBeClickable(capacity)).click();
		Thread.sleep(10000);
		
		//Identify the first product which is having in Stock 
		//Print the Product Name and Price 
		WebElement fproductPrice= driver.findElementByXPath("//span[text()='In Stock']/preceding::span[@id='product-price-10511']");
		String fPrice=fproductPrice.getText().replaceAll("\\D", "");
		WebElement fproductName=driver.findElementByXPath("//span[text()='In Stock']/preceding::a[@class='product-item-link']");
		String fProduct= fproductName.getText();
		System.out.println("Product price"+fPrice+"Product name:"+fProduct);
		
		//Add to cart
		driver.findElementByXPath("//span[text()='In Stock']/following::span[text()='Add To Cart']").click();
		Thread.sleep(5000);
		
		//Click on Shopping Cart icon --> Click on View and Edit Cart 
		driver.findElementByXPath("//a[@title='Shopping Cart']").click();
		driver.findElementByXPath("//a[@class='action primary viewcart']").click();
		Thread.sleep(2000);
		
		//check for pincode
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("600054");
		driver.findElementByXPath("//button[text()='check']").click();
		Thread.sleep(2000);
		
		//get message
		String getMsg=driver.findElementByXPath("//div[@class='delivery-days']").getText();
		System.out.println(getMsg);
		
		//Verify the order Total against the product price  
	   WebElement orderPrice=driver.findElementByXPath("//td[@data-th='Order Total']//span[1]");
	   String orPrice= orderPrice.getText().replaceAll("\\D", "");
	   if(fPrice.contentEquals(orPrice))
	   {
		   System.out.println("Total price is matching");
		   Thread.sleep(2000);
		   driver.findElementByXPath("(//span[text()='Proceed to Checkout'])[1]").click();
		   Thread.sleep(3000);
		   driver.findElementByXPath("(//span[text()='Place Order'])[4]").click();
	   }
	   else {
		   System.out.println("price doesnot match");
	   }
	   //take snapshot of the entire page
	   WebElement orderSummary = driver.findElementByXPath("//div[@class='col-mp mp-12']");
		File src1 = ((TakesScreenshot) orderSummary).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new File("C:\\Eclipse\\Selenium Workspace\\90days_SeleniumProgram\\HP_OrderSummary.png"));
		
		//
		WebElement errorMsg=driver.findElementByXPath("//*[text()=\"This is a required field.\"]");
		if(errorMsg.isDisplayed()) {
		
			String emailerror=driver.findElementByXPath("//label[@for='customer-email']/following-sibling::div[1]").getText();
			if(emailerror.isEmpty())
			{
				System.out.println("No error msg displayed for email");
			}
			else
			{
				System.out.println("Error msg displayed for email");
			}
			String fName=driver.findElementByXPath("(//span[text()='First Name']/following::div/span)[1]").getText();
			if(fName.isEmpty()) {
				System.out.println("Firstname no error msg");
			}
			else { System.out.println("Firstname error msg");}
			}
		  String lName=driver.findElementByXPath("(//span[text()='Last Name']/following::div/span)[1]").getText();
		  if(lName.isEmpty()) { System.out.println("Lastname no error message");}
		  else { System.out.println("LAstname error msg");}
		  String phnum= driver.findElementByXPath("(//span[text()='Phone Number']/following::div/span)[2]").getText();
		  if(phnum.isEmpty()) { System.out.println("phnum no error msg");}
		  else { System.out.println("phnum error msg");}
		  String address= driver.findElementByXPath("(//span[text()='Street Address']/following::div/span)[1]").getText();
		  if(address.isEmpty()) { System.out.println("address no error msg");}
		  else { System.out.println("address has error msg");}
		  
		  //close the browser
		  driver.close();
		  
	}
		
	

}

