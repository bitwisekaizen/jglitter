package harvard.persistable;

import javax.persistence.*;

/**
 */
@Entity
@Table(name = Experiment.TABLE_NAME)
public class Experiment {

    public static final String TABLE_NAME = "experiments";

    private Long id;
    private String uuid;
    private String name;
    private String instructions;
    public static final String NAME_COLUMN_NAME = "name";

    protected Experiment() {}

    public Experiment(String uuid, String name, String instructions) {
        this.uuid = uuid;
        this.name = name;
        this.instructions = instructions;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "instructions")
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
