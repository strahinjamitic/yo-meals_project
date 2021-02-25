package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement username() {
		return driver.findElement(By.name("user_first_name"));
	}

	public WebElement lastname() {
		return driver.findElement(By.name("user_last_name"));
	}

	public WebElement address() {
		return driver.findElement(By.name("user_address"));
	}

	public WebElement phone() {
		return driver.findElement(By.name("user_phone"));
	}

	public WebElement zip() {
		return driver.findElement(By.name("user_zip"));
	}

	public Select country() {
		WebElement country = driver.findElement(By.name("user_country_id"));
		Select selectCountry = new Select(country);
		return selectCountry;
	}

	public Select state() {
		WebElement state = driver.findElement(By.name("user_state_id"));
		Select selectState = new Select(state);
		return selectState;
	}

	public Select city() {
		WebElement city = driver.findElement(By.name("user_city"));
		Select selectCity = new Select(city);
		return selectCity;
	}

	public WebElement savebtn() {
		return driver.findElement(By.name("btn_submit"));
	}

	public WebElement camera() {
		return driver.findElement(By.className("upload"));
	}

	public void setPhoto() {
		js.executeScript("arguments[0].click()", this.camera());
	}

	public void updatePhoto(String photoUrl) {
		this.setPhoto();
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(photoUrl);
	}

	public WebElement delete() {
		return driver.findElement(By.className("remove"));
	}

	public void deletePhoto() {
		js.executeScript("arguments[0].click()", this.delete());
	}

	public void change() throws InterruptedException {
		this.username().clear();
		this.username().sendKeys("Str");
		this.lastname().clear();
		this.lastname().sendKeys("Str");
		this.address().clear();
		this.address().sendKeys("Aaaaa AAAA");
		this.phone().clear();
		this.phone().sendKeys("0987654321");
		this.zip().clear();
		this.zip().sendKeys("18000");
		this.country().selectByVisibleText("United States");
		Thread.sleep(3000);
		this.state().selectByVisibleText("Hawaii");
		Thread.sleep(3000);
		this.city().selectByVisibleText("Honolulu");
		Thread.sleep(3000);
		this.savebtn().click();
	}

}
