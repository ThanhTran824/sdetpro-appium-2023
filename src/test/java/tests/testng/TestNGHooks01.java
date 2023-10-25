package tests.testng;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestNGHooks01 extends BaseTestNG {

    @BeforeClass
    public void beforeClass() {
        System.out.println("\t\t\t----->" + this.getClass().getSimpleName() + " |Before Class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("\t\t\t\t----->" + this.getClass().getSimpleName() + " |Before Method");
    }

    @Test
    public void testSth01() {
        System.out.println("\t\t\t\t\t----->" + this.getClass().getSimpleName() + " Test Method 01");
    }

    @Test
    public void testSth02() {
        System.out.println("\t\t\t\t\t----->" + this.getClass().getSimpleName() + " Test Method 02");
    }

    @Test
    public void testSth03() {
        String actualResult = "a";
        String expectedResult = "b";

        //Verifier.assertEquals(actualResult, expectedResult);
        // Hard Assertion
        //Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResult, expectedResult, "[ERR] loi roi");
    }

    @Test
    public void testSth04() {
        String actualResult = "a";
        String expectedResult = "b";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualResult, expectedResult, "[ERR] error first");
        softAssert.assertEquals("actualResult", "expectedResult", "[ERR] error second");
        softAssert.assertTrue(false);
        softAssert.assertFalse(true);
        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("\t\t\t\t----->" + this.getClass().getSimpleName() + " |After Method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("\t\t\t----->" + this.getClass().getSimpleName() + " |After Class");
    }
}
