package harvard;

import harvard.pages.ExperimentBlock;
import harvard.pages.ExperimentPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

@Test
public class ExperimentViewTests extends AbstractViewTests {

    @Autowired
    private ExperimentPage experimentPage;

    private String experimentName = "Dummy";

    @BeforeMethod
    void setup() {
        experimentPage.go();
        assertEquals(experimentPage.getTitle(), "Experiments");

        experimentPage.createExperiment(experimentName);
        experimentPage.selectExperiment(experimentName);
    }

    @AfterMethod
    void cleanupMethod() {
        experimentPage.deleteExperiment(experimentName);
        assertFalse(experimentPage.containsExperiment(experimentName), "Experiment in list after delete.");
    }

    @Test
    void canModifyExperimentInstructions() {
        assertEquals(experimentPage.getInstructions(), "");
        experimentPage.setInstructions("Some stuff in the instructions.");
        experimentPage.save();
    }

    @Test
    void canAddBlockToExperiment() {
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
