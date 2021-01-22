package stepDefs;

import static enums.EnumUtils.asEnum;

import desktop.fragments.searchResults.SearchResultsComponent;
import desktop.pages.BasePage;
import enums.PageTitle;
import io.cucumber.java.en.And;
import static org.junit.Assert.*;

import java.util.List;

public class ValidationSteps {

    BasePage basePage = new BasePage();

    @And("^I am redirected to a \"(.+)\"$")
    public void verifyBrowserOnPage(String page) {
        assertTrue(isCurrentPageOpened(asEnum(page, PageTitle.class)));
    }

    private boolean isCurrentPageOpened(PageTitle pageTitle) {
        return basePage.getPageTitle().startsWith(pageTitle.getValue());
    }

}
