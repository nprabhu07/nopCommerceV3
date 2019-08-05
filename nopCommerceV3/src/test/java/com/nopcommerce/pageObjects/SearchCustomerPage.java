package com.nopcommerce.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchCustomerPage {

	WebDriver ldriver;
	
	public SearchCustomerPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}
	
	//Locators
	By lnkCustomers_menu = By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
	By lnkCutomers_menuitem = By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");

	By srchEmail = By.xpath("//input[@id='SearchEmail']");
	By srchFName = By.xpath("//input[@id='SearchFirstName']");
	By srchLName = By.xpath("//input[@id='SearchLastName']");
	By srchMonOfBirth = By.xpath("//select[@id='SearchMonthOfBirth']");
	By srchDayOfBirth = By.xpath("//select[@id='SearchDayOfBirth']");
	By srchCompany = By.xpath("//input[@id='SearchCompany']");
	By srchIPAddress = By.xpath("//input[@id='SearchIpAddress']");
	
	By srchCustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
	By srchAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
	By srchRegistered = By.xpath("//li[contains(text(),'Registered')]");
	By srchGuests = By.xpath("//li[contains(text(),'Guests')]");
	By srchVendors = By.xpath("//li[contains(text(),'Vendors')]");
	
	By srchCustomers = By.xpath("//button[@id='search-customers']");
	
	//Actions
	public void clickOnCustomersMenu()
	{
		ldriver.findElement(lnkCustomers_menu).click();
	}
	
	public void clickOnCustomersMenuItem()
	{
		ldriver.findElement(lnkCutomers_menuitem).click();
	}
	
	public void searchCustomersByEmail(String email)
	{
		ldriver.findElement(srchEmail).clear();
		ldriver.findElement(srchEmail).sendKeys(email);
	}
	
	public void searchCustomersByFName(String fName)
	{
		ldriver.findElement(srchFName).clear();
		ldriver.findElement(srchFName).sendKeys(fName);
	}
	
	public void searchCustomersByLName(String lName)
	{
		ldriver.findElement(srchLName).clear();
		ldriver.findElement(srchLName).sendKeys(lName);
	}
	
	public void searchCustomersByDob(String month, String day)
	{
		Select m_drp = new Select(ldriver.findElement(srchMonOfBirth));
		m_drp.selectByVisibleText(month);
		Select d_drp = new Select(ldriver.findElement(srchDayOfBirth));
		d_drp.selectByVisibleText(day);
	}
	
	public void searchCustomersByCompany(String comName)
	{
		ldriver.findElement(srchCompany).sendKeys(comName);
	}
	
	public void searchCustomersByIPAddress(String ipaddress)
	{
		ldriver.findElement(srchIPAddress).sendKeys(ipaddress);
	}
	
	public void searchCustomersByCustomerRoles(String role) throws InterruptedException
	{
		if(!role.equals("Vendors"))  //If role is vendors should not delete Register as per req.
		{
			ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
		}

		ldriver.findElement(srchCustomerRoles).click();
		
		WebElement listItem;
		
		Thread.sleep(3000);

		if(role.equals("Administrators"))
		{
			listItem = ldriver.findElement(srchAdministrators);
		}
		else if(role.equals("Guests"))
		{
			listItem = ldriver.findElement(srchGuests);
		}
		else if(role.equals("Registered"))
		{
			listItem = ldriver.findElement(srchRegistered);
		}
		else if(role.equals("Vendors"))
		{
			listItem = ldriver.findElement(srchVendors);
		}
		else
		{
			listItem = ldriver.findElement(srchGuests);
		}
		
		//listItem.click();
		//Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click();", listItem);
	}
	
	public void clickOnSearch()
	{
		ldriver.findElement(srchCustomers).click();
	}
}
