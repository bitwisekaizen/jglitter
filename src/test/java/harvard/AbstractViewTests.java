package harvard;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(value = "classpath:ui-test-context.xml")
public abstract class AbstractViewTests extends AbstractTests {

    @Autowired
    private WebDriver webDriver;

    @AfterSuite(alwaysRun = true)
    void cleanupSuite() {
        webDriver.close();
    }
}
