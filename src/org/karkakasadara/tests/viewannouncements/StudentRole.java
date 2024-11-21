package org.karkakasadara.tests.viewannouncements;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StudentRole {

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

	public void loginAsAco() throws InterruptedException{

		WebElement email=wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));

		email.click();
		email.sendKeys("demostudentuat@velammal.edu.in");

		WebElement password= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));

		password.click();

		password.sendKeys("studentuat");

		WebElement signIn=driver.findElement(By.xpath("//body/app-root[1]/layout[1]/empty-layout[1]/div[1]/div[1]/auth-sign-in[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

		signIn.click();

		Thread.sleep(1000);
	}
	
	@Test(priority=3)

	public void clickViewCircular() throws InterruptedException{

			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'View Announcements')]"))).click();
		
	}
	
	@Test(priority=4)

	public void displayTodayAnnouncementRecords() throws InterruptedException{
		
		List<WebElement> cardsList= wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'flex flex-col h-auto shadow rounded-2xl overflow-hidden bg-card ng-star-inserted')]")));
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate currentDate = LocalDate.now();
	    String formattedDate = currentDate.format(dateFormatter);
	    System.out.println("The current date is: " + formattedDate);
	   
		for(int eachCard=0;eachCard<cardsList.size();eachCard++) {

			WebElement dateInEachCard= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[contains(@class,'text-neutral-500 text-lg')][" + (eachCard + 1) + "]")));
			// Extract card date text
            String cardDateTimeText = dateInEachCard.getText();
            System.out.println("The card date with time is : " + cardDateTimeText);

            // Extract card date
            String cardDateText = cardDateTimeText.split(",")[0].trim();
            LocalDate cardDate = LocalDate.parse(cardDateText, dateFormatter);
            System.out.println("The card date is: " + cardDate);

            // Format cardDate to match formattedDate pattern
            String cardDateFormatted = cardDate.format(dateFormatter);
            System.out.println("The formatted card date is " +cardDateFormatted);

            // Compare cardDateFormatted with formattedDate
            if (cardDateFormatted.equals(formattedDate)) {
                // Find message element for the current card
                WebElement messageElement = driver.findElement(By.xpath("(//div[contains(@class,'flex flex-col h-auto shadow rounded-2xl overflow-hidden bg-card ng-star-inserted')])[" + (eachCard + 1) + "]"));

                // Get message text
                String message = messageElement.getText();
                System.out.println("Message: " + message);

                // Optionally, break out of the loop if you only want to print the first matching card
                // break;
            }
            else {
            	
            	System.out.println("No Announcement posted today");
            	break;
            }
		}
	}
	
}
