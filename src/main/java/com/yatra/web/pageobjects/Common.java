package com.yatra.web.pageobjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class Common {

	public static final String CONFIGFILEPATH = "\\src\\test\\resources\\config\\config.properties";
    public static final String YATRA_WEBBASEURL = "https://yatra.com";
    public static final String EXPECTED_PAYMENT_PAGETITLE = "Yatra.com | Book your flight in 3 simple steps";
	

	static Properties prop;
	static String password;

	public static Properties get_PropertiesFilesData() throws IOException {
		prop = new Properties();
		InputStream inStream = new FileInputStream(System.getProperty("user.dir") + Common.CONFIGFILEPATH);
		prop.load(inStream);
		return prop;
	}

	public static boolean isElementPresent(WebDriver driver, By element) {
		try {
			if (!(driver.findElement(element).getSize() == null)) {
				return true;
			}
		} catch (NoSuchElementException e) {
			return false;
		}
		return false;
	}

	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, Integer.valueOf(prop.getProperty("day")));
		String Date = formatter.format(cal.getTime());
		return Date;
	}

	public static void switchToParentWindow(WebDriver driver) throws InterruptedException {
		// Get current window handle
		String parentWindow = driver.getWindowHandle();
		Set<String> winHandles = driver.getWindowHandles();
		for (String handle : winHandles) {
			if (!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				Thread.sleep(1000);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}

}
