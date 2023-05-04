package tests.authen;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import platform.Platform;
import test_flows.authen.LoginFlow;

import java.util.HashMap;
import java.util.Map;

public class LoginTestWithDataProvider {

    @Test(dataProvider = "loginCredData")
    //public void TestLogin() {
    public void TestLogin(LoginCred loginCred) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        /*Map<String, String> credDataMap = new HashMap<>();
        credDataMap.put("", "12345678");
        credDataMap.put("teo@", "");
        credDataMap.put("teo@sth.com", "123456");
        credDataMap.put("teo@sth.xyz", "12345678");*/

        /*try {
            for (String email : credDataMap.keySet()) {
                LoginFlow loginFlow = new LoginFlow(appiumDriver, email, credDataMap.get(email));
                loginFlow.goToLoginScreen();
                System.out.println(email + ": " + credDataMap.get(email));
                loginFlow.login();
                loginFlow.verifyLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try{
            LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCred.getEmail(), loginCred.getPassword());
            loginFlow.goToLoginScreen();
            loginFlow.login();
            loginFlow.verifyLogin();
        }catch (Exception e){
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

    public static class LoginCred {
        private String email;
        private String password;

        public LoginCred(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return this.email;
        }

        public String getPassword() {
            return this.password;
        }

        @Override
        public String toString() {
            return "LoginCred{" +
                    "email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    @DataProvider
    public LoginCred[] loginCredData() {
        LoginCred loginCred1 = new LoginCred("", "12345678");
        LoginCred loginCred2 = new LoginCred("teo@", "");
        LoginCred loginCred3 = new LoginCred("teo@sth.com", "123456");
        LoginCred loginCred4 = new LoginCred("teo@sth.xyz", "12345678");
        return new LoginCred[]{loginCred1, loginCred2, loginCred3, loginCred4};

    }
}
