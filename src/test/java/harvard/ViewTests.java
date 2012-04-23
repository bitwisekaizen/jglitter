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

import static org.testng.Assert.assertEquals;

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
        assertEquals(imagesPage.getTitle(), "Images");
    }

    @Test
    void canNavigateToExperimentsPage() {
        experimentPage.go();
        assertEquals(experimentPage.getTitle(), "Experiments");
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
        assertEquals(experimentPage.getBlocks().size(), 0, "Before block is created, experiment should have one block.");
        experimentPage.addBlock();
        List<ExperimentBlock> blocks = experimentPage.getBlocks();
        assertEquals(blocks.size(), 1, "Experiment should have exactly one block.");
        ExperimentBlock block = blocks.get(0);
        assertEquals(block.getUpperLeftLabel(), "");
        assertEquals(block.getLowerLeftLabel(), "");
        assertEquals(block.getUpperRightLabel(), "");
        assertEquals(block.getLowerRightLabel(), "");
    }

}
