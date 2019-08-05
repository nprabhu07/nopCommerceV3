package com.nopcommerce.testCases;

//Test Comment
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;
import com.nopcommerce.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider="LoginData")
	public void loginTest(String user, String pwd) throws InterruptedException, IOException
	{
		logger.info("******** Starting TC_LoginDDT_002 ******************");
		driver.get(configPropObj.getProperty("baseURL"));
		LoginPage lp = new LoginPage(driver);
		
		logger.info("********* Providing login details **************");
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		Thread.sleep(5000);
		
		String exp_title = "Dashboard / nopCommerce administration";
		String act_title = driver.getTitle();
		
		if(exp_title.equals(act_title))
		{
			logger.info("********* loginTest is Passed ****************");
			lp.clickLogout();
			Assert.assertTrue(true);
		}
		else
		{
			logger.warn("********** loginTest is Failed ****************");
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
		}
		
		logger.info("************* Finished TC_LoginDDT_002 *************");
	}
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/TestData/LoginData.xlsx";
		int rowcount = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String loginData[][] = new String[rowcount][colcount];
		
		for(int i=1; i<=rowcount; i++)
		{
			for(int j=0; j<colcount; j++)
			{
				loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return loginData;
	}
}
