package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest {

	SoftAssert sa = new SoftAssert();

	@Test
	public void searchResultsTest() throws IOException, InterruptedException {

		this.driver.navigate().to(baseUrl + "meals");
		this.LocationPopupElement.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");

		for (int i = 1; i < 7; i++) {

			XSSFRow row = sheet.getRow(i);
			String location = row.getCell(0).getStringCellValue();
			String url = row.getCell(1).getStringCellValue();
			double numberOfProducts = row.getCell(2).getNumericCellValue();
			Thread.sleep(2000);

			this.driver.navigate().to(url);
			this.LocationPopupElement.clickLocation();
			this.LocationPopupElement.setLocation(location);
			Thread.sleep(2000);
			sa.assertEquals(this.SearchResultElement.numbreOfProducts(), numberOfProducts,
					"[ERROR] Numbre of products not the same");

			for (int j = 3; j < numberOfProducts + 3; j++) {

				String productsName = row.getCell(j).getStringCellValue();
				sa.assertTrue(this.SearchResultElement.nameOfProducts().get(j - 3).contains(productsName),
						"[ERROR] Unexpected name of product!");
			}
		}

		sa.assertAll();
		fis.close();
		wb.close();

	}
}
