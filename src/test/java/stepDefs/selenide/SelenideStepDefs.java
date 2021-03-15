package stepDefs.selenide;

import desktop.selenide.PageWithSelenide;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.assertj.core.api.SoftAssertions;

import java.util.List;
import java.util.Map;

public class SelenideStepDefs {

    PageWithSelenide page = new PageWithSelenide();

    @Given("open the \"Initial home page\"")
    public void openPage(){
        page.openPage();
    }

    @And("search for \"Thinking in Java\"")
    public void searchFor() {
        page.search();
    }

    @And("Search results contain the following product {string}")
    public void searchResultsContainTheFollowingProduct(String text) {
        page.searchResultContains(text);
    }

    @And("I click Add to basket button for product with name {string}")
    public void iClickAddToBasketButtonForProductWithName(String bookName) {
        page.clickAddForBook(bookName);
    }

    @And("I select BasketCheckout in basket pop-up")
    public void iSelectBasketCheckoutInBasketPopUp() {
        page.clickBuscketChechoutPopup();
    }

    @And("Basket order summary is following:")
    public void basketOrderSummaryIsAsFollowing(DataTable table) {
        List<Map<String, String>> list = table.asMaps(String.class,String.class);
        String deliveryCostValue = list.get(0).get("Delivery cost");
        String totalValue = list.get(0).get("Total");
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(page.getDeliveryCostValueText()).as("Delivery Cost is Wrong").isEqualTo(deliveryCostValue);
        softly.assertThat(page.getTotalValueText()).as("Total Cost is Wrong").isEqualTo(totalValue);
        softly.assertAll();
    }

}
