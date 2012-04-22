package harvard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(value = "classpath:test-context.xml")
public class ExperimentTests extends AbstractTestNGSpringContextTests {

    private WebDriver webDriver;

    @Autowired
    private UrlHelper urls;

    @Test
    void canNavigateToImagesPage() {
        webDriver = new FirefoxDriver();
        webDriver.get(urls.views().images());
        Assert.assertEquals(webDriver.getTitle(), "Images");
        webDriver.close();
    }
}
