package tests;

import org.testng.annotations.Test;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import base.TestBase;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;

	public LoginTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	/*
	 * @Test(priority =1) public void testPasswordDisplayed(){ boolean flag =
	 * loginPage.validatePasswordDisplayed(); Assert.assertTrue(flag); }
	 */

	@Test
	public void loginTest() {
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void verifyLoginSucessTest() {
		String homePageTitle = loginPage.verifyTitle();
		Assert.assertEquals(homePageTitle, "Amicus Cloud - Home", "title did not matched , error");
	}

	@Test
	public void verifyLogoutIcon() {
		assertTrue(loginPage.iconDisplayed());
	}

	@Test
	public void verifyLogoutSuccess() {
		loginPage.clickLogoutButton();
		String homePageTitle = loginPage.verifyTitle();
		Assert.assertEquals(homePageTitle, "Amicus Cloud", "title did not matched , error");
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}

}
