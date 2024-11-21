package org.karkakasadara.tests.viewhomework;

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

public class Student {
	
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

	public void loginAsStudent() throws InterruptedException{

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

	public void clickViewHomework() throws InterruptedException{
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'View Homework')]"))).click();
		
	}
	
	@Test(priority=4)

	public void displayRecords() throws InterruptedException{
		
		LocalDate currentDate= LocalDate.now();
		System.out.println("Current date is " +currentDate);
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedDate = currentDate.format(dateFormatter);
		System.out.println("Current date is " +formattedDate);
		
		WebElement dateElement= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(@class, 'font-bold pt-5')]")));
		
		if(formattedDate.equals(dateElement.getText())) {

			List<WebElement> eachHomeworkCard = driver.findElements(By.xpath("//mat-card[contains(@class,'mat-card mat-focus-indicator gridlistcard')]"));
            System.out.println("Total no. of HW posted: " + eachHomeworkCard.size());
            
            if(!eachHomeworkCard.isEmpty()) {

            for (int card = 0; card < eachHomeworkCard.size(); card++) {
                try {
                    // Refresh the list of homework cards in each iteration to handle dynamic changes
                    eachHomeworkCard = driver.findElements(By.xpath("//mat-card[contains(@class,'mat-card mat-focus-indicator gridlistcard')]"));
             
                    WebElement postedMessage = driver.findElement(By.xpath("(//mat-card[contains(@class,'mat-card mat-focus-indicator gridlistcard')])[" + (card + 1) + "]"));
           
                    String message = postedMessage.getText();
                    System.out.println("Message: " + message);
                    
                    Thread.sleep(1000);
                   
                    } 
                catch (Exception e) {
                    System.out.println("Homework not found for today or an error occurred.");
                }
            }
            }
            else {
            	
            	System.out.println("No data found");
            	// Click previous button
        		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@class,'mat-focus-indicator mat-raised-button mat-button-base mat-primary ng-star-inserted')]"))).click();
        		System.out.println("So clicked previous button");  
        	
            }
		
		}
		
	}
	
}
