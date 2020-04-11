package com.yatra.web.tests;

import java.io.IOException;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.yatra.web.pageobjects.CheckoutProcess;
import com.yatra.web.pageobjects.Common;

public class TestCases extends TestBaseClass {

	CheckoutProcess checkoutProcess_object;
	private static final Logger LOGGER = Logger.getLogger(TestCases.class.getName());
	static String actual_PaymentPageTitle;

	@BeforeClass
	public void init() {
		TestBaseClass.setup(Common.YATRA_WEBBASEURL);
	}

	@Test(priority = 1, enabled = true)
	public void checkoutProcessForGuestUser() throws InterruptedException, IOException {
		LOGGER.info("Scenario 1 is running");
		checkoutProcess_object = new CheckoutProcess(driver);
		actual_PaymentPageTitle = checkoutProcess_object.verify_checkOutProcessfor_GuestUser();
		Assert.assertEquals(actual_PaymentPageTitle, Common.EXPECTED_PAYMENT_PAGETITLE);

	}

}
