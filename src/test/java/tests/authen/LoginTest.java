package tests.authen;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import platform.Platform;
import test_flows.authen.LoginFlow;

import java.util.*;

public class LoginTest {

    //public static void main(String[] args) {
    @Test
    public void TestLogin(){
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        Map<String, String> credDataMap = new HashMap<>();
        credDataMap.put("", "12345678");
        credDataMap.put("teo@", "");
        credDataMap.put("teo@sth.com", "123456");
        credDataMap.put("teo@sth.xyz", "12345678");

        try {
            for (String email : credDataMap.keySet()) {
                LoginFlow loginFlow = new LoginFlow(appiumDriver, email, credDataMap.get(email));
                loginFlow.goToLoginScreen();
                System.out.println(email + ": " + credDataMap.get(email));
                loginFlow.login();
                loginFlow.verifyLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
