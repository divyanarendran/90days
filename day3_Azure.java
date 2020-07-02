package days;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class day3_Azure {
	public static ChromeDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	

	public static  void isFileDownloaded(String filename)
	{
		File file = new File("C:\\Users\\preet\\Downloads");
	 if(file.exists())
	 {
		 System.out.println("File "+filename+" has been downloaded");
	 }
	 else
	 {
		 System.out.println("File "+filename+" has NOT been downloaded");
	 }
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable notification");
	   driver= new ChromeDriver(options);
		driver.get("https://azure.microsoft.com/en-in/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
		//click on pricing
		driver.findElementById("navigation-pricing").click();
		
		//click on pricing calculator
		driver.findElementByXPath("//a[contains(text(),'Pricing calculator')]").click();
		
		//click on containers
		WebElement container = driver.findElementByXPath("//button[@value='containers']");
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false);",container);
		container.click();
		Thread.sleep(2000);
		
		//select container instance
		driver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();
		Thread.sleep(1000);
		//Click on Container Instance Added View
		WebElement view=driver.findElementById("new-module-loc");
		wait= new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(view)).click();
		Thread.sleep(1000);
		
		//Select Region as "South India"
		driver.findElementByXPath("(//select[@name='region'])[1]").click();
		driver.findElementByXPath("(//option[@value='south-india'])[1]").click();
		
		  	
		//Set the Duration as 180000 seconds
		WebElement seconds=driver.findElementByXPath("(//input[@name='seconds'])[1]");
		seconds.clear();
		seconds.sendKeys(Keys.ARROW_RIGHT,"80000");
		
		//select the Memory as 4GB
		WebElement memory=driver.findElementByXPath("(//select[@name='memory'])[1]");
		memory.click();
		driver.findElementByXPath("(//option[text()='4 GB'])[1]").click();
		
		// Enable SHOW DEV/TEST PRICING
		driver.findElementById("devtest-toggler").click();
		
		// Select Indian Rupee  as currency
		driver.findElementByXPath("//select[@class='select currency-dropdown']").click(); 
		driver.findElementByXPath("//select[@class='select currency-dropdown']//option[5]"). click();
	    
		//Print the Estimated monthly price
		String monthlyprice=driver.findElementByXPath("(//span[@class='numeric'])[4]").getText();
		System.out.println("Estimaged monthly price:" +monthlyprice);
		
		//Download Estimate price
		driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
		
		//Verify the downloded file in the local folder
		String file_name = "ExportedEstimate.xlsx";
		isFileDownloaded(file_name);
		Thread.sleep(1000);
		
		//Navigate to Example Scenarios and Select CI/CD for Containers
		driver.executeScript("window.scrollTo(0,-500)");
	    driver.findElementByXPath("//a[text() = 'Example Scenarios']").click();
		driver.findElementByXPath("//button[@title = 'CI/CD for Containers']").click();
		
        //Click Add to Estimate
		driver.findElementByXPath("//button[text() = 'Add to estimate']").click();
		Thread.sleep(10000);
		
		//Change the Currency as Indian Rupee
		
		driver.findElementByXPath("//select[@class='select currency-dropdown']").click(); 
		driver.findElementByXPath("//select[@class='select currency-dropdown']//option[5]"). click();
		
		//Enable SHOW DEV/TEST PRICING
		driver.findElementById("devtest-toggler").click();
		
		// Export the Estimate
		driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
		Thread.sleep(3000);
		
		//Verify the downloaded folder  in local folder
		String file_name1 = "ExportedEstimate (1).xlsx";
		isFileDownloaded(file_name1);
		
		
	}

}
