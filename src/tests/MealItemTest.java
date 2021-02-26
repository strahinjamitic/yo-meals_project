package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {

	SoftAssert sa = new SoftAssert();

	@Test(priority = 1)
	public void addMealToCartTest() throws InterruptedException {

		this.driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		this.LocationPopupElement.close();
		this.MealElement.addToCart("2");
		sa.assertTrue(
				this.NotificationSistemElement.messageText()
						.contains("\"The Following Errors Occurred:\r\n" + "Please Select Location\"\r\n" + ""),
				"[ERROR] Unexpected message!");
		this.NotificationSistemElement.waiter();
		this.LocationPopupElement.clickLocation();
		this.LocationPopupElement.setLocation("City Center - Albany");
		Thread.sleep(3000);
		this.MealElement.addToCart("2");
		sa.assertTrue(this.NotificationSistemElement.messageText().contains("Meal Added To Cart"),
				"[ERROR] Unexpected meal add to cart message!");
	}

	@Test(priority = 2)
	public void addMealToFavoriteTest() throws InterruptedException {

		this.driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		this.LocationPopupElement.close();
		this.MealElement.favorite();
		sa.assertTrue(this.NotificationSistemElement.messageText().contains("Please login first!"),
				"[ERROR] Unexpected message!");
		this.driver.navigate().to(baseUrl + "/guest-user/login-form");
		this.LoginElement.login(email, password);
		this.driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		this.MealElement.favorite();
		sa.assertTrue(
				this.NotificationSistemElement.messageText().contains("Product has been added to your favorites."),
				"[ERROR] Unexpected favorite product message!");
	}

	@Test(priority = 3)
	public void clearCartTest() throws InterruptedException, IOException {

		this.driver.navigate().to(baseUrl + "meals");
		this.LocationPopupElement.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		for (int i = 1; i < sheet.getLastRowNum(); i++) {

			String mealUrl = sheet.getRow(i).getCell(0).getStringCellValue();
			Double quantityNumber = sheet.getRow(i).getCell(1).getNumericCellValue();
			String quantity = String.valueOf(quantityNumber);

			this.driver.navigate().to(mealUrl);
			this.MealElement.addToCart(quantity);

			sa.assertTrue(this.NotificationSistemElement.messageText().contains("Meal Added To Cart"),
					"[ERROR] Unexpected meal add to cart message!");
		}

		fis.close();
		wb.close();

	}
}
