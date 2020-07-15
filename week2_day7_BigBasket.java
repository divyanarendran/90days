package days;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
/*  
			1) Go to https://www.bigbasket.com/
			2) mouse over on  Shop by Category 
			3) Go to Beverages and Fruit juices & Drinks
			4) Click on JUICES
			5) click Tropicana and Real under Brand
			6) Check count of the products from each Brands and total count
			6) Check whether the products is availabe with Add button.
			7) Add the First listed available product 
			8) click on Change Address 
			9) Select CHennai as City, Alandur-600016,Chennai as Area  and click Continue
			10) Mouse over on My Basket print the product name. count and price.
			11) Click View Basker and Checkout
			12) Click the close button and close the browser
		*/  

public class week2_day7_BigBasket {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int count=0;
    System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
    ChromeOptions options= new ChromeOptions();
    options.addArguments("--disableNotifications");
    ChromeDriver driver= new ChromeDriver(options);
    driver.get("https://www.bigbasket.com/");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    Thread.sleep(1000);
    
    //mouse over on  Shop by Category 
    // Go to Beverages and Fruit juices & Drinks
    // Click on JUICES
    Actions builder= new Actions(driver);
    WebElement category=driver.findElementByXPath("//a[@class='dropdown-toggle meganav-shop']");
    builder.moveToElement(category).perform();
    WebElement Beverages= driver.findElementByXPath("(//a[text()='Beverages'])[2]");
    builder.moveToElement(Beverages).perform();
    WebElement fruitsAndBeverages= driver.findElementByXPath("(//a[text()='Fruit Juices & Drinks'])[2]");
    builder.moveToElement(fruitsAndBeverages).perform();
    WebElement juicies= driver.findElementByXPath("(//a[text()='Juices'])[2]");
    builder.moveToElement(juicies).click().perform();
    Thread.sleep(2000);
    
    //click Tropicana and Real under Brand
    WebElement brand = driver.findElementByXPath("(//input[@value='Search by Brand'])[1]");
	brand.sendKeys("Tropicana");
	driver.findElementByXPath("(//span[text()='Tropicana'])[1]").click();
	brand.clear();
	Thread.sleep(3000);
	 driver.findElementByXPath("(//input[@value='Search by Brand'])[1]").sendKeys("Real");
	driver.findElementByXPath("(//span[text()='Real'])[1]").click();
	Thread.sleep(4000);
	//Check count of the products from each Brands and total count
	List totalsize=driver.findElementsByXPath("//div[@qa='product_name']");
	int totalSize=totalsize.size();
	System.out.println("Total size: "+totalSize);
	
	List tropicanaCount= driver.findElementsByXPath("//h6[text()='Tropicana']");
	int tropicana= tropicanaCount.size();
	System.out.println("Tropicana size: "+tropicana);
	
	List realCount = driver.findElementsByXPath("//h6[text()='Real']");
	int real= realCount.size();
	System.out.println("Real size :" +real);
	
	//Check whether the products is availabe with Add button.
	List add= driver.findElementsByXPath("//button[@qa='add']");
	int addCount=add.size();
	System.out.println("Products with add button: "+addCount);
	List notifyMe= driver.findElementsByXPath("//button[@qa='NM']");
	int notifyCount = notifyMe.size();
	System.out.println("Products with notify me :"+notifyCount);
	
	// Add the First listed available product 
	driver.findElementByXPath("(//button[@qa='add'])[1]").click();
	Thread.sleep(1000);
	
	//click on Change Address
	driver.findElementByXPath("//a[text()='Change Location']").click();
	Thread.sleep(10000);
	 //enter address
	driver.findElementByXPath("(//input[@qa='areaInput'])[1]").sendKeys("Avadi",Keys.DOWN,Keys.ENTER);
	Thread.sleep(4000);
	
	//click continue
	driver.findElementByXPath("//button[@name='continue']").click();
	Thread.sleep(1000);
	
	// Mouse over on My Basket print the product name. count and price.
	Actions builder4= new Actions(driver);
	WebElement myBasket=driver.findElementByXPath("//a[@qa='myBasket']");
	builder4.moveToElement(myBasket).perform();
	String  productName= driver.findElementByXPath("//a[@qa='prodNameMB']").getText();
	System.out.println("The product name is: "+productName);
		/*
		 * String productCount=
		 * driver.findElementByXPath("//input[@id='p_10000246']").getText();
		 * System.out.println("The count of the product is :"+productCount);
		 */
	WebElement count1 = driver.findElementByXPath("//input[contains(@class,'text-change-qty-search-popup ng-pristine')]");
	System.out.println(count1);
	String price= driver.findElementByXPath("//span[@qa='priceMB']").getText();
	System.out.println("The product price is: "+price);
	Thread.sleep(2000);
	
	//Click View Basker and Checkout
	driver.findElementByXPath("//button[@qa='viewBasketMB']").click();
	Thread.sleep(2000);
	
	//Click the close button and close the browser
	driver.findElementByXPath("//button[@class ='close']").click();

	Thread.sleep(2000);
	driver.quit();
	
	}

}
