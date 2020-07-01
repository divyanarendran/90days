package days;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class day2_ajio {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
     String couponactual=null;
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable notification");
		ChromeDriver driver= new ChromeDriver(options);
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//Enter Bags in the Search field and Select Bags in Women Handbags
		driver.findElementByXPath("//input[@name='searchVal']").sendKeys("Bags");
		driver.findElementByXPath("//span[text()='Bags in ']").click();
		Thread.sleep(1000);
		
		//Click on five grid and Select SORT BY as "What's New"
		driver.findElementByClassName("five-grid").click();
		//driver.findElementByClassName("ic-Chevron-down").click();
		driver.findElementByXPath("//div[@class='filter-dropdown']//select[1]").click();
		driver.findElementByXPath("//option[@value='newn']").click();
		Thread.sleep(2000);
		
		//Enter Price Range Min as 2500 and Max as 5000
		driver.findElementByXPath("(//div[@class = 'facet-head '])[1]").click();
		driver.findElementById("minPrice").sendKeys("2500");
		driver.findElementById("maxPrice").sendKeys("5000");
		driver.findElementByXPath("(//button[@type='submit'])[2]").click();
		Thread.sleep(3000);
		
		//Click on the product "TOMMY HILFIGER Sling Bag with Chain Strap"
		driver.findElementByXPath("(//div[text()='TOMMY HILFIGER'])[7]").click();
		Set <String> firstwindow= driver.getWindowHandles(); 
		  List<String> listHandle =new ArrayList<String>(firstwindow); 
		  String secondwindow= listHandle.get(2); 
		  driver.switchTo().window(secondwindow);
		System.out.println(driver.getTitle());
		//Verify the Coupon code for the price above 2890 is applicable for your product,
		//if applicable then get the Coupon Code and the discount price for the coupon
         
		String couponverif=driver.findElementByXPath("//div[text()='Extra Upto 32% Off on 2890 and Above. ']").getText();
		if(couponverif.contains("2890 and Above"))
		{
		System.out.println("yes coupon verification is done");
		}
        String couponcode= driver.findElementByXPath("//div[@class='promo-title-blck']").getText();
       String coupon1=couponcode.replaceAll("Use Code ", "");
       System.out.println(coupon1);
     
        String discountprice= driver.findElementByXPath("//div[@class='promo-discounted-price']//span").getText();
       // System.out.println(discountprice);
        String discount1= discountprice.replaceAll("[^0-9]", "");
        System.out.println("The discounted price is "+discount1);
		
        if(coupon1.contains("GRAB"))
        		{
        	 couponactual = "GRAB";
        	System.out.println(couponactual);
        		}
		
        //Check the availability of the product for pincode 560043, 
        //print the expected delivery date if it is available
        
        driver.findElementByXPath("//span[text()='Enter Pin-code To Know Estimated Delivery Date']").click();
       driver.findElementByClassName("edd-pincode-modal-text").sendKeys("560043");
       driver.findElementByXPath("//button[@class='edd-pincode-modal-submit-btn']").click();
       Thread.sleep(2000);
      String expecteddelivery= driver.findElementByXPath("//ul[@class='edd-message-success-details']/li[1]").getText();
        System.out.println(expecteddelivery);
        
        //Click on Other Informations under Product Details and Print the Customer Care address, phone and email
        driver.findElementByClassName("other-info-toggle").click();
       String customerCareAddress= driver.findElementByXPath("(//span[@class='other-info'])[7]").getText();
        System.out.println("Customer care address: "+customerCareAddress);
        
        // Click on ADD TO BAG and then GO TO BAG
        driver.findElementByXPath("//div[@class='pdp-addtocart-button']").click();
        Thread.sleep(10000);
        driver.findElementByXPath("//div[@class='btn-cart']").click();
        
        //Check the Order Total before apply coupon
        String priceBeforeCoupon= driver.findElementByXPath("//div[@class='net-price best-price-strip']").getText();
        System.out.println("The order total before apply coupon is "+priceBeforeCoupon);
        
        //Enter Coupon Code and Click Apply
        driver.findElementById("couponCodeInput").sendKeys(couponactual);
        driver.findElementByXPath("//button[text()='Apply']").click();
        Thread.sleep(5000);
        
        //Verify the discount price matches with the product price
        String discountedPrice= driver.findElementByXPath("//div[@class='net-price best-price-strip']").getText();
        String discountedPrice1=discountedPrice.replaceAll(",", "");
        System.out.println("Price after discount:" +discountedPrice1);
        if(!discountedPrice1.equals(discount1))
        {
        	System.out.println("Discount price not matched");
        }
        
        
        //Click on Delete and Delete the item from Bag
        driver.findElementByClassName("delete-btn").click();
        driver.findElementByXPath("(//div[@class='delete-btn'])[2]").click();
        
        //close all browsers
        driver.quit();
	}

}
