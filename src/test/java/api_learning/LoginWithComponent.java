package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreen;
import platform.Platform;

public class LoginWithComponent {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try{
            LoginScreen loginScreen = new LoginScreen(appiumDriver);
            loginScreen.bottomNavComponent().clickOnLoginIcon();
            loginScreen.loginFormComponent().inputEmail("teo@sth.com");
            loginScreen.loginFormComponent().inputPassword("12345678");
            loginScreen.loginFormComponent().clickOnLoginBtn();
        }catch (Exception e){
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
