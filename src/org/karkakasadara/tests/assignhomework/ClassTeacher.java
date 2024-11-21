package org.karkakasadara.tests.assignhomework;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ClassTeacher {
	
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
		
		@Test(priority=2)

		public void loginAsClassTeacher() throws InterruptedException{

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
		
		@Test(priority=3)

		public void clickViewCircular() throws InterruptedException{
			
			System.out.println("The title is " +driver.getTitle());
			
			if (driver.getTitle().equals("Result Analysis | Admission Status Approval")) {
				
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Assign Homework')]"))).click();

			}
		
			else if(driver.getTitle().equals("Velammal Digital")) {
				
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Assign Homework')]"))).click();
			}
			
			else {
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Assign Homework')]"))).click();
			}
		}
		
		@Test(priority=4)

		public void selectDateFromCalendar() throws InterruptedException{
			
			// Open the date picker
			WebElement datePickerToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-datepicker-toggle[contains(@data-mat-calendar, 'mat-datepicker-1')]")));      
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
		
		@Test(priority=5)

		public void chooseOptions() throws InterruptedException{
			
			// Grade
			WebElement gradeField= driver.findElement(By.xpath("//div[@id='mat-select-value-1']"));
			gradeField.click();
			
			driver.findElement(By.xpath("//span[contains(text(),'7')]")).click();
			
			// Section
			WebElement sectionField= driver.findElement(By.xpath("//div[@id='mat-select-value-3']"));
			sectionField.click();
			
			driver.findElement(By.xpath("//mat-option[@id='mat-option-5']")).click();// A
			
			
			WebElement subjectField= driver.findElement(By.xpath("//mat-select[@id='mat-select-4']"));
			
			// Create an Actions object
	        Actions actions = new Actions(driver);

	        // Perform double-click action on the element
	        actions.doubleClick(subjectField).perform();
			Thread.sleep(1000);
			// Subject	
			//subjectField.click();
			driver.findElement(By.xpath("//mat-option[@id='mat-option-6']")).click(); // Maths
			
		}
		
		@Test(priority=6)

		public void passMessage() throws InterruptedException{
			
			//Message field
			WebElement messageField= driver.findElement(By.xpath("//textarea[@id='mat-input-2']"));
			messageField.click();
			
			messageField.sendKeys("Test- Assigning homework for maths subject grade 7 A");
			
		}
		
		@Test(priority=7)

		public void clickProceed() throws InterruptedException{
			
			//driver.findElement(By.xpath("//body/app-root[1]/layout[1]/classy-layout[1]/div[1]/div[2]/app-add-homework[1]/div[1]/div[1]/mat-card[1]/form[1]/span[1]/button[1]/span[2]")).click();
			
		}

}
