package pages;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.TestBase;


public class LoginPage extends TestBase {
	WebDriverWait wait;
	
	@FindBy(id = "tbEmail")
	WebElement username;

	@FindBy(xpath = "//span[contains(text(),'Logout')]")
	WebElement logoutBtn;

	@FindBy(xpath = "//i[contains(text(),'exit_to_app')]")
	WebElement logoutIcon;

	@FindBy(id = "tbPwd")
	WebElement password;

	@FindBy(id = "btLogin")
	WebElement loginBtn;

	@FindBy(id = "cbRememberMe")
	WebElement ckRemember;// check box 1

	@FindBy(id = "cbTimeToLive")
	WebElement ckKeepLoggedIn; // checkbox 2

	@FindBy(id = "ddTimeToLive")
	WebElement dayDD; // dd 1

	@FindBy(xpath = "(//div[@class='ui-dialog-buttonset'])/button[1]")
	WebElement dialogBox;

	@FindBy(xpath = "(//div[@class='ui-dialog-buttonset'])/button")
	List<WebElement> dialogBoxWhole;

	// Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean validatePasswordDisplayed() {
		return password.isDisplayed();
	}

	public boolean validateDialogBox() {

		if (dialogBoxWhole.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void clickDialogContinue() {
		dialogBox.click();
	}

	public Boolean iconDisplayed() {
		return logoutIcon.isDisplayed();
	}

	public void waitForPageLoad() {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.titleContains("Amicus Cloud - Home"));
	}

	public HomePage login(String un, String pwd) {
		username.click();
		username.sendKeys(un);

		password.sendKeys(pwd);
		ckRemember.click();
		ckKeepLoggedIn.click();
		Select daysDrop = new Select(dayDD);
		daysDrop.selectByVisibleText("2 days");

		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginBtn);

		
		boolean flag = validateDialogBox();
		System.out.println("Dialog Box Status:--" + flag);

		if (flag == true) {
			clickDialogContinue();
			waitForPageLoad();
		} else {
			waitForPageLoad();
		}

		return new HomePage();

	}

	public void clickLogoutButton() {
		try {
			logoutBtn.click();
		} catch (Exception e) {
			JavascriptExecutor ex = (JavascriptExecutor) driver;
			ex.executeScript("arguments[0].click();", logoutBtn);
		}

	}

	public String verifyTitle() {
		return driver.getTitle();
	}

}
