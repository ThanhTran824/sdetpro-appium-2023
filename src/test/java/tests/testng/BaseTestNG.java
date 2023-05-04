package tests.testng;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTestNG {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("\t----->" + this.getClass().getSimpleName() + " |Before Suite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("\t\t----->" + this.getClass().getSimpleName() + " |Before Test");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("\t\t----->" + this.getClass().getSimpleName() + " |After Test");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("\t----->" + this.getClass().getSimpleName() + " |After Suite");
    }
}
