package harvard.service;

import harvard.marshallable.Image;
import harvard.marshallable.ImageContent;
import harvard.marshallable.Images;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by IntelliJ IDEA. User: gavin Date: 4/8/12 Time: 12:00 AM To change this template use File | Settings | File
 * Templates.
 */
@Component
public class ImagesService {

    @Autowired
    private SessionFactory sessionFactory;

    private Images images = new Images();
    private Map<String, byte[]> imageContentMap = new HashMap<String, byte[]>();

    public Images getAllImages() {
        return images;
    }

    public Image addImage(byte[] bytes) {
        Image image = new Image();
        image.setUuid(UUID.randomUUID().toString());
        imageContentMap.put(image.getUuid(), bytes);
        images.addImage(image);
        return image;
    }

    public void removeImage(String uuid) {
        List<Image> imagesToRemove = new ArrayList<Image>();
        for (Image image : images.getImages()) {
            if (image.getUuid().equals(uuid)) {
                imagesToRemove.add(image);
            }
        }
        images.getImages().removeAll(imagesToRemove);
    }

    public ImageContent getImageContent(String uuid) {
        ImageContent imageContent = new ImageContent();
        imageContent.setContent(imageContentMap.get(uuid));
        return imageContent;
    }
}
