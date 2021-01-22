package abstractClasses.fragment;

import io.cucumber.java.en_old.Ac;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.WebDriverWaiter;

import java.util.List;

import static driver.SingletonDriver.getDriver;

public abstract class AbstractFragment extends WebDriverWaiter {

    private WebElement rootElement;

    public AbstractFragment() {
        PageFactory.initElements(getDriver(), this);
    }

    public void setRootElement(WebElement element) {
        this.rootElement = element;
    }

    public WebElement getRootElement() {
        return rootElement;
    }

    public void clickElement(By byLocator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));

        Actions builder = new Actions(getDriver());
        builder.moveToElement(rootElement.findElement(byLocator))
                .click()
                .build()
                .perform();
    }

    public void clickElementJS(By byLocator){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", getDriver().findElement(byLocator));
    }

    public void setValueToInput(By locator, String keys) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        Actions builder = new Actions(getDriver());
        builder.moveToElement(rootElement.findElement(locator))
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(keys)
                .build()
                .perform();
    }

    public void selectItemInDropDownByCss(By locator, String dropDownItemName) {
        Select statusesSelect = new Select(rootElement.findElement(locator));
        statusesSelect.selectByVisibleText(dropDownItemName);
    }

    public void selectItemInDropDown(WebElement element, String dropDownItemName) {
        Select statusesSelect = new Select(element);
        statusesSelect.selectByVisibleText(dropDownItemName);
        statusesSelect.selectByVisibleText(dropDownItemName);
    }

    public boolean isElementsContainsProvidedText(String text, By byLocator){
        List<WebElement> elements = getDriver().findElements(byLocator);
        return elements.stream().anyMatch(e->e.getText().trim().equals(text));
    }

}
