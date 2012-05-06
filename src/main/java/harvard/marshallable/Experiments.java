package harvard.marshallable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Experiments {
    private List<Experiment> experiments = new ArrayList<Experiment>();

    protected Experiments () {}

    public Experiments(List<harvard.persistable.Experiment> dbExperiments) {
        for (harvard.persistable.Experiment dbExperiment : dbExperiments) {
            this.experiments.add(new Experiment(dbExperiment));
        }
    }

    @XmlElement(name = "experiment")
    public List<Experiment> getExperiments() {
        return experiments;
    }

    public void setExperiments(List<Experiment> experiments) {
        this.experiments = experiments;
    }
}
