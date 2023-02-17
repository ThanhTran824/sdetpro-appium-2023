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

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NarrowDownSearching {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Forms screen
            MobileElement navSwipeScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
            navSwipeScreenBtnElem.click();

            // Wait until Form screen is displayed
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Swipe horizontal\")")));

            //SwipeEx.SwipeDown(50, 10, 70, 70, appiumDriver);
            // Get Mobile window size
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenWidth = windowSize.getWidth();
            int screenHeight = windowSize.getHeight();

            // Calculate touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;

            int yStartPoint = 0;
            int yEndPoint = 50 * screenHeight / 100;

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


            List<MobileElement> notificationElems =
                    appiumDriver.findElements(MobileBy.id("android:id/notification_headerless_view_column"));

            Map<String, String> notificationContents = new HashMap<>();
            for (MobileElement notificationElem : notificationElems) {
                // Get title
                MobileElement notification_top_line = notificationElem.findElement(MobileBy.id("android:id/notification_top_line"));
                MobileElement titleElem = notification_top_line.findElement(MobileBy.id("android:id/title"));
                // Get text
                MobileElement notification_main_column = notificationElem.findElement(MobileBy.id("android:id/notification_main_column"));
                MobileElement contentElem = notification_main_column.findElement(MobileBy.id("android:id/text"));
                notificationContents.put(titleElem.getText().trim(), contentElem.getText().trim());
                //System.out.println(titleElem.getText());
                //System.out.println(contentElem.getText());
            }

            // Verification

            if(notificationContents.keySet().isEmpty())
                throw new RuntimeException("There is no notification");

            for(String title: notificationContents.keySet()){
                System.out.println(("Title: " + title));
                System.out.println(("Content:" + notificationContents.get(title)));
            }


            Thread.sleep(3000);
        } catch (Exception e) {
        }
    }
}
