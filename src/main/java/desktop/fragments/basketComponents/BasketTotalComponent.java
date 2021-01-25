package desktop.fragments.basketComponents;

import abstractClasses.fragment.AbstractFragment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketTotalComponent extends AbstractFragment {
    By deliveryCostValue = By.cssSelector("dl.delivery-text dd");
    By totalValue = By.cssSelector("dl.total dd");
    By checkoutBtn = By.cssSelector(".checkout-btn");

    @FindBy (css = ".basket-totals-wrap")
    WebElement fragmentRootElement;

    public BasketTotalComponent() {
        setRootElement(fragmentRootElement);
    }

    public String getDeliveryCostValueText(){
        return getRootElement().findElement(deliveryCostValue).getText();
    }

    public String getTotalValueText(){
        return getRootElement().findElement(totalValue).getText();
    }

    public void clickCheckoutBtn(){
        clickElement(checkoutBtn);
    }

}
