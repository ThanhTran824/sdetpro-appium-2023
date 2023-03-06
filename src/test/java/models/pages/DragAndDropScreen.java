package models.pages;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.touch.offset.PointOption;
import models.components.DragAndDrop.DragAndDropComponent;
import models.components.global.BottomNavComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.util.HashMap;
import java.util.Map;

public class DragAndDropScreen {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            BottomNavComponent bottomNavComponent = new BottomNavComponent(appiumDriver);
            bottomNavComponent.clickOnDragAndDropIcon();

            Map<String, String> elementsMap = new HashMap<>();
            elementsMap.put("drag-c1", "drop-c1");
            elementsMap.put("drag-c2", "drop-c2");
            elementsMap.put("drag-c3", "drop-c3");
            elementsMap.put("drag-l1", "drop-l1");
            elementsMap.put("drag-l2", "drop-l2");
            elementsMap.put("drag-l3", "drop-l3");
            elementsMap.put("drag-r1", "drop-r1");
            elementsMap.put("drag-r2", "drop-r2");
            elementsMap.put("drag-r3", "drop-r3");

            DragAndDropComponent dragAndDropComponent = new DragAndDropComponent(appiumDriver);
            for (String element : elementsMap.keySet()) {
                dragAndDropComponent.dragAndDropElement(element, elementsMap.get(element));
            }

            Thread.sleep(3000);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        appiumDriver.quit();
    }



}
