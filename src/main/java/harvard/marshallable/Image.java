package harvard.marshallable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 */
@XmlRootElement
public class Image {

    private String uuid;

    public Image() { }

    public Image(String uuid) {
        setUuid(uuid);
    }

    public Image(harvard.persistable.Image dbImage) {
        setUuid(dbImage.getUuid());
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
