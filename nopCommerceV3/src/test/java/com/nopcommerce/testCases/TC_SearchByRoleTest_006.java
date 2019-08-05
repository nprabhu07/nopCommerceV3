package com.nopcommerce.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.AddCustomerPage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.pageObjects.SearchCustomerPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_SearchByRoleTest_006 extends BaseClass {
	
	@Test
	public void searchByRole() throws InterruptedException, IOException
	{
		driver.get(configPropObj.getProperty("baseURL"));
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		
		logger.info("****************** TC_SearchByRoleTest_006 Started *************************");
		SearchCustomerPage scp = new SearchCustomerPage(driver);
		scp.clickOnCustomersMenu();
		scp.clickOnCustomersMenuItem();

		logger.info("****************** Providing Name Search Details ******************************");

		String rl = "Administrators";
		scp.searchCustomersByCustomerRoles(rl);
		scp.clickOnSearch();
		
		Thread.sleep(5000);
		
		//Validations
		String returnValue = driver.findElement(By.xpath("//table/tbody/tr[1]/td[4]")).getText();
		
		if(returnValue.contains(rl))
		{
			logger.info("******************* Customer searched successfully **************************");
			Assert.assertTrue(true);	
		}
		else
		{
			logger.error("**************** Customer NOT searched successfully *************************");
			captureScreen(driver, "searchByRole");
			Assert.assertTrue(false);
		}
		
		logger.info("******************** TC_SearchByRoleTest_006 Finished ****************************");
	}
}
