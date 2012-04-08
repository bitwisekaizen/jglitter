package harvard.persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA. User: gavin Date: 4/7/12 Time: 10:58 PM To change this template use File | Settings | File
 * Templates.
 */
@Entity
public class ImageContent {
    private Long id;
    private byte[] content;


    @Column(name = "content")
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
