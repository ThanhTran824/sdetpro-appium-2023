package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenByMethodChaining;
import platform.Platform;

public class LoginWithPOM {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Login screen
            MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtnElem.click();

            /*LoginScreenMod01 loginScreen = new LoginScreenMod01(appiumDriver);
            loginScreen.usernameElem().sendKeys("teo@sth.com");
            loginScreen.passwordElem().sendKeys("12345678");
            loginScreen.loginBtnElem().click();*/

            /*LoginScreenMod02 loginScreen = new LoginScreenMod02(appiumDriver);
            loginScreen.inputUsername("teo@sth.com");
            loginScreen.inputPassword("123456789");
            loginScreen.clickOnLoginBtn();*/

            LoginScreenByMethodChaining loginScreen = new LoginScreenByMethodChaining(appiumDriver);
            loginScreen
                    .inputUsername("teo@sth.com")
                    .inputPassword("12345678")
                    .clickOnLoginBtn();


        } catch (Exception e) {
            e.printStackTrace();
        }


        appiumDriver.quit();
    }
}
