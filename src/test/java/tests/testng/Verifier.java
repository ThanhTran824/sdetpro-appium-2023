package tests.testng;

public class Verifier {

    public static void assertEquals(String actualResult, String expectedResult){
        if(!actualResult.equals(expectedResult))
            throw new RuntimeException("Expected value and actual value are diff!");
    }
}
