package desktop.fragments.checkOutComponents;

import abstractClasses.fragment.AbstractFragment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutTotalComponent extends AbstractFragment {
    By subTotalValue = By.cssSelector("dl:nth-child(2) > dd");
    By deliveryValue = By.cssSelector("dl:nth-child(3) > dd");
    By vatValue = By.cssSelector("dl:nth-child(4) > dd");
    By totalValue = By.cssSelector("dl:nth-child(5) > dd");

    @FindBy(css = ".sidebar.right")
    WebElement rootElement;

    public CheckOutTotalComponent() {
        setRootElement(rootElement);
    }

    public String getSubTotalValue() {
        return rootElement.findElement(subTotalValue).getText();
    }

    public String getDeliveryValue() {
        return rootElement.findElement(deliveryValue).getText();
    }

    public String getVatValue() {
        return rootElement.findElement(vatValue).getText();
    }

    public String getTotalValue() {
        return rootElement.findElement(totalValue).getText();
    }

    }
