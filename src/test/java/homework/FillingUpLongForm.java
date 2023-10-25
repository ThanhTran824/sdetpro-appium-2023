package homework;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;
import utils.UiAutomatorEx;

import java.util.List;

public class FillingUpLongForm {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Forms screen
            MobileElement navSwipeScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navSwipeScreenBtnElem.click();

            // Wait until Form screen is displayed
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Form components\")")));

            MobileElement inputElem = appiumDriver.findElement(MobileBy.AccessibilityId("text-input"));
            MobileElement switchElem = appiumDriver.findElement(MobileBy.AccessibilityId("switch"));
            MobileElement dropdownElem = appiumDriver.findElement(MobileBy.AccessibilityId("Dropdown"));

            /* another way to interact with dropdown
             *driver.findElementById("android:id/text1").click();//click on dropdown
             *List options=driver.findElementsById("android:id/text1");
             */

            inputElem.sendKeys("teo tao lao");
            System.out.println(switchElem.getText());
            if (switchElem.getText().contains("OFF"))
                switchElem.click();
            dropdownElem.click();
            List<MobileElement> items = appiumDriver.findElementsById("android:id/text1");
            for (MobileElement item : items) {
                String val = item.getText();

                if (val.contains("Appium is awesome")) {
                    item.click();
                    break;
                }
            }

            MobileElement activeButton = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
            activeButton.click();

            // Switch to frame

            wait = new WebDriverWait(appiumDriver, 10L);
            //wait.until(ExpectedConditions
            //        .visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"This button is\")")));

            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(MobileBy.AndroidUIAutomator(UiAutomatorEx.textContains("This button is"))));

            MobileElement askMeLaterButton = appiumDriver.findElement(MobileBy.id("android:id/button3"));
            MobileElement cancelButton = appiumDriver.findElement(MobileBy.id("android:id/button2"));
            MobileElement okButton = appiumDriver.findElement(MobileBy.id("android:id/button3"));

            //cancelButton.click();


            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
