package desktop.fragments.checkOutComponents;

import abstractClasses.fragment.AbstractFragment;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class CheckOutPaymentComponent extends AbstractFragment {
    By cardTypeDropDown = By.cssSelector("select#brandSelected");
    By cardNumber = By.cssSelector("input#visacardNumber");
    By cardMonth = By.cssSelector("select#visacardValidToMonth");
    By cardYear = By.cssSelector("select#visacardValidToYear");
    By cardName = By.cssSelector("input#visacardName");
    By cardCvv = By.cssSelector("input#visacardCvv");

    @FindBy(css = "div#payInputContainer")
    WebElement rootElement;

    public CheckOutPaymentComponent() {
        setRootElement(rootElement);
    }

    public void setCardType(String cardType){
        selectItemInDropDown(rootElement.findElement(cardTypeDropDown),cardType);
    }

    public void setCardNumber(String cardNumberName){
        setValueToInput(cardNumber, cardNumberName);
    }

    public void setCardMonth(String month){
        selectItemInDropDown(rootElement.findElement(cardMonth), month);
    }

    public void setCardYear(String year){
        selectItemInDropDown(rootElement.findElement(cardYear), year);
    }

    public void setCardName(String name){
        setValueToInput(cardName, name);
    }

    public void setCardCvv(String cvv){
        setValueToInput(cardCvv, cvv);
    }

    public void setCardInformation(DataTable dataTable){
        Map<String, String> cardInfo = dataTable.asMap(String.class, String.class);
        switchToIFrame(driver.findElement(By.cssSelector("iframe#chase.global-checkout-chase")));
        setCardType(cardInfo.get("Card Type"));
        setCardName(cardInfo.get("Name On Card"));
        setCardNumber(cardInfo.get("cardNumber"));
        setCardMonth(cardInfo.get("Expiry Month"));
        setCardYear(cardInfo.get("Expiry Year"));
        setCardCvv(cardInfo.get("Cvv"));
    }
}
