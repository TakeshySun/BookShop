package stepDefs;

import desktop.pages.BasePage;
import desktop.pages.BasketPageImp;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class BasketChechoutStepDefenitions {
    BasketPageImp basketPage = new BasketPageImp();

    @And("Basket order summary is as following:")
    public void basketOrderSummaryIsAsFollowing(DataTable table) {
        List<Map<String, String>> list = table.asMaps(String.class,String.class);
        String deliveryCostValue = list.get(0).get("Delivery cost");
        String totalValue = list.get(0).get("Total");
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(basketPage.basketTotalComponent.getDeliveryCostValueText()).as("Delivery Cost is Wrong").isEqualTo(deliveryCostValue);
        softly.assertThat(basketPage.basketTotalComponent.getTotalValueText()).as("Total Cost is Wrong").isEqualTo(totalValue);
        softly.assertAll();
      }

    @And("I click Checkout button on Basket page")
    public void iClickCheckoutButtonOnBasketPage() {
        basketPage.basketTotalComponent.clickCheckoutBtn();
    }
}
