package days;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class week4_day13_Amazon_getnamesifpricedisplayed {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disableNotifications");
		ChromeDriver driver= new ChromeDriver(options);
		driver.get("https://www.amazon.in/");
		
		//type LG tv in search bar
		driver.findElementById("twotabsearchtextbox").sendKeys("LG TV",Keys.ENTER);
		Thread.sleep(3000);
			
		List<WebElement> lgwithPrices = driver.findElementsByXPath("//span[@class='a-price-whole']");

		for (int i = 1; i <= lgwithPrices.size(); i++) {
			String xpath1 = "(//span[@class='a-price-whole'])" + "[" + i + "]";
			String xpath2 = "/preceding::span[1]/../../../../../../../../..//../div[1]//div//div//div//h2//a//span";
			String xpathfinal = xpath1.concat(xpath2);
			WebElement tvPrices = driver.findElementByXPath(xpathfinal);
			System.out.println(tvPrices.getText());
		
		
	}
	}
}
