package normalcode;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class calendar {

	static WebDriver driver;

	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));



	@BeforeTest

	public void setUpDriver() {

		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Subasri\\Downloads\\geckodriver-v0.35.0-win64\\geckodriver.exe");

		driver= new FirefoxDriver();

		driver.navigate().to("https://uat.karkakasadara.in/");

		wait= new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.manage().window().maximize();		

	}
	
	@Test(priority=1)

	public void loginAsAco() throws InterruptedException{

		WebElement email=wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));

		email.click();
		email.sendKeys("democlassteachernew@velammal.edu.in");

		WebElement password= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));

		password.click();

		password.sendKeys("ctuat123");

		WebElement signIn=driver.findElement(By.xpath("//body/app-root[1]/layout[1]/empty-layout[1]/div[1]/div[1]/auth-sign-in[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

		signIn.click();

		Thread.sleep(1000);
	}
	
	@Test(priority=2)

	public void viewAllHomework() throws InterruptedException{
		
		WebElement viewAllHomework= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'View All Homework')]")));
		viewAllHomework.click();
	}
	
	@Test(priority=3)

	public void selectDateFromCalendar() throws InterruptedException{
		
		// Open the date picker
		WebElement datePickerToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-datepicker-toggle[contains(@data-mat-calendar, 'mat-datepicker-0')]")));      
        datePickerToggle.click();	
        
		LocalDate currentDate = LocalDate.now();
        System.out.println("The current date of the month is: " + currentDate);
        
        // Calculate the date that is 2 days from the current date
        LocalDate targetday = currentDate.plusDays(2);
        System.out.println("After 2 days from the current day: " + targetday);     
        
        // Format the target date for the date picker
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        String formattedDate = targetday.format(formatter);
        System.out.println("The formatted Date is  :" + formattedDate);

       WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td//button[contains(@aria-label, '"+formattedDate+"')]")));
       dateElement.click();
	}
		
}
