package stepDefs.selenide;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import com.codeborne.selenide.Configuration;

public class Hooks {
    @Before
    public void setUp() {
        open("https://www.bookdepository.com/");
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tT %4$s %5$s%6$s%n");
        Configuration.timeout = 3000;
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}
