package desktop.fragments;

import abstractClasses.fragment.AbstractFragment;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExampleFragment extends AbstractFragment {

    @FindBy(xpath = "put your locator expression here")
    private WebElement fragmentRootElement;

    public ExampleFragment() {
        setRootElement(fragmentRootElement);
    }
}
