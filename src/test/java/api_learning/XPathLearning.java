package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import platform.Platform;

import java.time.Duration;
import java.util.List;

public class XPathLearning {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Login screen
            MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtnElem.click();

            // Find all matches element
            List<MobileElement> credentialFieldsElem = appiumDriver.findElements(MobileBy.xpath("//android.widget.EditText"));
            final int USERNAME_INDEX = 0;
            final int PASSWORD_INDEX = 1;
            credentialFieldsElem.get(USERNAME_INDEX).sendKeys("teo@sth.com");
            credentialFieldsElem.get(PASSWORD_INDEX).sendKeys("12345678");

            // Find element by UiSelector
            MobileElement loginInstructionText =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"When the device\")"));
            System.out.println(loginInstructionText.getText());

            // checked null in DriverFactor class
            appiumDriver.quit();
        } catch (Exception ignored) {
        }
    }
}
