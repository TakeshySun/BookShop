package desktop.fragments.checkOutComponents;

import abstractClasses.fragment.AbstractFragment;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutDisablePaymentComponent extends AbstractFragment {

    @FindBy(css = ".disabled-container")
    WebElement rootElement;

    public CheckOutDisablePaymentComponent() {
        setRootElement(rootElement);
    }

    public boolean isPaymentSectionDisabled(){
        return rootElement.isDisplayed();
    }

}
