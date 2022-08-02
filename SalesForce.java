package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForce {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();

		// Adding Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// login
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 username']"))
				.sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 password']")).sendKeys("Password$123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// clicking on LearnMore from Mob Publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();

		// Window handles
		String FirstWindow = driver.getWindowHandle();
		System.out.println("The First Window title is : " + FirstWindow);
		Set<String> windowHandles = driver.getWindowHandles();
		java.util.List<String> allWindows = new ArrayList<String>(windowHandles);
		String secWindow = allWindows.get(1);
		System.out.println("Total no of windows are : " + windowHandles.size());
		
		//Switching to secondWindow
		driver.switchTo().window(secWindow);
		
		//confirm to redirect
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		System.out.println("Title of the page is : "+driver.getTitle());
		
		//moving back to parent window
		driver.switchTo().window(FirstWindow).close();
		

	}

}
