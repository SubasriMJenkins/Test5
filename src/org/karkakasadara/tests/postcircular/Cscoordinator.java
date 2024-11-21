package org.karkakasadara.tests.postcircular;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Cscoordinator {
	
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

	public void loginAsCscoordinator() throws InterruptedException{

		WebElement email=wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));

		email.click();
		email.sendKeys("democscoordinator@velammal.edu.in");

		WebElement password= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));

		password.click();

		password.sendKeys("csuat123");

		WebElement signIn=driver.findElement(By.xpath("//body/app-root[1]/layout[1]/empty-layout[1]/div[1]/div[1]/auth-sign-in[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));

		signIn.click();

		Thread.sleep(1000);
	}
	
	@Test(priority=3)

	public void clickPostCircular() throws InterruptedException{
		
		System.out.println("The title is " +driver.getTitle());
		
		if (driver.getTitle().equals("Result Analysis | Admission Status Approval")) {
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Post Circular')]"))).click();

		}
	
		else if(driver.getTitle().equals("Velammal Digital")) {
			
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication')]"))).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Post Circular')]"))).click();
		}
		
		else {
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Post Circular')]"))).click();
		}
	}
	
	@Test(priority=4)

	public void enterFields() throws InterruptedException{
		
		//Title
		
		WebElement title= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='mat-input-0']")));
		title.click();
		
		title.sendKeys("Testing post circular title 1");
		
		// Message
		
		WebElement message= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='mat-input-1']")));
		message.click();
		
		message.sendKeys("!------Testing through Cs-coordinator-----!");
		

	}
	
	@Test(priority=5)

	public void clickOnSubmit() throws InterruptedException{
		
		//driver.findElement(By.xpath("//body/app-root[1]/layout[1]/classy-layout[1]/div[1]/div[2]/app-post[1]/div[1]/div[1]/div[2]/div[1]/form[1]/span[1]/button[1]")).click();
		
	}

}
