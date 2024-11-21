package org.karkakasadara.tests.reviewhomework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Principal {

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

		public void loginAsPrincipal() throws InterruptedException{

			WebElement email=wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));

			email.click();
			email.sendKeys("demoprincipal@velammal.edu.in");

			WebElement password= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));

			password.click();

			password.sendKeys("principaluat");

			WebElement signIn=driver.findElement(By.xpath("//body/app-root[1]/layout[1]/empty-layout[1]/div[1]/div[1]/auth-sign-in[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

			signIn.click();

			Thread.sleep(1000);
		}
		
		@Test(priority=3)

		public void clickReviewHomework() throws InterruptedException{
			
			System.out.println("The title is " +driver.getTitle());
			
			if (driver.getTitle().equals("Result Analysis | Admission Status Approval")) {
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Review Homework')]"))).click();

			}
		
			else if(driver.getTitle().equals("Velammal Digital")) {
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Review Homework')]"))).click();
			}
			
			else {
				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Review Homework')]"))).click();
			}
		}
		
		@Test(priority=4)
		
		public void clickOnEachGrade() throws InterruptedException {
			
		    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    boolean hasPendingWork;

		    do {
		        // Assume no pending work left until we find one
		        hasPendingWork = false;

		     try {
		        	
		        
		        // Re-locate all grade dropdowns after each refresh
		        List<WebElement> gradeList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//mat-expansion-panel")));
		        System.out.println("Total classes found: " + gradeList.size());
		        
		        
		        // Iterate over each grade dropdown
		        for (int i = 0; i < gradeList.size(); i++) {
		            // Re-locate gradeList in case of a refresh after approval
		            gradeList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//mat-expansion-panel")));
		            WebElement currentGrade = gradeList.get(i);

		            if (currentGrade.isDisplayed()) {
		                currentGrade.click();
		                Thread.sleep(1000);  // Wait for homework cards to load

		                // Locate homework cards within the current grade dropdown
		                List<WebElement> homeworkCards = driver.findElements(By.xpath("//mat-card[contains(@class,'mat-card mat-focus-indicator bg-default pb-2 ng-star-inserted')]"));
		                System.out.println("Total homework posted: " + homeworkCards.size());

		                for (WebElement homeworkCard : homeworkCards) {
		                    try {
		                        WebElement status = homeworkCard.findElement(By.xpath(".//mat-card-title[contains(@class,'mat-card-title rjct')]"));

		                        if ("Pending".equals(status.getText())) {
		                            hasPendingWork = true;
		                            
		                            /*String grade= homeworkCard.findElement(By.xpath(".//mat-panel-title//b")).getText();
		                            System.out.println("Grade is " + grade);
		                            
		                            String subject= homeworkCard.findElement(By.xpath(".//mat-panel-title//b")).getText();
		                            System.out.println("Subject is " + subject);*/

		                            // Print message from the card
		                            String message = homeworkCard.findElement(By.xpath(".//mat-card-content")).getText();
		                            System.out.println("Message: " + message);
		                            
		                            //System.out.println("Homework approved for the " + grade + " and subject " + subject);

		                            // Click on approve button
		                            WebElement approveButton = homeworkCard.findElement(By.xpath(".//button[contains(@class,'bg-green-600')]"));
		                            approveButton.click();

		                            // Handle confirmation popup if it appears
		                            WebElement popup = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//mat-dialog-container[@id='mat-dialog-0']")));
		                            	if (popup.isDisplayed()) {
		                            		driver.findElement(By.xpath("//button[contains(@class,'mat-focus-indicator mat-flat-button mat-button-base mat-primary')]")).click();
		                            }

		                            // Break inner loop to restart from the first grade after page refresh
		                            break;
		                        }
		                    } 
		                    catch (StaleElementReferenceException e) {
		                        System.out.println("Stale element detected, re-fetching elements.");
		                        break;
		                    } 
		                    catch (Exception e) {
		                        System.out.println("No Pending homework found or an error occurred.");
		                    }
		                }

		             // If no pending work is found, print "No data found"
		                if (!hasPendingWork) {
		                    System.out.println("No data found");
		                    break;  // Exit the loop if no "Pending" homework was found
		                }
		                
		            }  
		         }
		       }                  
		       
		       catch (Exception e) {
		                // Handle case when elements are not found within the timeout
		                //System.out.println("Timeout while waiting for grade list or elements.");
		            System.out.println("No data found");
		            break;
		           
		       }
		    } while (hasPendingWork);  // Repeat until no "Pending" homework is found
		    
		}
}
