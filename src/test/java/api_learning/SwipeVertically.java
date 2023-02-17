package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;
import utils.SwipeEx;


import java.time.Duration;

public class SwipeVertically {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Forms screen
            MobileElement navFormsScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormsScreenBtnElem.click();

            // Wait until Form screen is displayed
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Form components\")")));

            //SwipeEx.SwipeDown(50, 50, 50, 10, appiumDriver);
            // Get Mobile window size
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenWidth = windowSize.getWidth();
            int screenHeight = windowSize.getHeight();

            // Calculate touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;

            int yStartPoint = 50 * screenHeight / 100;
            int yEndPoint = 10 * screenHeight / 100;

            // Convert coordinates -> PointOption
            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

            // Using TouchAction to swipe
            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            // Swipe up
            touchAction
                    .longPress(endPoint)
                    .moveTo(startPoint)
                    .release()
                    .perform();

            // Click on Btn-active
            MobileElement activeElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
            activeElem.click();

            Thread.sleep(3000);
        } catch (Exception e) {
        }
    }
}
