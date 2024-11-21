package org.karkakasadara.tests.postdlm;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KgClassTeacher {
	
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
			email.sendKeys("democlassteacherkg@velammal.edu.in");

			WebElement password= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));

			password.click();

			password.sendKeys("classteacherkg");

			WebElement signIn=driver.findElement(By.xpath("//body/app-root[1]/layout[1]/empty-layout[1]/div[1]/div[1]/auth-sign-in[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

			signIn.click();

			Thread.sleep(1000);
		}
		
		@Test(priority=3)

		public void clickViewHomework() throws InterruptedException{
			
			System.out.println("The title is " +driver.getTitle());
			
			if (driver.getTitle().equals("Result Analysis | Admission Status Approval")) {
				
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Post Daily Learning Moments')]"))).click();

			}
		
			else if(driver.getTitle().equals("Velammal Digital")) {
				
				//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Post Daily Learning Moments')]"))).click();
			}
			
			else {
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Post Daily Learning Moments')]"))).click();
			}
		}
		
		/*@Test(priority=4)
		public void selectGradeSection() throws InterruptedException {
			
			WebElement grade = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-select[@id='mat-select-0']")));
			grade.click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'6')]"))).click();
			
			WebElement section = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-select[@id='mat-select-2']")));
			section.click();
			
			driver.findElement(By.xpath("//mat-option[@id='mat-option-12']")).click(); // A
		}
		*/
		
		@Test(priority=5)
		public void enterPeriodwiseRecords() throws InterruptedException {
			
			try {
				
			wait= new WebDriverWait(driver, Duration.ofSeconds(10));

		    String[] periodNames = {"Period :1", "Period :2", "Period :3", "Period :4", 
		                            "Period :5", "Period :6", "Period :7", "Period :8"};
		    //String[] subjects = {" 2nd L Tamil ", "Numeracy", "Circle time", "Skill", "Extended Activities", "Literacy", "Numeracy", "Circle time"};
		   // String[] subjectIds = {"0", "1", "2", "3", "4", "0", "1", "2"};
		    String[] learningEngagements = {"Test Literacy", "Test Numeracy", "Test Circle time", 
		                                    "Test skill", "Extended Activities", "Test Literacy", 
		                                    "Test Numeracy", "Test Circle time"};
		    String activityText = "Testing Daily Learning Moments screen through KG Class Teacher";
		    
		    int subjectFieldIndex = 1;
	        int subjectOptionId= 0;
	        int learningActivitiesFieldIndex = 2;
	        int todayHomeConnectFieldIndex = 3;

		    for (int i = 0; i < periodNames.length; i++) {
		    	
		    	try {
		    		
		    	wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		        WebElement periodName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'" + periodNames[i] + "')]")));

		        if (periodName != null) {

		            WebElement subjectField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='mat-select-value-"+subjectFieldIndex+"']")));
		            subjectField.click();

		            // Use explicit wait for the subject options to be clickable
		            WebElement subjectOption = wait.until(ExpectedConditions.elementToBeClickable(
		                    By.xpath("//mat-option[@id='mat-option-"+(subjectOptionId % 6)+"']")));
		            subjectOption.click();

		            WebElement learningEngagementField = driver.findElement(By.xpath("//textarea[@id='mat-input-" + learningActivitiesFieldIndex + "']"));
		            learningEngagementField.click();
		            learningEngagementField.sendKeys(learningEngagements[i]);

		            WebElement todayActivityField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='mat-input-" + todayHomeConnectFieldIndex + "']")));
		            todayActivityField.click();
		            todayActivityField.sendKeys(activityText);
		            
		            // Increment the field indexes for the next iteration
		            subjectFieldIndex += 2;
		            subjectOptionId +=6;
		            learningActivitiesFieldIndex += 2;
		            todayHomeConnectFieldIndex += 2;

		        }
		    }
		    	catch(Exception e) {
		    		
		    		System.out.println("moving to next case");
		    		break;
		    	}
		     }
			}
	    	catch(Exception e) {
	    		
	    		System.out.println("Error in running " +e.getMessage());
	    		
	    	}
		}
		
		@Test(dependsOnMethods = {"enterPeriodwiseRecords"}, alwaysRun = true)
		public void clickSaveandPreview() throws InterruptedException {
			
			//WebElement save= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'mat-focus-indicator mr-4 mat-raised-button mat-button-base mat-primary')]")));
			WebElement preview=  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Preview')]")));
			
			
			if(preview.isEnabled()) {
				
				Actions actions = new Actions(driver);

		        // Perform double-click on the element
		        actions.doubleClick(preview).perform();
		
				//preview.click();
			}			
		}		
}
