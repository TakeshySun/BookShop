package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

import static driver.SingletonDriver.*;

public class InitiateSteps {
    //Before for run in cucumber
//    @Before
//    public void set_up() {
//        createDriver("chrome");
//    }
    //After for run in cucumber
//    @After
//    public void down(){
//        quit();
//    }

    @Given("I am an anonymous customer with clear cookies")
    public void iAmAnAnonymousCustomerWithClearCookies() {
        getDriver();
    }
}
