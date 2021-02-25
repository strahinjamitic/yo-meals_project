package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}
	
	public List<WebElement> listOfProducts(){
		return driver.findElements(By.xpath("//*[@class='product-name']/a"));
	}
	
	public ArrayList<String> nameOfProducts() {
		String name = null;
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < this.listOfProducts().size(); i++) {
			name = this.listOfProducts().get(i).getText();
			names.add(name);
		}
		return names;
	}
	
	public int numbreOfProducts() {
		return this.listOfProducts().size();
	}

}
