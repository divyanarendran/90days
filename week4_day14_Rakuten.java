package days;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class week4_day14_Rakuten {
	/*
	 * 1.Goto https://www.rakuten.com/shop/ 
	 * 2.Type playstation 5 in search and search for it
	 *3.print the name and price of all the outputs
	 */

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disableNotifications");
		ChromeDriver driver= new ChromeDriver(options);
		driver.get("https://www.rakuten.com/shop/");
		
		//type playstation and search for it
		Thread.sleep(10000);
		driver.findElementByXPath("(//div[@class='r-search__placeholder-text'])[1]").click();
		driver.findElementByXPath("(//input[@name='searchQueryTextBox'])[1]").sendKeys("Playstation");
		driver.findElementByXPath("(//div[@class='r-search__submit-button'])[1]").click();
		Thread.sleep(3000);
		
		//print the name and price of all outputs
		List<WebElement> name= driver.findElementsByXPath("(//div[@class='r-product__sub-block'])");
		int size= name.size();
		System.out.println("size is"+size);
		for(int i=0;i<size;i++) {
		System.out.println(name.get(i).getText());
		}
		
	}

}
