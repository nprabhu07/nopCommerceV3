package com.nopcommerce.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddCustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.pageObjects.SearchCustomerPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_SearchByEmailTest_004 extends BaseClass {

	@Test
	public void searchByEmail() throws IOException, InterruptedException
	{
		driver.get(configPropObj.getProperty("baseURL"));
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		
		logger.info("****************** TC_SearchByEmailTest_004 Started *************************");
		SearchCustomerPage scp = new SearchCustomerPage(driver);
		
		scp.clickOnCustomersMenu();
		scp.clickOnCustomersMenuItem();
		
		logger.info("****************** Providing Email Search Details ******************************");
		
		String emailAddress = "victoria_victoria@nopCommerce.com";
		scp.searchCustomersByEmail(emailAddress);
		scp.clickOnSearch();
		
		Thread.sleep(5000);
		
		//Validations
		String returnValue = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]")).getText();
		if(emailAddress.equals(returnValue))
		{
			logger.info("******************* Customer searched successfully **************************");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("**************** Customer NOT searched successfully *************************");
			captureScreen(driver, "searchByEmail");
			Assert.assertTrue(false);
		}
		
		logger.info("******************** TC_SearchByEmailTest_004 Finished ****************************");
	}
}
