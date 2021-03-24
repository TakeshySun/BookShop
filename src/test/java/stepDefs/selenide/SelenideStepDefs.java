package stepDefs.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.impl.Screenshot;
import com.codeborne.selenide.junit.ScreenShooter;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import desktop.selenide.PageWithSelenide;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static driver.SingletonDriver.getDriver;
import static driver.SingletonDriver.quit;

public class SelenideStepDefs {

    @Rule
    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests();

    PageWithSelenide page = new PageWithSelenide();

    @Before
    public void setUp() {
        open("https://www.bookdepository.com/");
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tT %4$s %5$s%6$s%n");
        Configuration.timeout = 3000;
    }


    @Given("open the \"Initial home page\"")
    public void openPage(){

    }

    @And("search for \"Thinking in Java\"")
//@Test
    public void searchFor() {
        page.search();
    }

    @And("Search results contain the following product {string}")
//@Test
    public void searchResultsContainTheFollowingProduct(String text) {
        page.searchResultContains(text);
    }

    @And("I click Add to basket button for product with name {string}")
//@Test
    public void iClickAddToBasketButtonForProductWithName(String bookName) {
        page.clickAddForBook(bookName);
    }

    @And("I select BasketCheckout in basket pop-up")
//@Test
    public void iSelectBasketCheckoutInBasketPopUp() {
        page.clickBuscketChechoutPopup();
    }

    @Then("Basket order summary is following:")
//@Test
    public void basketOrderSummaryIsAsFollowing(DataTable table) {
        List<Map<String, String>> list = table.asMaps(String.class,String.class);
        String deliveryCostValue = list.get(0).get("Delivery cost");
        String totalValue = list.get(0).get("Total");
//
        $("dl.delivery-text dd").should(Condition.visible).shouldHave(Condition.exactText(deliveryCostValue));
        $("dl.total dd").should(Condition.visible).shouldHave(Condition.exactText(totalValue));
    }

}
