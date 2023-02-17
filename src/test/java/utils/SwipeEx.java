package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.time.Duration;

public final class SwipeEx {

    public static void SwipeDown(int startXCoordinate, int endXCoordinate,
                                 int startYCoordinate, int endYCoordinate, AppiumDriver<MobileElement> appiumDriver) {
        // Get Mobile window size
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenWidth = windowSize.getWidth();
        int screenHeight = windowSize.getHeight();

        // Calculate touch point
        int xStartPoint = startXCoordinate * screenWidth / 100;
        int xEndPoint = endXCoordinate * screenWidth / 100;

        int yStartPoint = startYCoordinate * screenHeight / 100;
        int yEndPoint = endYCoordinate * screenHeight / 100;

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
    }

    public static void SwipeUp(int startXCoordinate, int endXCoordinate,
                               int startYCoordinate, int endYCoordinate, AppiumDriver<MobileElement> appiumDriver) {
        // Get Mobile window size
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenWidth = windowSize.getWidth();
        int screenHeight = windowSize.getHeight();

        // Calculate touch points
        int xStartPoint = startXCoordinate * screenWidth / 100;
        int xEndPoint = endXCoordinate * screenWidth / 100;

        int yStartPoint = startYCoordinate * screenHeight / 100;
        int yEndPoint = endYCoordinate * screenHeight / 100;

        // Convert coordinates -> PointOption
        PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

        // Using TouchAction to swipe up
        TouchAction touchAction = new TouchAction(appiumDriver);
        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                .moveTo(endPoint)
                .release()
                .perform();
    }

    public static void SwipeLeft(){

    }

    public static void SwipeRight(){

    }
}
