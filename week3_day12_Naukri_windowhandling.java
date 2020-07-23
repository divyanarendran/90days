package days;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class week3_day12_Naukri_windowhandling {

	/*  1) Go to https://www.naukri.com/
		2) Three popup windows will get opened
		3) Capture the Company name from each window using the attribute value which holds the company name
		4) print the company name
		5) close each window		
	*/  

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disableNotifications");
		ChromeDriver driver= new ChromeDriver(options);
		driver.get("https://www.naukri.com/");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
		Set<String> firstSecondThird= driver.getWindowHandles();
		List<String> listHandles= new ArrayList<String>(firstSecondThird);
		int size= listHandles.size();
		System.out.println("Size:+"+size);
		for(int i=0;i<size-1;i++) {
			driver.switchTo().window(listHandles.get(i+1));
			String windowName=driver.findElementByXPath("//img[contains(@src,'gif')]").getAttribute("alt");
			System.out.println("Window name:" +windowName);
			System.out.println("Window Title:"+driver.getTitle());
			driver.close();
				}
			driver.switchTo().window(listHandles.get(0));
			driver.close();
	}
	}


