package harvard.marshallable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Images {

    private List<Image> images = new ArrayList<Image>();

    public Images() { }

    public Images(List<harvard.persistable.Image> dbImages) {
        images.clear();
        for (harvard.persistable.Image dbImage : dbImages) {
            images.add(new Image(dbImage.getUuid()));
        }
    }

    @XmlElement(name = "image")
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void addImage(Image image) {
        images.add(image);
    }
}
