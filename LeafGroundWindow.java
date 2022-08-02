package week4.day1;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundWindow {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// Launch the browser
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.manage().window().maximize();

		// Primary window handle ID
		String window1 = driver.getWindowHandle();
		System.out.println("The Primary window is : " + window1);
		driver.findElement(By.id("home")).click();
		
		

		// secondary window check
		Set<String> windowHandles = driver.getWindowHandles();
		java.util.List<String> allWindows = new ArrayList<String>(windowHandles);
		String window2 = allWindows.get(1);
		driver.switchTo().window(window2).close();
		System.out.println("The Secondary window is : " + window2);
		
		

		// to check the size of opened window
		System.out.println("To check the size of the Primary windows : " + allWindows.size());

		// Switch the control to Primary window 
		driver.switchTo().window(window1);
		

		// Open multiple window
		WebElement findElement = driver.findElement(By.xpath("//button[text()='Open Multiple Windows']"));
		findElement.click();
		System.out.println("How many window opened : " + windowHandles.size());		
		Set<String> windowsOpen = driver.getWindowHandles();
		java.util.List<String> lstWindowsOpen = new ArrayList<String>(windowsOpen);
		String openFirst = lstWindowsOpen.get(1);
		String openSecond = lstWindowsOpen.get(2);
		driver.switchTo().window(openFirst).close();
		driver.switchTo().window(openSecond).close();		
		driver.switchTo().window(window1);
		
		//wait for 5 seconds
		WebElement findElement2 = driver.findElement(By.id("color"));
		findElement2.click();
		Set<String> windowHandles2 = driver.getWindowHandles();
		java.util.List<String> fstWindow = new ArrayList<String>(windowHandles2);
		String FstNewWindow = fstWindow.get(1);
		String SecNewWindow = fstWindow.get(2);
		System.out.println("First New Window : " + FstNewWindow);
		System.out.println("Second New Window : " + SecNewWindow);
		driver.switchTo().window(FstNewWindow).close();
		driver.switchTo().window(SecNewWindow).close();

	}

}
