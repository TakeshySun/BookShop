package desktop.fragments;

import abstractClasses.fragment.AbstractFragment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends AbstractFragment {

    private final By headerSearchFieldInput = By.cssSelector("input.text-input");
    private final By headerSearchBtn = By.cssSelector("button.header-search-btn");

    @FindBy(css = ".primary-wrap")
    private WebElement rootElement;

    public HeaderComponent() {
        setRootElement(rootElement);
    }

    public void fillInSearchInput(String searchName){
        setValueToInput(headerSearchFieldInput, searchName);
    }

    public void clickSearchBtn(){
        clickElement(headerSearchBtn);
    }
}
