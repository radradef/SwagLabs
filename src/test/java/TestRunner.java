import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features",
glue = "stepDefinitions",
plugin = {"pretty" , "html:reports/cucumber-reports.html"})

public class TestRunner {
}
