package com.nopcommerce.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddCustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.pageObjects.SearchCustomerPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_SearchByNameTest_005 extends BaseClass {

	@Test
	public void searchByName() throws IOException, InterruptedException
	{
		driver.get(configPropObj.getProperty("baseURL"));
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		
		logger.info("****************** TC_SearchByNameTest_005 Started *************************");
		SearchCustomerPage scp = new SearchCustomerPage(driver);
		
		scp.clickOnCustomersMenu();
		scp.clickOnCustomersMenuItem();

		logger.info("****************** Providing Name Search Details ******************************");

		String fname = "James";
		String lname = "Pan";
		
		scp.searchCustomersByFName(fname);
		scp.searchCustomersByLName(lname);
		scp.clickOnSearch();
		
		Thread.sleep(5000);
		
		//Validations
		String returnValue = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]")).getText();
		if(returnValue.contains(fname+" "+lname))
		{
			logger.info("******************* Customer searched successfully **************************");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("**************** Customer NOT searched successfully *************************");
			captureScreen(driver, "searchByName");
			Assert.assertTrue(false);
		}
		logger.info("******************** TC_SearchByNameTest_005 Finished ****************************");
	}
}
