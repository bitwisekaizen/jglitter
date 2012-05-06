package harvard;

import harvard.marshallable.Experiment;
import harvard.marshallable.Experiments;
import harvard.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IntelliJ IDEA. User: gavin Date: 4/30/12 Time: 9:21 PM To change this template use File | Settings | File
 * Templates.
 */
@Controller
public class ExperimentsController {

    public static final String EXPERIMENTS_MAPPING = "/experiments";
    public static final String EXPERIMENTS_DELETE_MAPPING = "/experiments/delete";

    @Autowired
    private ExperimentService experimentService;

    @RequestMapping(value = EXPERIMENTS_MAPPING, method = RequestMethod.POST)
    public Experiment createExperiment(@RequestParam String name, @RequestParam String instructions) {
        return experimentService.createExperiment(name, instructions);
    }

    @RequestMapping(value = EXPERIMENTS_MAPPING, method = RequestMethod.GET)
    public Experiments getExperiments() {
        Experiments experiments = experimentService.getExperiments();
        return experiments;
    }

    @RequestMapping(value = EXPERIMENTS_DELETE_MAPPING, method = RequestMethod.POST)
    public Experiment deleteExperiment(@RequestParam String name) {
        return experimentService.deleteExperiment(name);
    }
}
