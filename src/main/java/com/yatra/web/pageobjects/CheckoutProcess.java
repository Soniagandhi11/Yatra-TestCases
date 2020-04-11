package com.yatra.web.pageobjects;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutProcess {
	static WebDriver driver;
	Properties prop;
	WebDriverWait wait;
	Select dropDown;
	JavascriptExecutor javascript;

	// xpath
	@FindBy(xpath = "//a[@id='booking_engine_flights'][contains(@title,'Flights')]")
	WebElement flights;
	@FindBy(xpath = "//ul[@class='selc-flight-options']//a[contains(@title, 'One Way')]")
	WebElement oneWay;
	@FindBy(xpath = "//input[@id='BE_flight_origin_city'][@name='flight_origin']")
	WebElement flight_departure;
	@FindBy(xpath = "//input[@id='BE_flight_arrival_city'][@name='flight_destination']")
	WebElement flight_destination;
	@FindBy(xpath = "//input[@id='BE_flight_origin_date']")
	WebElement departure_Date;
	@FindBy(xpath = "//input[@class='js_ripple search-btn '][@id='BE_flight_flsearch_btn']")
	WebElement search_flights;
	@FindBy(xpath = "//div[@class='dflex filter-box']//a[1]//i")
	WebElement nonStop_Checkbox;
	@FindBy(xpath = "//p[@class='pr uprcse option-label inline-block cursor-pointer '][contains(text(),'Duration')]")
	WebElement sorting_Duration;
	@FindBy(xpath = "//div[contains(@class,'flight-seg col-12')]//div[1]//div[1]//div[1]//div[4]//div[1]//div[2]//button[1]")
	WebElement bookNow;
	@FindBy(xpath = "//input[@id='additionalContactEmail'][contains(@placeholder,'Email ID')]")
	WebElement emailID;
	@FindBy(xpath = "//input[@id='additionalContactMobile'][contains(@placeholder,'Mobile Number')]")
	WebElement mobile_Number;
	@FindBy(xpath = "//select[@name='title0']")
	WebElement title_dropDown;
	@FindBy(xpath = "//input[@placeholder='First Name'][@name='fname0']")
	WebElement first_Name;
	@FindBy(xpath = "//input[@placeholder='Last Name'][@name='lname0']")
	WebElement last_Name;
	@FindBy(xpath = "//label[@class='align-checkbox save-child fs-base']//i[@class='ytfi-checker']")
	WebElement contribution_AddOn;
	@FindBy(xpath = "//button[@id='gtm_saveflightreview']")
	WebElement proceedto_Payment;
	@FindBy(xpath = "//input[contains(@class,'fs-md button primary rounded remove')][contains(@value,'Risk My Travel')]")
	WebElement risk_myTravel;

	// Constructor
	public CheckoutProcess(WebDriver driver) throws IOException {
		CheckoutProcess.driver = driver;
		prop = Common.get_PropertiesFilesData();
		PageFactory.initElements(driver, this);
		javascript = (JavascriptExecutor) driver;

	}

	// This function is used for verify the checkout Process of flights for Guest user
	public String verify_checkOutProcessfor_GuestUser() throws InterruptedException {
		this.selectFlights_BasisOnSearchCriteria();
		Common.switchToParentWindow(driver);
		dropDown = new Select(title_dropDown);
		dropDown.selectByValue("Mr");
		first_Name.sendKeys(prop.getProperty("firstName"));
		last_Name.sendKeys(prop.getProperty("lastName"));
		Thread.sleep(6000);
		emailID.sendKeys(prop.getProperty("emailId"));
		mobile_Number.sendKeys(prop.getProperty("mobileNumber"));
		javascript.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		if (contribution_AddOn.isEnabled()) {
			contribution_AddOn.click();
		}
		proceedto_Payment.click();
		risk_myTravel.click();
		return (driver.getTitle());
	}

	// This function select the flight basis on Search Criteria
	public void selectFlights_BasisOnSearchCriteria() throws InterruptedException {

		if (!(flights.isSelected())) {
			flights.click();
		}
		if (!(oneWay.isSelected())) {
			oneWay.click();
		}
		flight_departure.clear();
		Thread.sleep(2000);
		flight_departure.sendKeys(prop.getProperty("fromlocation"));
		Thread.sleep(4000);
		flight_departure.sendKeys(Keys.ENTER);
		flight_destination.clear();
		flight_destination.sendKeys(prop.getProperty("tolocation"));
		Thread.sleep(4000);
		flight_destination.sendKeys(Keys.ENTER);
		departure_Date.click();
		driver.findElement(By.xpath("//td[@id='" + Common.getDate() + "']")).click();
		nonStop_Checkbox.click();
		search_flights.click();
		sorting_Duration.click();
		bookNow.click();

	}

}
