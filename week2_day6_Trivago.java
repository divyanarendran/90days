package days;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
/*  
			1) Go to https://www.trivago.com/
			2) Type Agra in Destination and select Agra, Uttar Pradesh.
			3) Choose May 15 as check in and May 30 as check out
			4) Select Room as Family Room
			5) Choose Number of Adults 2, Childern 1 and set Child's Age as 4
			6) Click Confirm button and click Search
			7) Select Accommodation type as Hotels only and choose 4 stars
			8) Select Guest rating as Very Good
			9) Set Hotel Location as Agra Fort and click Done
			10) In more Filters, select Air conditioning, Restaurant and WiFi and click Done
			11) Sort the result as Rating & Recommended
			12) Print the Hotel name, Rating, Number of Reviews and Click View Deal
			13) Print the URL of the Page
			14) Print the Price of the Room and click Choose Your Room
			15) Click Reserve and I'll Reserve
			16) Close the browser
		*/  
		

public class week2_day6_Trivago {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disableNotifications");
		ChromeDriver driver= new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.trivago.in/");
		driver.manage().deleteAllCookies();
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,150)", "");
		Thread.sleep(2000);
		
		//Type Agra in Destination and select Agra, Uttar Pradesh.
		WebElement destination = driver.findElementById("querytext");
		destination.clear();
		destination.sendKeys("Agra");
		Thread.sleep(1000);
		driver.findElementByXPath("//span[text()='City - Uttar Pradesh, India']/parent::div").click();
		Thread.sleep(2000);
		
		//Choose May 15 as check in and May 30 as check out
		Thread.sleep(2000);
		driver.findElement(By.xpath("//time[@datetime='2020-07-19']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//time[@datetime='2020-07-26']")).click();
		Thread.sleep(1000);
		
		//Choose Number of Adults 2, Childern 1 and set Child's Age as 4
		driver.findElementByXPath("(//button[@class='circle-btn circle-btn--plus'])[2]").click();
		driver.findElementByXPath("//li[@class='ages-input__item has-tooltip']").click();
		driver.findElementByXPath("//select[@name='mf-select-children-age-0']/option[6]").click();
		driver.findElementByXPath("//button[text()='Apply']").click();
		Thread.sleep(1000);
		//click search button
		driver.findElementByXPath("//span[text()='Search']").click();
		Thread.sleep(3000);
		
		//Select Accommodation type as Hotels only and choose 4 stars
		Actions builder= new Actions(driver);
		WebElement accomodation= driver.findElementByXPath("//span[text()='All types']");
		builder.moveToElement(accomodation).perform();
		Thread.sleep(1000);
		WebElement hotes = driver.findElementByXPath("(//input[@name='accommodation-type'])[2]");
		builder.moveToElement(hotes).click().perform();
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@class='refinement-row__content']//button[@title='4-star hotels']").click();
		Thread.sleep(1000);
		//builder.moveToElement(fourstar).click().perform();
		Thread.sleep(1000);
		 driver.findElementByXPath("//button[text()='Done']").click();
		//builder.moveToElement(done).click().perform();
		
		// Select Guest rating as Very Good
		Actions builder1= new Actions(driver);
		WebElement guestRating= driver.findElementByXPath("(//span[@class='filter-item__placeholder'])[2]");
		builder1.moveToElement(guestRating).perform();
		WebElement veryGood= driver.findElementByXPath("//span[text()='Very good']");
		builder1.moveToElement(veryGood).click().perform();
		
		//Set Hotel Location as Agra Fort and click Done
		Actions builder2= new Actions(driver);
		WebElement hotelLocation= driver.findElementByXPath("(//span[@class='filter-item__placeholder'])[3]");
		builder2.moveToElement(hotelLocation).perform();
		Thread.sleep(1000);
		WebElement popularsite= driver.findElementByXPath("//select[@id='pois']");
		builder2.moveToElement(popularsite).click().perform();
		Thread.sleep(1000);
		Select ss= new Select(popularsite);
		ss.selectByVisibleText("Agra Fort");
		WebElement done1= driver.findElementByXPath("//button[text()='Done']");
		builder2.moveToElement(done1).click().perform();
		
		// In more Filters, select Air conditioning, Restaurant and WiFi and click Done
		Actions builder3= new Actions(driver);
		WebElement filters= driver.findElementByXPath("//span[text()='Select']");
		builder3.moveToElement(filters).perform();
		driver.findElementByXPath("//label[text()='Air conditioning']").click();
		driver.findElementByXPath("//label[text()='WiFi']").click();
		driver.findElementByXPath("//label[text()='Restaurant']").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//button[text()='Done']").click();
		
		//sort by recommendations
		driver.findElementByXPath("//select[@title='Sort by']").click();
		driver.findElementByXPath("//select[@title='Sort by']/option[1]").click();
		Thread.sleep(2000);
		
		//print all the hotel names
		List<WebElement> hotelnames= driver.findElementsByXPath("//span[@class='item-link name__copytext']");
		int size=hotelnames.size();
		System.out.println(size);
		for(int i=0;i<size;i++)
		{
			System.out.println(hotelnames.get(i).getText());
		}
		
	}

}
