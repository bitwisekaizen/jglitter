package harvard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class ViewTests extends AbstractTests {

    private WebDriver webDriver;

    @BeforeClass
    void setup() {
        webDriver = new FirefoxDriver();
    }

    @AfterClass(alwaysRun = true)
    void cleanup() {
        webDriver.close();
    }

    @Test
    void canNavigateToImagesPage() {
        webDriver.get(urls.views().images());
        Assert.assertEquals(webDriver.getTitle(), "Images");
    }

    @Test
    void canNavigateToExperimentsPage() {
        webDriver.get(urls.views().experiments());
        Assert.assertEquals(webDriver.getTitle(), "Experiments");
    }
}
