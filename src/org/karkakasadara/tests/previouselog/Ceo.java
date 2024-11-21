package org.karkakasadara.tests.previouselog;

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

public class Ceo {
		
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
						email.sendKeys("democeo@velammal.edu.in");

						WebElement password= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));

						password.click();

						password.sendKeys("ceouat123");

						WebElement signIn=driver.findElement(By.xpath("//body/app-root[1]/layout[1]/empty-layout[1]/div[1]/div[1]/auth-sign-in[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

						signIn.click();

						Thread.sleep(1000);
					}
					
				@Test(priority=3)

				public void clickViewHomework() throws InterruptedException{
						
						System.out.println("The title is " +driver.getTitle());
						
						if (driver.getTitle().equals("Result Analysis | Admission Status Approval")) {
							
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
							
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Previous elog')]"))).click();

						}
					
						else if(driver.getTitle().equals("Velammal Digital")) {
							
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
							
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Previous elog')]"))).click();
						}
						
						else {
							
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Previous elog')]"))).click();
						}
					}
					
				@Test(priority=4)

				public void selectFields() throws InterruptedException{
					
					//Syllabus
					WebElement syllabusField=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-select[@id='mat-select-2']")));
					syllabusField.click();
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-option[@id='mat-option-2']"))).click();// NewGen
					
					// School
					WebElement schoolField=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='mat-select-value-5']")));
					schoolField.click();
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-option[@id='mat-option-5']"))).click(); // VNGS-MDM
						
					// Grade
					WebElement gradeField=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='mat-select-value-7']")));
					gradeField.click();
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-option[@id='mat-option-28']"))).click(); // 5
						
					// section
					WebElement sectionField=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-select[@id='mat-select-0']")));
					sectionField.click();
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-option[@id='mat-option-36']"))).click(); // A
					
					}
					
				@Test(priority=5)

				public void selectDate() throws InterruptedException{
									
						// Open the date picker
						WebElement datePickerToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-datepicker-toggle[contains(@data-mat-calendar, 'mat-datepicker-0')]")));      
						datePickerToggle.click();	
								        
						LocalDate currentDate = LocalDate.now();
						System.out.println("The current date of the month is: " + currentDate);
								        
						// Calculate the date that is 2 days from the current date
						LocalDate targetday = currentDate.minusDays(0);
						System.out.println("After 2 days from the current day: " + targetday);     
								        
						// Format the target date for the date picker
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
						String formattedDate = targetday.format(formatter);
						System.out.println("The formatted Date is  :" + formattedDate);

						WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td//button[contains(@aria-label, '"+formattedDate+"')]")));
						dateElement.click();		
						
						
					}
					
				@Test(priority=6)

				public void displayPreviousElogRecords() throws InterruptedException{
					
				boolean  hasGridIsDisplayed;
						
						do {
										
							// Assuming no grid is displayed
							hasGridIsDisplayed=false;
									
							//WebElement noData= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'No Record Found')]")));
									
							try {
									
								List<WebElement> gradeList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'flex flex-col flex-auto p-6 bg-card shadow rounded-2xl overflow-hidden pt-6 px-5 pb-6 w-full pt-6 sm:col-span-6 mat-elevation-z0')]")));

								if(gradeList!=null) {		
									
									System.out.println("No of HW posted today: " + gradeList.size());
									
									// Date & day
									WebElement dateDay=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'gridlist')]")));
									System.out.println("The posted date & day is " +dateDay.getText());
									
									
									for (int i = 0; i < 2; i++) {
							        	
										wait = new WebDriverWait(driver, Duration.ofSeconds(10));
										// Re-fetch the elements due to DOM changes after navigation
										gradeList = driver.findElements(By.xpath("//div[contains(@class,'flex flex-col flex-auto p-6 bg-card shadow rounded-2xl overflow-hidden pt-6 px-5 pb-6 w-full pt-6 sm:col-span-6 mat-elevation-z0')]"));
										WebElement grade = gradeList.get(i);
							           
										if(grade.isDisplayed()) {
							            
											//grade.click();
											//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'flex flex-col flex-auto p-6 bg-card shadow rounded-2xl overflow-hidden pt-6 px-5 pb-6 w-full pt-6 sm:col-span-6 mat-elevation-z0')]")));	

											// Get the updated list of homework cards for this specific grade
											List<WebElement> eachHomeworkCard = driver.findElements(By.xpath("//div[contains(@class,'flex flex-col flex-auto p-6 bg-card shadow rounded-2xl overflow-hidden pt-6 px-5 pb-6 w-full pt-6 sm:col-span-6 mat-elevation-z0')]"));
											System.out.println("Total no. of HW posted: " + eachHomeworkCard.size());

							
							            	
												try {
													
													// Refresh the list of homework cards in each iteration to handle dynamic changes
								                    eachHomeworkCard = driver.findElements(By.xpath("//div[contains(@class,'flex flex-col flex-auto p-6 bg-card shadow rounded-2xl overflow-hidden pt-6 px-5 pb-6 w-full pt-6 sm:col-span-6 mat-elevation-z0')]"));
								   
								                    // Get the updated elements for postedDate and postedMessage
								                    WebElement postedMessage = driver.findElement(By.xpath("(//div[@class='grid grid-cols-2 gap-4'])["+(i+1)+"]"));

								                    String message = postedMessage.getText();
								                    
								                    System.out.println("Message: " + message);
								                    
								                    Thread.sleep(1000);
													
												}
												catch (Exception e) {
													
													System.out.println("Homework not found for today or an error occurred.");
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
