package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LocationPopupPage extends BasicPage {

	public LocationPopupPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getLocation() {
		return driver.findElement(By.className("location-selector"));
	}

	public WebElement closeBtn() {
		return driver.findElement(By.className("close-btn"));
	}

	public WebElement getKeyword() {
		return driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}

	public WebElement getLocationItem(String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}
	
	public void clickLocation() {
		this.getLocation().click();
	}
	
	public void setLocation(String locationName) {
		this.getKeyword().click();
		String a = this.getLocationItem(locationName).getAttribute("data-value");
		js.executeScript("arguments[0].value= arguments[1]", this.getLocationInput(), a);
		js.executeScript("arguments[0].click()", this.getSubmit());
	}
	
	public void close() {
		this.closeBtn().click();
	}

}
