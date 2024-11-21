package org.karkakasadara.tests.viewelog;

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
		
		@Test(priority=3)

		public void clickViewElog() throws InterruptedException{
			
			System.out.println("The title is " +driver.getTitle());
			
			if (driver.getTitle().equals("Result Analysis | Admission Status Approval")) {
				
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'View elog')]"))).click();

			}
		
			else if(driver.getTitle().equals("Velammal Digital")) {
				
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'View elog')]"))).click();
			}
			
			else {
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'View elog')]"))).click();
			}	
		}
		
		@Test(priority=4)

		public void displayElogRecords() throws InterruptedException{
			
		//h3//b[contains(text(),'Grade: 6 B')]
		WebElement gradeElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'flex flex-auto flex-col w-full')]//h3//b")));
		String grade= gradeElement.getText();
		System.out.println("The grade is " +grade);

		//div[contains(@class,'gridlist')]
		WebElement dateElement=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'gridlist')]")));
		String date= dateElement.getText();
		System.out.println("The posted date is " +date);
		
			
		// Get the updated list of homework cards for this specific grade
	    List<WebElement> eachCard = driver.findElements(By.xpath("//div[contains(@class,'flex flex-col flex-auto p-6 bg-card shadow rounded-2xl overflow-hidden pt-6 px-5 pb-6 w-full pt-6 sm:col-span-6 mat-elevation-z0')]"));
	    System.out.println("Total no. of periods: " + eachCard.size());

	    for (int card=0;card<eachCard.size(); card++) {
	        try {
	            // Refresh the list of homework cards in each iteration to handle dynamic changes
	            eachCard = driver.findElements(By.xpath("//div[contains(@class,'flex flex-col flex-auto p-6 bg-card shadow rounded-2xl overflow-hidden pt-6 px-5 pb-6 w-full pt-6 sm:col-span-6 mat-elevation-z0')]"));

	            // Get the updated elements for postedDate and postedMessage
	            WebElement postedMessage = driver.findElement(By.xpath(".//div[contains(@class,'flex flex-col flex-auto p-6 bg-card shadow rounded-2xl overflow-hidden pt-6 px-5 pb-6 w-full pt-6 sm:col-span-6 mat-elevation-z0')]"));

	            String message = postedMessage.getText();
	            
	            System.out.println("Message: " + message);
	            
	            Thread.sleep(1000);
	 
	            }
	        catch (Exception e) {
	            System.out.println("Elog not found for today");
	        }
	        continue;
	    }
	    
	    
	    // Note field
	    
		WebElement noteField=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'flex flex-col flex-auto p-6 bg-card shadow rounded-2xl overflow-hidden pt-6 px-5 pb-6 w-full sm:col-span-6 mat-elevation-z0 my-2 mr-2 ng-star-inserted')]")));
	   
	    if(noteField.isDisplayed()) {
	    	
	    	String note= gradeElement.getText();
	    	System.out.println("The grade is " +note);
	    	
	    }
	    else {
	    	System.out.println("Note field not available");
	    }

		}
}
