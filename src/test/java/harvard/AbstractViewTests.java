package harvard;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

@Test
public abstract class AbstractViewTests extends AbstractTests {

    @Autowired
    private WebDriver webDriver;

    @AfterClass(alwaysRun = true)
    void cleanupClass() {
        webDriver.close();
    }
}
