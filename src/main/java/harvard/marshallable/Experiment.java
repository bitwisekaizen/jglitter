package harvard.marshallable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Experiment {

    private String uuid;
    private String name;
    private String instructions;

    protected Experiment() {}

    public Experiment(harvard.persistable.Experiment experiment) {
        this.uuid = experiment.getUuid();
        this.name = experiment.getName();
        this.instructions = experiment.getInstructions();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
