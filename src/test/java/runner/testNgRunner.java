package runner;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Parameters;


import static driver.SingletonDriver.createDriver;
import static driver.SingletonDriver.quit;

@CucumberOptions
        (
                features = "src/test/resources/features", //the path of the feature files
                glue="stepDefs", //the path of the step definition files
                plugin= {"pretty:target/cucumber-pretty.txt",
                        "html:target/cucumber-html-report",
                        "json:target/cucumber.json",
                        "rerun:target/rerun.txt"
                },
                tags = "@Runme"

        )

public class testNgRunner extends AbstractTestNGCucumberTests {

    @Parameters("browser")
    @BeforeTest
    public static void setUpScenario(String browser) {
        createDriver(browser);
    }

//    @AfterTest
//    public void down(){
//        quit();
//    }
}
