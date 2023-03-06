package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class LoginScreenMod02 {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final static By usernameSel = MobileBy.AccessibilityId("input-email");
    private final static By passwordSel = MobileBy.AccessibilityId("input-password");
    private final static By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginScreenMod02(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void inputUsername(String usernameStr) {
        // usernameStr must be trim before pass to this function
        if (!usernameStr.isEmpty())
            appiumDriver.findElement(usernameSel).sendKeys(usernameStr);
    }

    public void inputPassword(String passwordStr) {
        // passwordStr must be trim before pass to this function
        if (!passwordStr.trim().isEmpty())
            appiumDriver.findElement(passwordSel).sendKeys(passwordStr);
    }

    public void clickOnLoginBtn() {
        appiumDriver.findElement(loginBtnSel).click();
    }
}
