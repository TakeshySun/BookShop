package desktop.fragments.searchResults;

import abstractClasses.fragment.AbstractFragment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResultsPopUpComponent extends AbstractFragment {
    By basketCheckoutBtn = By.cssSelector("a.continue-to-basket ");

    @FindBy(css = ".modal-content")
    private WebElement rootElement;

    public ResultsPopUpComponent() {
        setRootElement(rootElement);
    }

    public void clickBasketCheckoutBtn(){
        clickElementJS(basketCheckoutBtn);
    }

}
