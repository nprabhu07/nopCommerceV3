package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.testBase.BaseClass;
import com.nopcommerce.utilities.XLUtils;

public class TC_AddCustomerDDT_007 extends BaseClass {
	
	@Test(dataProvider="NewCustData")
	public void addCustomerDDT()
	{
		
	}
	
	
	@DataProvider(name="NewCustomerData")
	public String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/TestData/TestData.xlsx";
		int rowcount = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String newCustData[][] = new String[rowcount][colcount];
		
		for(int i=1; i<=rowcount; i++)
		{
			for(int j=0; j<colcount; j++)
			{
				newCustData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return newCustData;
	}


}
