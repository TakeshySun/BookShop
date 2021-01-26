package desktop.fragments.searchResults;

import abstractClasses.fragment.AbstractFragment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static driver.SingletonDriver.getDriver;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsComponent extends AbstractFragment {

    private final By bookName = By.cssSelector("h3 a");

    @FindBy(css = ".search-page")
    private WebElement rootElement;

    public SearchResultsComponent() {
        setRootElement(rootElement);
    }

    public List<String> getAllBookNames() {
        waitForPageLoaded();
        return getAllBookItems().stream()
                .map(book -> book.findElement(bookName).getText())
                .collect(Collectors.toList());
    }

    public void clickAddToBasketForSelectedBook(String bookName){
        String xpathSelector = String.format("//a[contains(text(),'%s')]/../.././following-sibling::div//a", bookName);
        By bookAddToBasket = By.xpath(xpathSelector);
        clickElementJS(bookAddToBasket);
    }

    private List<WebElement> getAllBookItems() {
        return getDriver().findElements(By.cssSelector("div.book-item"));
    }


}
