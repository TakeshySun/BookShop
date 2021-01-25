package desktop.fragments.checkOutComponents;

import abstractClasses.fragment.AbstractFragment;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CheckOutPersonalInformationComponent extends AbstractFragment {
    By emailInput = By.cssSelector("input[name=emailAddress]");
    By deliveryFullNameInput = By.cssSelector("input[name=delivery-fullName]");
    By deliveryCountryDropDown = By.cssSelector("select[name=deliveryCountry]");
    By deliveryAddressInput = By.cssSelector("input[name=delivery-addressSearch]");
    By deliverySuggestion = By.cssSelector("div.result-wrap > div > span:nth-child(1)");
    By deliveryAddressOneInput = By.cssSelector("input[name=delivery-addressLine1]");
    By deliveryAddressTwoInput = By.cssSelector("input[name=delivery-addressLine2]");
    By deliveryCityInput = By.cssSelector("input[name=delivery-city]");
    By deliveryCountryInput = By.cssSelector("input[name=delivery-county]");
    By deliveryPostCodeInput = By.cssSelector("input[name=delivery-postCode]");
    By continueToPaymentBtn = By.cssSelector("button#hasSubmittedSameAddresses");


    @FindBy(css = ".main-content > div:nth-child(4)")
    WebElement rootElement;

    public CheckOutPersonalInformationComponent() {
        setRootElement(rootElement);
    }

    public void fillEmailField(String email) {
        setValueToInput(emailInput, email);
    }

    public void fillFullNameField(String name) {
        setValueToInput(deliveryFullNameInput, name);
    }

    public void selectDeliveryCountry(String country) {
        selectItemInDropDown(rootElement.findElement(deliveryCountryDropDown), country);
    }

    public void findDeliveryAddress(String address) {
        setValueToInput(deliveryAddressInput, address);
        chooseDeliveryAddress();
    }

    public void fillDeliveryAddressOneField(String name) {
        setValueToInput(deliveryAddressOneInput, name);
    }

    public void fillDeliveryAddressTwoField(String name) {
        setValueToInput(deliveryAddressTwoInput, name);
    }

    public void fillDeliveryCityField(String name) {
        setValueToInput(deliveryCityInput, name);
    }

    public void fillDeliveryCountryField(String name) {
        setValueToInput(deliveryCountryInput, name);
    }

    public void fillPostCodeField(String name) {
        setValueToInput(deliveryPostCodeInput, name);
    }

    public void fillInPersonalData(DataTable dataTable) {
        List<Map<String, String>> personalInfoForms = dataTable.asMaps(String.class, String.class);
        fillFullNameField(personalInfoForms.get(0).get("Full name"));
        selectDeliveryCountry(personalInfoForms.get(0).get("Delivery country"));
        findDeliveryAddress("Kyiv");
        fillDeliveryAddressOneField(personalInfoForms.get(0).get("Address line 1"));
        fillDeliveryAddressTwoField(personalInfoForms.get(0).get("Address line 2"));
        fillDeliveryCityField(personalInfoForms.get(0).get("Town/City"));
        fillDeliveryCountryField(personalInfoForms.get(0).get("County/State"));
        fillPostCodeField(personalInfoForms.get(0).get("Postcode"));
    }

    public List<String> getPersonalDetails() {
        return new ArrayList<>(Arrays.asList(getElementValue(deliveryFullNameInput),
                getDropDownElementText(deliveryCountryDropDown),
                getElementValue(deliveryAddressOneInput),
                getElementValue(deliveryAddressTwoInput),
                getElementValue(deliveryCityInput),
                getElementValue(deliveryCountryInput),
                getElementValue(deliveryPostCodeInput)
        ));
    }

    public void pressContinueToPaymentButton(){
        clickElementJS(continueToPaymentBtn);
    }

    private void chooseDeliveryAddress() {
        clickElementJS(deliverySuggestion);
    }
}
