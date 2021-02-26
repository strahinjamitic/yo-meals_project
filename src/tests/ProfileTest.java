package tests;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProfileTest extends BasicTest {

	SoftAssert sa = new SoftAssert();

	@Test(priority = 1)
	public void editProfileTest() throws InterruptedException {

		this.driver.navigate().to(baseUrl + "/guest-user/login-form");
		this.LocationPopupElement.close();
		this.LoginElement.login(email, password);
		sa.assertTrue(this.NotificationSistemElement.messageText().contains("Login Successfull"),
				"[ERROR] Unexpected login message!");
		this.driver.navigate().to(baseUrl + "/member/profile");
		this.ProfileElement.change();
		sa.assertTrue(this.NotificationSistemElement.messageText().contains("Setup Successful"),
				"[ERROR] Unexpected setup message!");
		this.AuthElement.logoutAccount();
		sa.assertTrue(this.NotificationSistemElement.messageText().contains("Logout Successfull!"),
				"[ERROR] Unexpected logout message!");
	}

	@Test(priority = 2)
	public void changeProfileImageTest() throws IOException {
		this.driver.navigate().to(baseUrl + "/guest-user/login-form");
		this.LocationPopupElement.close();
		this.LoginElement.login(email, password);
		sa.assertTrue(this.NotificationSistemElement.messageText().contains("Login Successfull"),
				"[ERROR] Unexpected login message!");
		this.driver.navigate().to(baseUrl + "/member/profile");
		String imgPath = new File("img/VE9DuR.jpg").getCanonicalPath();
		this.ProfileElement.updatePhoto(imgPath);
		sa.assertTrue(this.NotificationSistemElement.messageText().contains("Profile Image Uploaded Successfully"),
				"[ERROR] Unexpected image upload message!");
		this.NotificationSistemElement.waiter();
		this.ProfileElement.deletePhoto();
		sa.assertTrue(this.NotificationSistemElement.messageText().contains("Profile Image Deleted Successfully"),
				"[ERROR] Unexpected image delete message!");
		this.NotificationSistemElement.waiter();
		this.AuthElement.logoutAccount();
		sa.assertTrue(this.NotificationSistemElement.messageText().contains("Logout Successfull!"),
				"[ERROR] Unexpected logout message!");
	}
}
