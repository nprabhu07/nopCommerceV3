package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException
	{
		logger.info("********** Staring TC_LoginTest_001 ************");
		driver.get(configPropObj.getProperty("baseURL"));
		LoginPage lp = new LoginPage(driver);
		
		logger.info("********** Proividing login details ************");
		lp.setUserName(configPropObj.getProperty("useremail"));
		lp.setPassword(configPropObj.getProperty("password"));
		lp.clickLogin();
		
		logger.info("********** Validating login ********************");
		String exp_title = "Dashboard / nopCommerce administration";
		String act_title = driver.getTitle();
		
		if(exp_title.equals(act_title))
		{
			logger.info("******* Login Test is Passed ***************");
			Assert.assertTrue(true);
		}
		else
		{
			logger.warn("******* Login Test is Failed ***************");
			//captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
		}
		
		logger.info("*********** Finished TC_LoginTest_001 **********");
	}

}
