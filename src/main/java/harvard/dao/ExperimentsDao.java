package harvard.dao;

import harvard.persistable.Experiment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.thegrayfiles.hqlbuilder.HQLBuilder.hql;
import static com.thegrayfiles.hqlbuilder.HQLConditionFactory.isEqual;

@Repository
public class ExperimentsDao {
    @Autowired
    private SessionFactory factory;

    public List<Experiment> getAllExperiments() {
        return hql(factory.getCurrentSession()).from("Experiment").list(Experiment.class);
    }

    public Experiment addExperiment(String name, String instructions) {
        Session session = factory.getCurrentSession();
        Experiment experiment = new Experiment(UUID.randomUUID().toString(), name, instructions);
        session.save(experiment);
        return experiment;
    }

    public Experiment deleteExperiment(String name) {
        Session session = factory.getCurrentSession();
        Experiment experiment = getExperimentByName(session, name);
        session.delete(experiment);
        return experiment;
    }

    private Experiment getExperimentByName(Session session, String name) {
        return hql(session).from("Experiment").where(isEqual(Experiment.NAME_COLUMN_NAME, name)).uniqueResult(Experiment.class);
    }
}
