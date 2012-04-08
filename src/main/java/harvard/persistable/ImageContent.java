package harvard.persistable;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by IntelliJ IDEA. User: gavin Date: 4/7/12 Time: 10:58 PM To change this template use File | Settings | File
 * Templates.
 */
@Entity
@Table(name="image_contents")
public class ImageContent {
    private Long id;
    private Blob content;

    private Image image;

    public ImageContent() { }

    @Column(name = "content")
    @Lob
    public Blob getContent() {
        return content;
    }

    public void setContent(Blob content) {
        this.content = content;
    }

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
