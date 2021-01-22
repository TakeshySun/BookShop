package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;

import static driver.SingletonDriver.*;

public class InitiateSteps {

    @After
    public void down(){
        quit();
    }

    @Given("I am an anonymous customer with clear cookies")
    public void iAmAnAnonymousCustomerWithClearCookies() {
        getDriver();
    }
}
