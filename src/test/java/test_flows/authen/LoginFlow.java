package test_flows.authen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;
import org.apache.commons.validator.routines.EmailValidator;
import test_flows.BaseFlow;

public class LoginFlow extends BaseFlow {

    private String username;
    private String password;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, String username, String password) {
        super(appiumDriver);
        this.username = username;
        this.password = password;
    }

    public void login() {
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComponent = loginScreen.loginFormComponent();

        loginFormComponent.inputEmail(username);
        loginFormComponent.inputPassword(password);

        loginFormComponent.clickOnLoginBtn();
    }

    public void verifyLogin() {
        LoginScreen loginScreen = new LoginScreen(appiumDriver);
        LoginFormComponent loginFormComponent = loginScreen.loginFormComponent();

        boolean isEmailValid = EmailValidator.getInstance().isValid(username);
        boolean isPasswordValid = password.length() >= 8;

        if (isEmailValid && isPasswordValid) {
            verifyCorrectLoginCreds();
        }

        if (!isEmailValid) {
            verifyIncorrectEmail(loginFormComponent);
        }

        if (!isPasswordValid) {
            verifyIncorrectPassword(loginFormComponent);
        }
    }

    private void verifyCorrectLoginCreds() {
        MobileElement successMessage = appiumDriver.findElement(MobileBy.id("android:id/alertTitle"));
        if (successMessage.getText().equalsIgnoreCase("success")) {
            // Close alert
            MobileElement okBtn = appiumDriver.findElement(MobileBy.id("android:id/button1"));
            okBtn.click();
        }
    }

    private void verifyIncorrectEmail(LoginFormComponent loginFormComponent) {
        String actualIncorrectEmailStr = loginFormComponent.getIncorrectEmailString();
        String expectedIncorrectEmailStr = "Please enter a valid email address";

        System.out.println("actualIncorrectEmailStr: " + actualIncorrectEmailStr);
        System.out.println("expectedIncorrectEmailStr: " + expectedIncorrectEmailStr);
    }

    private void verifyIncorrectPassword(LoginFormComponent loginFormComponent) {
        String actualIncorrectPasswordStr = loginFormComponent.getIncorrectPasswordString();
        String expectedIncorrectPasswordStr = "Please enter at least 8 characters";

        System.out.println("actualIncorrectPasswordStr: " + actualIncorrectPasswordStr);
        System.out.println("expectedIncorrectPasswordStr: " + expectedIncorrectPasswordStr);
    }
}
