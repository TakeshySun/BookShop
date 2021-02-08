package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

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
    public void down(){
        quit();
    }

    @Given("I am an anonymous customer with clear cookies")
    public void iAmAnAnonymousCustomerWithClearCookies() {
//        getDriver();
    }
}
