package harvard.persistable;

import javax.persistence.*;

/**
 */
@Entity
@Table(name = Image.TABLE_NAME)
public class Image {

    public static final String TABLE_NAME = "images";
    public static final String UUID_COLUMN_NAME = "uuid";

    private Long id;
    private String uuid;
    private ImageContent imageContent;

    public Image() { }

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "image")
    public ImageContent getImageContent() {
        return imageContent;
    }

    public void setImageContent(ImageContent imageContent) {
        this.imageContent = imageContent;
    }
}
