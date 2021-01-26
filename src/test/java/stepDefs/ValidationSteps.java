package stepDefs;

import static enums.EnumUtils.asEnum;

import desktop.pages.BasePage;
import enums.PageTitle;
import io.cucumber.java.en.And;
import org.assertj.core.api.SoftAssertions;



public class ValidationSteps {

    BasePage basePage = new BasePage();

    @And("^I am redirected to a \"(.+)\"$")
    public void verifyBrowserOnPage(String page) {
//        assertTrue(isCurrentPageOpened(asEnum(page, PageTitle.class)));
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(isCurrentPageOpened(asEnum(page, PageTitle.class))).as("WrongPage").isTrue();
        softly.assertAll();
    }

    private boolean isCurrentPageOpened(PageTitle pageTitle) {
        return basePage.getPageTitle().startsWith(pageTitle.getValue());
    }

}
