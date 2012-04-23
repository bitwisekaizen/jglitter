package harvard;

import harvard.pages.ExperimentBlock;
import harvard.pages.ExperimentPage;
import harvard.pages.ImagesPage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

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
        String experimentName = "Dummy";
        canNavigateToExperimentsPage();
        experimentPage.createExperiment(experimentName, "Test Description");
        experimentPage.selectExperiment(experimentName);
        experimentPage.deleteExperiment(experimentName);
        Assert.assertFalse(experimentPage.containsExperiment(experimentName), "Experiment in list after delete.");
    }

    @Test
    void canAddBlockToExperiment() {
        canCreateNewExperiment();
        experimentPage.addBlock();
        List<ExperimentBlock> blocks = experimentPage.getBlocks();
        Assert.assertEquals(blocks.size(),  1, "Experiment should have exactly one block.");
        ExperimentBlock block = blocks.get(0);
        Assert.assertEquals(block.getUpperLeftLabel(), "");
        Assert.assertEquals(block.getLowerLeftLabel(), "");
        Assert.assertEquals(block.getUpperRightLabel(), "");
        Assert.assertEquals(block.getLowerRightLabel(), "");
    }

}
