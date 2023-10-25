package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import platform.Platform;
import io.appium.java_client.MobileBy;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SwipeHorizontalTest {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        MobileElement navSwipeScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
        navSwipeScreenBtnElem.click();

        // Wait until user is on Swipe screen
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
        wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Swipe horizontal\")")));

            swipeRightToLeft(appiumDriver);
    }

    public static void swipeRightToLeft(AppiumDriver<MobileElement> appiumDriver) {
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        // Calculate touch points
        int xStartPoint = 50 * screenWidth / 100;
        int xEndPoint = 10 * screenWidth / 100;

        int yStartPoint = 70 * screenHeight / 100;
        int yEndPoint = 70 * screenHeight / 100;

        // Convert coordinates -> PointOption
        PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

        // Using TouchAction to swipe from Right to Left
        TouchAction touchAction = new TouchAction(appiumDriver);

        String tileElem = "";
        do {
            touchAction.press(startPoint).waitAction(new WaitOptions().withDuration(Duration.ofMillis(500))).moveTo(endPoint).release().perform();
            tileElem = appiumDriver.findElement(MobileBy.xpath("(//android.view.ViewGroup[@content-desc=\"slideTextContainer\"])[2]/android.widget.TextView[1]")).getText();
            System.out.println(tileElem);

        } while (!tileElem.contains("EXTENDABLE"));

    }
}
