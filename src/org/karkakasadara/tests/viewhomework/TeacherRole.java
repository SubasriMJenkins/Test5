package org.karkakasadara.tests.viewhomework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TeacherRole {
	
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

		public void loginAsTeacher() throws InterruptedException{

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

		public void clickViewHomework() throws InterruptedException{
			
			System.out.println("The title is " +driver.getTitle());
			
			if (driver.getTitle().equals("Result Analysis | Admission Status Approval")) {
				
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'View Homework')]"))).click();

			}
		
			else if(driver.getTitle().equals("Velammal Digital")) {
				
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'View Homework')]"))).click();
			}
			
			else {
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'View Homework')]"))).click();
			}
		}
		
		@Test(priority=4)

		public void displayHomeworkRecords() throws InterruptedException{
			
		
			List<WebElement> gradeList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//mat-expansion-panel")));
	        System.out.println("No of HW posted today: " + gradeList.size());

	        for (int i = 0; i < gradeList.size(); i++) {
	        	
	        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            // Re-fetch the elements due to DOM changes after navigation
	        	gradeList = driver.findElements(By.xpath("//mat-expansion-panel"));
	            WebElement grade = gradeList.get(i);

	            grade.click();
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-card")));

	            // Get the updated list of homework cards for this specific grade
	            List<WebElement> eachHomeworkCard = driver.findElements(By.xpath("//mat-card"));
	            System.out.println("Total no. of HW posted: " + eachHomeworkCard.size());

	            for (WebElement card: eachHomeworkCard) {
	                try {
	                    // Refresh the list of homework cards in each iteration to handle dynamic changes
	                    eachHomeworkCard = driver.findElements(By.xpath("//mat-card"));
       
	                    // Get the updated elements for postedDate and postedMessage
	                    WebElement postedDate = card.findElement(By.xpath(".//div[@class='grid md:grid-cols-2 sm:grid-cols-1']"));
	                    WebElement postedMessage = card.findElement(By.xpath(".//div[@class='gridlistcard ng-star-inserted']"));

	                    String date = postedDate.getText();
	                    String message = postedMessage.getText();

	                    System.out.println("Date: " + date);
	                    Thread.sleep(1000);

	                    System.out.println("Message: " + message);
	                    Thread.sleep(1000);
	                    //}
	                    } catch (Exception e) {
	                    System.out.println("Homework not found for today or an error occurred.");
	                }
	            }

	            // Collapse the current grade card after processing its homework cards
	            //grade.click();
	        }
	    }
	
}
