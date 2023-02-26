package api_learning;

import context.Contexts;
import context.WaitMoreThanOneContext;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HybridContext {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            By webviewNavBtnSel = MobileBy.AccessibilityId("Webview");
            MobileElement webviewNavBtnElem = appiumDriver.findElement(webviewNavBtnSel);
            webviewNavBtnElem.click();

            // Explicit wait: wait until we have once more than context
            //WebDriverWait wait = new WebDriverWait(appiumDriver, 15L);
            //wait.until(new WaitMoreThanOneContext(appiumDriver));

            for (String contextHandle : appiumDriver.getContextHandles()) {
                System.out.println(contextHandle);
            }

            // Switch to Webview context
            appiumDriver.context(Contexts.WEBVIEW);

            WebElement navToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtnElem.click();

            List<MobileElement> menuItemsElem = appiumDriver.findElementsByCssSelector(".menu__list li a");
            // Using Map
            /*Map<String, String> menuItemDataMap = new HashMap<>();
            // Check false negative case
            if (menuItemsElem.isEmpty())
                throw new RuntimeException("[ERR] There is no list items!");

            for (MobileElement menuItemElem : menuItemsElem) {
                String itemText = menuItemElem.getText();
                String itemHref = menuItemElem.getAttribute("href");


                if (itemHref.contains("https://github")) {
                    menuItemDataMap.put("Github", itemHref);
                }
                else if (itemHref.contains("https://twitter")) {
                    menuItemDataMap.put("Twitter", itemHref);

                }
                else {
                    menuItemDataMap.put(itemText, itemHref);
                }
            }
            // Verification
            for (String itemText : menuItemDataMap.keySet()) {
                System.out.println("itemText: " + itemText);
                System.out.println(menuItemDataMap.get(itemText));
            }*/
            // Using Builder Design Pattern
            List<MenuItemData> menuItemDataList = new ArrayList<>();

            // Check false negative case
            if (menuItemsElem.isEmpty())
                throw new RuntimeException("[ERR] There is no list items!");

            for (MobileElement menuItemElem : menuItemsElem) {
                String itemText = menuItemElem.getText();
                String itemHref = menuItemElem.getAttribute("href");

                if (itemHref.contains("https://github")) {
                    menuItemDataList.add(new MenuItemData("Github", itemHref));
                }
                else if (itemHref.contains("https://twitter")) {
                    menuItemDataList.add(new MenuItemData("Twitter", itemHref));
                }
                else {
                    menuItemDataList.add(new MenuItemData(itemText, itemHref));
                }
            }

            // Verification
            for (MenuItemData menuItemData : menuItemDataList) {
                System.out.println(menuItemData);
            }

            // Switch to Native context
            appiumDriver.context(Contexts.NATIVE);

            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

    public static class MenuItemData {
        private String name;
        private String href;

        public MenuItemData(String name, String href) {
            this.name = name;
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public String getHref() {
            return href;
        }

        @Override
        public String toString() {
            return "MenuItemData{" +
                    "name='" + name + '\'' +
                    ", href='" + href + '\'' +
                    '}';
        }
    }
}
