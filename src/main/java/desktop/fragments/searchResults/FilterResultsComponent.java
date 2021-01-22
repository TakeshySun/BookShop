package desktop.fragments.searchResults;

import abstractClasses.fragment.AbstractFragment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static driver.SingletonDriver.getDriver;

public class FilterResultsComponent extends AbstractFragment {
    By refineResultsBtn = By.cssSelector(".btn");

    @FindBy(css = ".search-filter")
    WebElement rootElement;

    public FilterResultsComponent() {
        setRootElement(rootElement);
    }

    public void selectFilterValue(String filterName, String filterValue) {
        selectItemInDropDown(getFilterOption(filterName), filterValue);
    }

    public void clickRefineResultsBtn(){
        clickElementJS(refineResultsBtn);
    }

    private WebElement getFilterOption(String filterName) {
//        return getDriver().findElements(By.cssSelector("form.filter-menu label")).stream()
//                .filter(option -> option.getText().equals(filterName))
//                .findFirst()
//                .orElseThrow(() -> new IllegalStateException("No element"));
        String xpathSelector = String.format("//label[.='%s']/following-sibling::*[1]", filterName);
        return getDriver().findElement(By.xpath(xpathSelector));
    }
}
