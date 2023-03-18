package models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class LoginFormComponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final static By emailSel = MobileBy.AccessibilityId("input-email");
    private final static By incorrectEmailTxtSel = MobileBy.xpath("//*[contains(@text, 'a valid email address')]");
    private final static By passwordSel = MobileBy.AccessibilityId("input-password");
    private final static By incorrectPasswordTxtSel = MobileBy.xpath("//*[contains(@text, 'at least 8 characters')]");
    private final static By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void inputEmail(String usernameStr) {
        MobileElement usernameElem = appiumDriver.findElement(emailSel);
        usernameElem.clear();
        usernameElem.sendKeys(usernameStr);
    }

    public String getIncorrectEmailString() {
        return appiumDriver.findElement(incorrectEmailTxtSel).getText();
    }

    public void inputPassword(String passwordStr) {
        MobileElement passwordElem = appiumDriver.findElement(passwordSel);
        passwordElem.clear();
        passwordElem.sendKeys(passwordStr);
    }

    public String getIncorrectPasswordString() {
        return appiumDriver.findElement(incorrectPasswordTxtSel).getText();
    }

    public void clickOnLoginBtn() {
        appiumDriver.findElement(loginBtnSel).click();
    }
}
