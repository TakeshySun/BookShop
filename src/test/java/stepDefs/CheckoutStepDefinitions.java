package stepDefs;

import desktop.pages.CheckOutPageImpl;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.assertj.core.api.SoftAssertions;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CheckoutStepDefinitions {
    CheckOutPageImpl checkOutPage = new CheckOutPageImpl();
    SoftAssertions softly = new SoftAssertions();

    @And("I checkout as a new customer with email {string}")
    public void fillInCheckoutNewCustomerEmail(String email) {
        checkOutPage.checkOutPersonalInformationComponent.fillEmailField(email);
    }

    @And("Checkout order summary is as following:")
    public void checkoutOrderSummaryIsAsFollowing(DataTable dataTable) {
        List<Map<String, String>> personalInfoForms = dataTable.asMaps(String.class, String.class);
        String subTotalValue = personalInfoForms.get(0).get("Sub-total");
        String DeliveryValue = personalInfoForms.get(0).get("Delivery");
        String vatValue = personalInfoForms.get(0).get("VAT");
        String totalValue = personalInfoForms.get(0).get("Total");

        softly.assertThat(checkOutPage.checkOutTotalComponent.getSubTotalValue()).as("Sub-total is Wrong").isEqualTo(subTotalValue);
        softly.assertThat(checkOutPage.checkOutTotalComponent.getDeliveryValue()).as("Delivery is Wrong").isEqualTo(DeliveryValue);
        softly.assertThat(checkOutPage.checkOutTotalComponent.getVatValue()).as("VAT is Wrong").isEqualTo(vatValue);
        softly.assertThat(checkOutPage.checkOutTotalComponent.getTotalValue()).as("Total is Wrong").isEqualTo(totalValue);
        softly.assertAll();
    }

    @And("I fill delivery address information manually:")
    public void iFillDeliveryAddressInformationManually(DataTable dataTable) {
        checkOutPage.checkOutPersonalInformationComponent.fillInPersonalData(dataTable);

        List<String> expected = dataTable.row(1);
        softly.assertThat(checkOutPage.checkOutPersonalInformationComponent.getPersonalDetails()).as("Error").isEqualTo(expected);
        softly.assertAll();
    }

    @And("'Payment' section is disabled for editing")
    public void paymentSectionIsDisabledForEditing() {
        softly.assertThat(checkOutPage.checkOutDisablePaymentComponent.isPaymentSectionDisabled())
                .as("Is False")
                .isTrue();
        softly.assertAll();
    }

    @When("I press 'Continue to payment' button on checkout")
    public void iPressContinueToPaymentButtonOnCheckout() {
        checkOutPage.checkOutPersonalInformationComponent.pressContinueToPaymentButton();
    }

    @And("I enter my card details")
    public void iEnterMyCardDetails(DataTable dataTable) {
        checkOutPage.checkOutPaymentComponent.setCardInformation(dataTable);
    }
}
