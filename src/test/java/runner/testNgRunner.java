// For use it, please comment in POM other non-testNg dependencies and uncomment testNG.

//package runner;
//
//import org.testng.annotations.*;
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//
//
//import static driver.SingletonDriver.*;
//
//@CucumberOptions
//        (
//                features = "src/test/resources/features/DesktopCheckoutForGuestUser.feature", //the path of the feature files
//                glue="stepDefs", //the path of the step definition files
//                plugin= {"pretty:target/cucumber-pretty.txt",
//                        "html:target/cucumber-html-report",
//                        "json:target/cucumber.json",
//                        "rerun:target/rerun.txt"
//                },
//                tags = "@Runme"
//
//        )
//
//public class testNgRunner extends AbstractTestNGCucumberTests {
//
//    @Parameters("browser")
//    @BeforeClass
//    public static void setUpScenario(String browser) {
//            createDriver(browser);
//    }
//
//    @AfterClass
//    public void down(){
//        quit();
//    }
//}
