package tests;

import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test
	public void test() throws InterruptedException {
		
		this.driver.navigate().to(baseUrl + "/guest-user/login-form");
		this.LoginElement.login(email, password);
		Thread.sleep(3000);
		this.driver.navigate().to(baseUrl + "/member/profile");
		Thread.sleep(3000);
		this.ProfileElement.change();
		Thread.sleep(3000);
		this.ProfileElement.updatePhoto(photo);
		Thread.sleep(9000);
		this.ProfileElement.deletePhoto();
		Thread.sleep(9000);
		this.ProfileElement.savebtn().click();
	}
}
