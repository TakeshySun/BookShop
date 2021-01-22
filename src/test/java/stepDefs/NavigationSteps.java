package stepDefs;

import desktop.pages.HomePage;
import io.cucumber.java.en.And;

public class NavigationSteps {

    HomePage homePage = new HomePage();

    @And("I open the \"Initial home page\"")
    public void openHomePage(){
        homePage.openPage();
    }

}
