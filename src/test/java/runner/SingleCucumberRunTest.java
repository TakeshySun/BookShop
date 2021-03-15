package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = "stepDefs",
        plugin = {
                "pretty",
//                "json:target/cucumber-reports/Cucumber.json",
                "com.epam.reportportal.cucumber.ScenarioReporter"
//                "com.epam.reportportal.cucumber.StepReporter"
                },
        tags = "@Runme"
)
// Parallel run: mvn clean test -DbrowserName=chrome or firefox
public class SingleCucumberRunTest {
}
