package tests;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;
import pages.SearchResultPage;

public abstract class BasicTest {

	protected WebDriver driver;
	protected JavascriptExecutor js;
	protected WebDriverWait waiter;
	protected String baseUrl = "http://demo.yo-meals.com/";
	protected String email = "customer@dummyid.com";
	protected String password = "12345678a";
	protected String photo = "C:\\Users\\Dell\\Desktop\\yo-meals_project\\yo-meals_project\\img\\VE9DuR.jpg";
	protected LocationPopupPage LocationPopupElement;
	protected LoginPage LoginElement;
	protected NotificationSistemPage NotificationSistemElement;
	protected ProfilePage ProfileElement;
	protected MealPage MealElement;
	protected CartSummaryPage CartSummaryElement;
	protected SearchResultPage SearchResultElement;
	protected AuthPage AuthElement;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");

		this.driver = new ChromeDriver();
		this.js = (JavascriptExecutor) driver;
		this.waiter = new WebDriverWait(driver, 30);
		this.LocationPopupElement = new LocationPopupPage(driver, js, waiter);
		this.LoginElement = new LoginPage(driver, js, waiter);
		this.NotificationSistemElement = new NotificationSistemPage(driver, js, waiter);
		this.ProfileElement = new ProfilePage(driver, js, waiter);
		this.MealElement = new MealPage(driver, js, waiter);
		this.CartSummaryElement = new CartSummaryPage(driver, js, waiter);
		this.SearchResultElement = new SearchResultPage(driver, js, waiter);
		this.AuthElement = new AuthPage(driver, js, waiter);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void ifTestFail(ITestResult result) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh-mm-ss");
		Date date = new Date();
		if (result.getStatus() == ITestResult.FAILURE) {
			File src = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(
					"screenshots/" + dateFormat.format(date) + ".png"));
		}

		this.driver.manage().deleteAllCookies();
	}

	@AfterClass
	public void quit() {
		this.driver.quit();
	}
}
