package days;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*  
			1. Go to https://juliemr.github.io/protractor-demo/
			2. Input a number
			3. Select Multiplication
			4. Input second number
			5. Click GO
			6. Wait for the output to load and print the results
			Condition:
			* Should not use Thread.sleep
			Hint: Refer https://blog.vsoftconsulting.com/blog/testing-angular-applications-using-selenium
		*/  
public class week3_day9_AngularSuperCalculator {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://juliemr.github.io/protractor-demo/");
		Thread.sleep(2000);
		
		//give input1
		WebElement input1=driver.findElementByXPath("//input[@ng-model='first']");
		input1.sendKeys("5");
		//mul
		driver.findElementByXPath("//select[@ng-model='operator']").click();
		driver.findElementByXPath("//option[@value='MULTIPLICATION']").click();
		//give input2
		driver.findElementByXPath("//input[@ng-model='second']").sendKeys("5");
		driver.findElementById("gobutton").click();
		WebDriverWait wait;
		WebElement answer=driver.findElementByXPath("//h2[@class='ng-binding']");
		wait= new WebDriverWait(driver, 20);
		//wait.until(ExpectedConditions.visibilityOf(answer));
		//wait.until(ExpectedConditions.textToBePresentInElement(answer,	));
		Actions builder= new Actions(driver);
		builder.pause(2000).perform();		
		System.out.println(answer.getText());
		//System.out.println("its wrong");
		
		
	}

}
