package desktop.pages;

import abstractClasses.page.AbstractPage;
import desktop.fragments.HeaderComponent;
import org.openqa.selenium.JavascriptExecutor;

import static driver.SingletonDriver.getDriver;

public class BasePage extends AbstractPage {
    public HeaderComponent headerComponent = new HeaderComponent();

    public String getPageTitle() {
        JavascriptExecutor javascript = (JavascriptExecutor) getDriver();
        //Get current page title
        return (String)javascript.executeScript("return document.title");
    }


}
