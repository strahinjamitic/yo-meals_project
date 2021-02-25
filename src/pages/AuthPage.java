package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement account() {
		return driver.findElement(By.className("after-arrow"));
	}

	public WebElement myAccount() {
		return driver.findElement(By.xpath("//*[@class=\"my-account-dropdown\"]/ul/li[1]"));
	}

	public WebElement logout() {
		return driver.findElement(By.xpath("//*[@class=\"my-account-dropdown\"]/ul/li[2]"));
	}

	public void logoutAccount() {
		this.account().click();
		this.logout().click();
	}

}
