import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class day1_makemytrip {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable notification");
		ChromeDriver driver= new ChromeDriver(options);
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		//Click Hotels
		driver.findElementByXPath("//div[contains(@class,'loginModal')]").click();
		//Thread.sleep(1000);
	   driver.findElementByXPath("//a[@href='https://www.makemytrip.com/hotels/']").click();
				//driver.findElementByXPath("//div[@class='chHeaderContainer']//li[@class='menu_Hotels']/a").click();
	   
	   //Enter city as Goa, and choose Goa, India
	   driver.findElementByXPath("//label[@for='city']").click();
	   WebElement city= driver.findElementByXPath("//input[contains(@class,'react-autosuggest__input react-autosuggest__input--open')]");
		/*
		 * city.sendKeys("Goa"); city.sendKeys(Keys.DOWN); city.click();
		 */
	   city.sendKeys("Goa");
	   driver.findElementByXPath("//p[text()='Goa, India']").click();
	   
	   //Enter Check in date as Next month 15th (July 15) and Check out as start date+4
	   driver.findElementByXPath("(//div[text()='15'])[1]").click();
		
		driver.findElementByXPath("//div[contains(@class,'DayPicker-Day DayPicker-Day--start')]/following::div[4]/div[2]").click();
         
		//Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
		driver.findElementByXPath("//input[contains(@class,'hsw_inputField guests font20')]").click();
		driver.findElementByXPath("//ul[contains(@class,'guestCounter font12 darkText')]//li[2]").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		driver.findElementByXPath("//select[contains(@class,\"ageSelectBox\")]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//option[@data-cy='childAgeValue-12']").click();
		driver.findElementByXPath("//button[text()='APPLY']").click();
		
		
	    //Click Search button
		driver.findElementById("hsw_search_button").click();
		
		//since we couldnot connect this page elements since it shows like tour
		driver.findElementByClassName("mapCont").click();
		driver.findElementByXPath("//span[@class='mapClose']").click();
		//Select locality as Baga
		//((WebElement) driver).sendKeys(Keys.ENTER);
		driver.findElementByXPath("//label[@for='mmLocality_checkbox_35']").click();
		Thread.sleep(1000);
		//Select user rating as 3&above(good) under Select Filters
		driver.findElementByXPath("//label[text()='3 & above (Good)']").click();
		
		//Select Sort By: Price-Low to High
		driver.findElementByXPath("//span[text()='Popularity']").click();
		driver.findElementByXPath("//li[text()='Price - Low to High']").click();
		//Click on the first resulting hotel and go to the new window
		
		driver.findElementByXPath("//span[text()='Baga Suites']").click();
		
		
		  Set <String> firstwindow= driver.getWindowHandles(); 
		  List<String> listHandle =new ArrayList<String>(firstwindow); 
		  String secondwindow= listHandle.get(2); 
		  driver.switchTo().window(secondwindow);
		  driver.manage().deleteAllCookies();
		  driver.navigate().refresh();
		  System.out.println(driver.getTitle());
		 
		/*
		 * driver.getCurrentUrl(); driver.get(
		 * "https://www.makemytrip.com/hotels/hotel-details/?hotelId=201908211954577721&mtkeys=defaultMtkey&_uCurrency=INR&checkin=07152020&checkout=07202020&city=CTGOI&country=IN&filterData=USER_RATING%7C3&lat=15.554174&lng=73.75536&locusId=CTGOI&locusType=city&mmAreaTag=Baga%7CARBAG&rank=1&roomStayQualifier=2e1e12e&searchText=Goa%2C%20India&sort=price-asc&visitorId=23135743754336375813576230312387556030"
		 * );
		 */
		System.out.println("done");
		
		// Print the Hotel Name 
		String hotelname=driver.findElementById("detpg_hotel_name").getText();
		System.out.println(hotelname);
		//Click VIEW THIS COMBO button under Recommended Combo
		driver.findElementByXPath("//a[@class = 'primaryBtn ']").click();
		//Print the Total Payable amount
		String amount = driver.findElementById("revpg_total_payable_amt").getText();
		System.out.println(amount);
		
		
		
		driver.quit();
		
	
		
		
	}

}
