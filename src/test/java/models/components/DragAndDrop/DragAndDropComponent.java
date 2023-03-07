package models.components.DragAndDrop;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import java.time.Duration;

public class DragAndDropComponent {

    private final AppiumDriver<MobileElement> appiumDriver;

    public DragAndDropComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }


    public void dragAndDropElement(String dragIDElement, String dropIDElement) {
        // Select item want to be dragged
        try {
            MobileElement startElem = appiumDriver.findElement(MobileBy.AccessibilityId(dragIDElement));
            MobileElement endElem = appiumDriver.findElement(MobileBy.AccessibilityId(dropIDElement));

            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .press(PointOption.point(startElem.getCenter().x, startElem.getCenter().y))
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(endElem.getCenter().x, endElem.getCenter().y))
                    .release()
                    .perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
