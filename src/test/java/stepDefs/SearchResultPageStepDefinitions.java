package stepDefs;

import desktop.pages.SearchPageImp;
import io.cucumber.java.en.And;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SearchResultPageStepDefinitions {

    SearchPageImp searchPage = new SearchPageImp();

    @And("^Search results contain the following products$")
    public void searchResultsContainTheFollowingProducts(List<String> texts) {
        texts.forEach(text -> assertTrue(searchPage.searchResultsComponent.getAllBookNames()
                .stream().anyMatch(bookName -> bookName.contains(text))));
    }

    @And("I apply the following search filters")
    public void iApplyTheFollowingSearchFilters(Map<String, String> entry) {
        entry.forEach((k,v) -> searchPage.filterResultsComponent.selectFilterValue(k, v));
        searchPage.filterResultsComponent.clickRefineResultsBtn();
    }

    @And("Search results contain only the following products")
    public void searchResultsContainOnlyTheFollowingProducts(List<String> texts) {
        texts.forEach(text -> assertTrue(searchPage.searchResultsComponent.getAllBookNames()
                .stream().anyMatch(bookName -> bookName.contains(text))));
    }

    @And("I click 'Add to basket' button for product with name {string}")
    public void iClickAddToBasketButtonForProductWithName(String bookName) {
        searchPage.searchResultsComponent.clickAddToBasketForSelectedBook(bookName);
    }

    @And("I select BasketCheckout in basket pop-up")
    public void iSelectBasketCheckoutInBasketPopUp() {
        searchPage.resultsPopUpComponent.clickBasketCheckoutBtn();
    }

}
