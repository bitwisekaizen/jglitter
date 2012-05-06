package harvard;

import harvard.marshallable.Experiment;
import harvard.marshallable.Experiments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

@Test
public class ExperimentControllerTests extends AbstractTests {

    @Autowired
    private RestTemplate template;

    @Test
    void canCreateExperiment() {
        String experimentName = "moo " + System.currentTimeMillis();
        String experimentInstructions = "some instructions to follow...or else!";
        Map<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("name", experimentName);
        requestParams.put("instructions", experimentInstructions);

        Object requestBody = null;
        List<Experiment> originalExperiments = getAllExperiments();

        // create the experiment
        Experiment createdExperiment = template.postForEntity(urls.getExperimentsUrl() + "?name={name}&instructions={instructions}", requestBody, Experiment.class, requestParams).getBody();

        // get the list of experiments
        List<Experiment> newExperiments = getAllExperiments();
        assertEquals(newExperiments.size(), originalExperiments.size() + 1, "Should have exactly one more experiment than when the test began");

        Assert.assertTrue(containsExperiment(newExperiments, experimentName), "Experiment named " + experimentName + " was not found in list.");

        // delete the experiment
        Map<String, String> deleteParams = new HashMap<String, String>();
        deleteParams.put("name", createdExperiment.getName());
        template.postForEntity(urls.getDeleteExperimentsUrl() + "?name={name}", requestBody, Experiment.class, deleteParams);
    }

    private boolean containsExperiment(List<Experiment> experiments, String name) {
        for (Experiment experiment : experiments) {
            if (experiment.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Experiment> getAllExperiments() {
        Experiments experiments = template.getForEntity(urls.getExperimentsUrl(), Experiments.class).getBody();
        return experiments.getExperiments();
    }
}
