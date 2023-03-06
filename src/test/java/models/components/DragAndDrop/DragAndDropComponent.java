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

            int x = startElem.getLocation().x;
            System.out.println(x);
            int y = startElem.getLocation().y;
            System.out.println(y);

            int _x = endElem.getLocation().x;
            System.out.println(_x);
            int _y = endElem.getLocation().y;
            System.out.println(_y);

            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction
                    .press(PointOption.point(startElem.getLocation().x, startElem.getLocation().y))
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                    .moveTo(PointOption.point(endElem.getLocation().x, endElem.getLocation().y))
                    .release()
                    .perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
