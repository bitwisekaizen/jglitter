package harvard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ExperimentTests extends AbstractTests {

    private WebDriver webDriver;

    @Test
    void canNavigateToImagesPage() {
        webDriver = new FirefoxDriver();
        webDriver.get(urls.views().images());
        Assert.assertEquals(webDriver.getTitle(), "Images");
        webDriver.close();
    }
}
