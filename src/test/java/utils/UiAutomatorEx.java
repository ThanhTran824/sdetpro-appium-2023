package utils;

public final class UiAutomatorEx {

    public static String textContains(String text){
        return "new UiSelector().textContains(\"" + text + "\")";
    }
}
