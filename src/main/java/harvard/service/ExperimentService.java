package harvard.service;

import harvard.dao.ExperimentsDao;
import harvard.marshallable.Experiment;
import harvard.marshallable.Experiments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ExperimentService {

    @Autowired
    private ExperimentsDao experimentsDao;

    public Experiment createExperiment(String name, String instructions) {
        return new Experiment(experimentsDao.addExperiment(name, instructions));
    }

    @Transactional(readOnly = true)
    public Experiments getExperiments() {
        return new Experiments(experimentsDao.getAllExperiments());
    }

    public Experiment deleteExperiment(String name) {
        return new Experiment(experimentsDao.deleteExperiment(name));
    }
}
