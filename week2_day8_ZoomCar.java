package days;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*  
			1. Go to https://www.zoomcar.com/chennai
			2. Click on Start your wonderful journey
			3. Select  any location under POPULAR PICK-UP POINTS and click next
			4. Select tomorrow's date and time as 9:00 AM as start date and time and Click Next
			5. Click on Show More and Select tomorrow's date and Select time as 6:00 PM as end date  and Click Done
			6.  Take the snapshot for PICKUP TIME and DROP OFF TIME
			7. Validate the pickup time and Drop off time are correct (as you selected) and click on Done
			8. Click on Price: High to Low and validate the sort order of the car price programmatically
			9. Print all the Car name and respective Price from High to Low ( car name -- price )
			10. Take snapshot of the details for the Highest price car
			11. Click on Know More for the Highest price car and print the rate after 45Kms
			12. Close the Browser
		*/  

public class week2_day8_ZoomCar {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disableNotifications");
		ChromeDriver driver= new ChromeDriver(options);
		driver.get("https://www.zoomcar.com/chennai/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		Thread.sleep(2000);
		
		//Click on Start your wonderful journey
		driver.findElementByXPath("//a[@title='Start your wonderful journey']").click();
		Thread.sleep(2000);
		
		//Select  any location under POPULAR PICK-UP POINTS and click next
		
		driver.findElementByXPath("//input[@placeholder='Tell us your Starting point in Chennai']").sendKeys("Avadi");
		Thread.sleep(1000);
		driver.findElementByXPath("(//div[@class='pac-item'])[1]").click();
		Thread.sleep(1000);
		
		//click on next
		driver.findElementByXPath("//button[text()='Next']").click();
		Thread.sleep(1000);
		
		//Select tomorrow's date and time as 9:00 AM as start date and time and Click Next
		driver.findElementByXPath("//div[@class='days']/div[2]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//span[text()='09:00']/parent::li").click();
		Thread.sleep(1000);
		
		//click on next
		driver.findElementByXPath("//button[text()='Next']").click();
		Thread.sleep(3000);
		
		//Click on Show More and 
		//Select tomorrow's date and
		//Select time as 6:00 PM as end date  and
		//Click Done
		driver.findElementByXPath("//div[text()='show more']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//li[text()='15']").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//span[text()='18:00']").click();
		
		  //driver.findElementByXPath("//button[text()='Done']").click();
		  Thread.sleep(2000);
		 
		//take screenshot
		WebElement pickupdrop= driver.findElementByXPath("//div[@class='label time-label']");
		// wait = null;
		//wait.until(ExpectedConditions.visibilityOf(pickupdrop));
		//to get the entire page screenshot
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);
		//To get the location of the page of that element
		Point point = pickupdrop.getLocation();
		//To get the width,height of element
		int eleWidth = pickupdrop.getSize().getWidth();
		int eleHeight = pickupdrop.getSize().getHeight();
		//to crop that particular element from entire page 
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		//copy the element screenshot to disk
		File DestFile = new File("./screenshot/Zoomcar_Pickupdropofftime4.png");
		FileUtils.copyFile(screenshot, DestFile);
		
		driver.findElementByXPath("//button[text()='Done']").click();
		Thread.sleep(2000);
		//Validate the pickup time and Drop off time are correct 
		String pickuptime= driver.findElementByXPath("(//div[@class='time'])[1]").getText();
		String droptime=driver.findElementByXPath("(//div[@class='time'])[2]").getText();
		if(( pickuptime.contains("09:00 AM"))&& (droptime.contains("06:00 PM"))){
			System.out.println("Pickup time "+pickuptime+"Droptime:"+droptime);
		}
		//Click on Price: High to Low and validate the sort order of the car price programmatically
		driver.findElementByXPath("//div[@class='item active-input']").click();
		Thread.sleep(1000);
		
		
		/*
		 * List<WebElement> carprice=
		 * driver.findElementsByXPath("//div[@class='price']"); List<Integer> carprices=
		 * new ArrayList<>(); for (WebElement carint : carprice) { String
		 * carrupees=carint.getText(); carrupees=carrupees.replaceAll("[^0-9]", "");
		 * carprices.add(Integer.parseInt(carrupees));
		 * 
		 * } System.out.println("Before sort"); System.out.println(carprice);
		 * Collections.sort(carprices); System.out.println(carprices);
		 * System.out.println("d");
		 */
		 

		// 8. Click on Price: High to Low and validate the sort order of the car price
				// programmatically
		
		  driver.findElementByXPath("//div[text() = ' Price: High to Low ']").click();
		  List<WebElement> prices =
		  driver.findElementsByXPath("//div[@class = 'new-price']"); List<Integer>
		  prices1 = new ArrayList<>(); for (WebElement price : prices) { String
		  pricenum = price.getText(); pricenum = pricenum.replaceAll("[^0-9]", "");
		  prices1.add(Integer.parseInt(pricenum)); }
		  System.out.println("----------Before Sort---------");
		  System.out.println(prices1);
		  System.out.println("----------After Sort---------");
		  Collections.sort(prices1); System.out.println(prices1);
		  System.out.println("----------After Reverse Sort---------");
		  Collections.reverse(prices1);
		  System.out.println(prices1);
		  
		  // 9. Print all the Car name and respective Price from High to Low ( car name --
			// price
		  int size= driver.findElementsByXPath("//div[@class = 'details']/h3").size();
		  for(int i=1; i< size;i++) {
			  String carnames="(//div[@class = 'details']/h3["+i+"]";
			 String carname= driver.findElementByXPath("//div[@class = 'details']/h3").getText();
			 String carprices = "(//div[@class = 'new-price'])[" + i + "]";
				String carprz = driver.findElementByXPath(carprices).getText();
				System.out.println(carname + "-----" + carprz);
			 
		  }
		  //take screenshot of first element
		  WebElement high_paycar = driver.findElementByXPath("(//div[@class = 'details'])[1]");
			File src1 = ((TakesScreenshot) high_paycar).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src1, new File("C:\\Users\\preet\\OneDrive\\Desktop\\img1.png"));
			
			//// 11. Click on Know More for the Highest price car and print the rate after 45Kms
			driver.findElementByXPath("(//div[@class = 'know-more-details'])[1]").click();
			String fourtyfivekm= driver.findElementByXPath("(//div[@class = 'price-info'])[1]").getText();
			System.out.println(fourtyfivekm);
			
			driver.quit();
		  

		
	}
	
		
		
		
		
	}


