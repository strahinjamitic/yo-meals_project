package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement searchProducts() {
		return driver.findElement(By.className("js-search-keywords"));
	}

	public WebElement product() {
		return driver.findElement(By.className("product-image"));
	}

	public WebElement quantity() {
		return driver.findElement(By.name("product_qty"));
	}

	public void addToCart(String quantity) {
		this.quantity().sendKeys(Keys.CONTROL + "a");
		this.quantity().sendKeys(quantity);
		driver.findElement(By.linkText("Add To Cart")).click();
	}

	public void favorite() {
		driver.findElement(By.className("favourite")).click();
	}
}
