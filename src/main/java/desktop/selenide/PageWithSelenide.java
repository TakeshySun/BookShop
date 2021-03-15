package desktop.selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class PageWithSelenide {
    public void openPage(){
        open("https://www.bookdepository.com/");
    }

    public void search(){
        $("input.text-input").setValue("Thinking in Java");
        $("button.header-search-btn").click();
    }

    public void searchResultContains(String text){
        $("div.book-item").shouldHave(text(text));
    }

    public void clickAddForBook(String bookName){
        String xpathSelector = String.format("//a[contains(text(),'%s')]/../.././following-sibling::div//a", bookName);
        $(By.xpath(xpathSelector)).click();
    }

    public void clickBuscketChechoutPopup(){
        $("a.continue-to-basket ").should(appear);
        $("a.continue-to-basket ").click();
    }

    public String getDeliveryCostValueText(){
        return $("dl.delivery-text dd").getText();
    }

    public String getTotalValueText(){
        return $("dl.total dd").getText();
    }
}
