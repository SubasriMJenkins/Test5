package org.karkakasadara.tests.viewallhomework;

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

public class AcoRole {

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
		email.sendKeys("demoaco@velammal.edu.in");

		WebElement password= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));

		password.click();

		password.sendKeys("acouat123");

		WebElement signIn=driver.findElement(By.xpath("//body/app-root[1]/layout[1]/empty-layout[1]/div[1]/div[1]/auth-sign-in[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

		signIn.click();

		Thread.sleep(1000);
	}
	
	@Test(priority=3)

	public void clickViewHomework() throws InterruptedException{
		
		System.out.println("The title is " +driver.getTitle());
		
		if (driver.getTitle().equals("Result Analysis | Admission Status Approval")) {
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'View All Homework')]"))).click();

		}
	
		else if(driver.getTitle().equals("Velammal Digital")) {
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'View All Homework')]"))).click();
		}
		
		else {
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'View All Homework')]"))).click();
		}
	}
	
	@Test(priority=4)

	public void selectDateFromCalendar() throws InterruptedException{
		
		// Open the date picker
		WebElement datePickerToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-datepicker-toggle[contains(@data-mat-calendar, 'mat-datepicker-0')]")));      
		datePickerToggle.click();	
		        
		LocalDate currentDate = LocalDate.now();
		System.out.println("The current date of the month is: " + currentDate);
		        
		// Calculate the date that is 2 days from the current date
		LocalDate targetday = currentDate.minusDays(6);
		System.out.println("After 2 days from the current day: " + targetday);     
		        
		// Format the target date for the date picker
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
		String formattedDate = targetday.format(formatter);
		System.out.println("The formatted Date is  :" + formattedDate);

		WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td//button[contains(@aria-label, '"+formattedDate+"')]")));
		dateElement.click();
	
	}
	
	@Test(priority=5)
	public void displayHomeworkRecords() throws InterruptedException{
	//List<WebElement> gradeList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//mat-expansion-panel")));
		
	boolean  hasGridIsDisplayed;
					
		do {
						
			// Assuming no grid is displayed
			hasGridIsDisplayed=false;
					
			//WebElement noData= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'No Record Found')]")));
					
			try {
					
				List<WebElement> gradeList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//mat-expansion-panel")));

				if(gradeList!=null) {
					
					System.out.println("No of HW posted today: " + gradeList.size());

					for (int i = 0; i < 2; i++) {
			        	
						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						// Re-fetch the elements due to DOM changes after navigation
						gradeList = driver.findElements(By.xpath("//mat-expansion-panel"));
						WebElement grade = gradeList.get(i);
			           
						if(grade.isDisplayed()) {
			            
							grade.click();
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-card")));
			            
							WebElement dateElement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-card")));
							String date = dateElement.getText();
							System.out.println(date);

							// Get the updated list of homework cards for this specific grade
							List<WebElement> eachHomeworkCard = driver.findElements(By.xpath("//mat-card[contains(@class,'mat-card mat-focus-indicator gridlistcard')]"));
							System.out.println("Total no. of HW posted: " + eachHomeworkCard.size());

							for (WebElement card: eachHomeworkCard) {
			            	
								try {
									
									// Refresh the list of homework cards in each iteration to handle dynamic changes
									eachHomeworkCard = driver.findElements(By.xpath("//mat-card[contains(@class,'mat-card mat-focus-indicator gridlistcard')]"));
			   
									// Get the updated elements for postedDate and postedMessage
					                 WebElement postedDate = card.findElement(By.xpath(".//div[@class='grid md:grid-cols-2 sm:grid-cols-1']"));
					                 WebElement postedMessage = card.findElement(By.xpath(".//div[@class='gridlistcard']"));

				                    String cardDate = postedDate.getText();
				                    String message = postedMessage.getText();

				                    System.out.println("Date: " + cardDate);
				                    Thread.sleep(1000);

				                    System.out.println("Message: " + message);
			                    
									Thread.sleep(1000);
									
								}
								catch (Exception e) {
									
									System.out.println("Homework not found for today or an error occurred.");
								}       	                
							}
			            
						}
			
					}
				}
				else {
					
					break;
				}
			}
					
			catch(Exception e) {
							
					System.out.println("No data found");
					break;
			}
		}
					
			while(hasGridIsDisplayed);
					
			System.out.println("All work completed!");
	}
	
}
