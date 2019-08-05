package com.nopcommerce.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddCustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		driver.get(configPropObj.getProperty("baseURL"));
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		
		logger.info("****************** TC_AddCustomerTest_003 started *************************");
		AddCustomerPage addcust = new AddCustomerPage(driver);
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		addcust.clickOnAddNew();
		
		logger.info("****************** Providing Customer Details ******************************");
		String email = randomString() + "@bing.com";
		addcust.setEmail(email);
		addcust.setPassword("test456");
		addcust.setCustomerRoles("Guests");
		addcust.setManagerOfVendor("Vendor 1");
		addcust.setGender("Female");
		addcust.setFirstName("Mary");
		addcust.setLastName("Jones");
		addcust.setDob("10/05/1980");
		addcust.setCompanyName("busyQA");
		addcust.setAdminComment("This is for Testing .... ");
		addcust.clickOnSave();
		
		//Validation
		String msg = driver.findElement(By.tagName("body")).getText();
		
		if(msg.contains("The new customer has been added successfully"))
		{
			logger.info("******************* Customer added successfully **************************");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("**************** Customer NOT added successfully *************************");
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
		}
		logger.info("******************** TC_AddCustomerTest_003 Finished **************************");
	}
}
