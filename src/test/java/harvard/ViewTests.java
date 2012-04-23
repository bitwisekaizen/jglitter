package harvard;

import harvard.pages.ExperimentPage;
import harvard.pages.ImagesPage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class ViewTests extends AbstractTests {

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private ExperimentPage experimentPage;

    @Autowired
    private ImagesPage imagesPage;

    @BeforeClass
    void setup() {

    }

    @AfterClass(alwaysRun = true)
    void cleanup() {
        webDriver.close();
    }

    @Test
    void canNavigateToImagesPage() {
        imagesPage.go();
        Assert.assertEquals(imagesPage.getTitle(), "Images");
    }

    @Test
    void canNavigateToExperimentsPage() {
        experimentPage.go();
        Assert.assertEquals(experimentPage.getTitle(), "Experiments");
    }

    @Test
    void canCreateNewExperiment() {
        canNavigateToExperimentsPage();

    }

}
