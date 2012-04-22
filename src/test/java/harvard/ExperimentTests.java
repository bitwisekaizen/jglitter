package harvard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(value = "classpath:test-context.xml")
public class ExperimentTests extends AbstractTestNGSpringContextTests {

    private WebDriver webDriver;

    @Test
    void canNavigateToExperimentsPage() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://google.com");
        Assert.assertEquals(webDriver.getTitle(), "Google");
        webDriver.close();
    }
}
