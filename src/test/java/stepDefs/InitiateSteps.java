package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static driver.SingletonDriver.*;

public class InitiateSteps {
    //Comment @Before before TestNg run xml
    @Before
    public void set_up() {
        String browserName = System.getProperty("browserName");
        createDriver(browserName);
    }

    //Comment @After before TestNg run xml
    @After
    public void down(Scenario scenario){
        // Add screenShot to fail test.
        if(scenario.isFailed()){
            byte[] screen = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screen, "image/png", scenario.getName());
        }
        quit();
    }

    @Given("I am an anonymous customer with clear cookies")
    public void iAmAnAnonymousCustomerWithClearCookies() {

    }
}
