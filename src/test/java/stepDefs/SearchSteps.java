package stepDefs;

import desktop.fragments.HeaderComponent;
import desktop.pages.BasePage;
import io.cucumber.java.en.And;

public class SearchSteps {
    BasePage basePage = new BasePage();

    @And("^(?:I|I am|Guest|) search for (.+)$")
    public void searchFor(String book){
        basePage.headerComponent.fillInSearchInput(book);
        basePage.headerComponent.clickSearchBtn();
    }
}
