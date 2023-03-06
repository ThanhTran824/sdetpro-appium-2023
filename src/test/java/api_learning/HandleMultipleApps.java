package api_learning;

import driver.AppPackages;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.internal.CapabilityHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;

import static driver.MobileCapabilityTypeEx.APP_PACKAGE;
import static driver.MobileCapabilityTypeEx.PLATFORM_NAME;

public class HandleMultipleApps {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Login screen
            MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtnElem.click();

            // Find Login form elements
            MobileElement emailInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement passwordInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            // Fill the form and login
            emailInputElem.sendKeys("teo@sth.com");
            passwordInputElem.sendKeys("12345678");
            loginBtnElem.click();

            // Put the app under test to background | simulate pressing home button > relaunch
            //appiumDriver.runAppInBackground(Duration.ofSeconds(3));

            // Put the app under test to background till we call it back
            appiumDriver.runAppInBackground(Duration.ofSeconds(-1));

            // Switch into another app | Go to Settings toggle WiFi
            // Run command adb shell "dumpsys activity activities | grep -E mResumedActivity" to get app package parameter
            appiumDriver.activateApp(AppPackages.SETTINGS);

            // Navigate to network list
            By wifiLabelSel = MobileBy.xpath("//*[@text='Network & internet']");
            appiumDriver.findElement(wifiLabelSel).click();

            By internetLabelSel = MobileBy.xpath("//*[@text='Internet']");
            appiumDriver.findElement(internetLabelSel).click();

            // Toggle ON/OFF
            By wifiStatusSel = MobileBy.xpath("//*[@text='Wi-Fi']");
            MobileElement wifiStatusElem = appiumDriver.findElement(wifiStatusSel);
            String isChecked = wifiStatusElem.getAttribute("checked").trim();
            if(isChecked.compareTo("false") == 0)
                wifiStatusElem.click();

            WebDriverWait wait = new WebDriverWait(appiumDriver, 2);
            wait.until(ExpectedConditions.elementToBeClickable(wifiStatusElem));

            // Back to the app > interact with other elements
            appiumDriver.activateApp(AppPackages.WEBDRIVER_IO);
            appiumDriver.findElement(MobileBy.xpath("//*[@text='OK']")).click();

            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
