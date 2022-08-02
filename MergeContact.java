package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {

		/*
		 * //Pseudo Code
		 * 
		 * 1. Launch URL "http://leaftaps.com/opentaps/control/login" * 2. Enter
		 * UserName and Password Using Id Locator * 3. Click on Login Button using Class
		 * Locator * 4. Click on CRM/SFA Link * 5. Click on contacts Button * 6. Click
		 * on Merge Contacts using Xpath Locator * 7. Click on Widget of From Contact *
		 * 8. Click on First Resulting Contact * 9. Click on Widget of To Contact * 10.
		 * Click on Second Resulting Contact * 11. Click on Merge button using Xpath
		 * Locator * 12. Accept the Alert * 13. Verify the title of the page
		 */

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// Launch the browser
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();

		// Adding wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// To enter the username and password
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		System.out.println("username and password provided successfully");

		// To click on submit button
		driver.findElement(By.className("decorativeSubmit")).click();

		// To click on on CRM/SFA link
		driver.findElement(By.linkText("CRM/SFA")).click();

		// clicking on create contact
		driver.findElement(By.linkText("Create Contact")).click();

		// clicking on Merge contact
		driver.findElement(By.xpath("//a[@href='/crmsfa/control/mergeContactsForm']")).click();

		// Click on Widget of From Contact
		driver.findElement(By.xpath("//span[text()='From Contact']/following::img")).click();
		
		// get the primaryWindow
		String window1 = driver.getWindowHandle();
		
		//using set and list we can find the no of windows and we can handle
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("The number of window handles are : " + windowHandles.size());
		java.util.List<String> allWindow = new ArrayList<String>(windowHandles);
		String secondWindowHandle = allWindow.get(1);

		// to move the control to the next Window handle
		driver.switchTo().window(secondWindowHandle);
		System.out.println("Second Window Title is : " + driver.getTitle());

		// clicking on the first resulting contact
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		System.out.println("First Resulting contact is clicked");
		
		// to move the control to the next Window handle
		driver.switchTo().window(window1);
		driver.findElement(By.xpath("//span[text()='To Contact']/following::img")).click();
		System.out.println("clicked the TO contact");

		// switching to second window
		Set<String> windowHandles2 = driver.getWindowHandles();
		java.util.List<String> allWindow2 = new ArrayList<String>(windowHandles2);
		String secondWindowHandle2 = allWindow2.get(1);
		
		// to move the control to the next Window handle
		driver.switchTo().window(secondWindowHandle2);
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();

		// to move the control to the next Window handle
		driver.switchTo().window(window1);
		
		// clikcing on merge button
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();

		// Get the text from alert
		Alert alert = driver.switchTo().alert();
		System.out.println("The text of the alert is : " + alert.getText());
		alert.accept();
		Thread.sleep(3000);
		System.out.println(driver.getTitle());

	}

}
