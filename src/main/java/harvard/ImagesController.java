package harvard;

import harvard.marshallable.Image;
import harvard.marshallable.ImageContent;
import harvard.marshallable.Images;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@Controller
public class ImagesController {

    public static final String IMAGES_MAPPING = "/images";
    public static final String IMAGES_CONTENT_MAPPING = "/images/content";

    private Images images = new Images();
    private Map<String, byte[]> imageContentMap = new HashMap<String, byte[]>();

    @RequestMapping(value = IMAGES_MAPPING, method = RequestMethod.GET)
    public Images testGet() {
        return images;
    }

    @RequestMapping(value = IMAGES_MAPPING, method = RequestMethod.POST)
    public Image uploadImage(@RequestParam(value = "file") MultipartFile file) throws IOException {
        Image image = new Image();
        image.setUuid(UUID.randomUUID().toString());
        imageContentMap.put(image.getUuid(), file.getBytes());
        images.addImage(image);
        return image;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = IMAGES_MAPPING, method = RequestMethod.DELETE)
    public void deleteImage(@RequestParam(value = "uuid") String uuid) {
        List<Image> imagesToRemove = new ArrayList<Image>();
        for (Image image : images.getImages()) {
            if (image.getUuid().equals(uuid)) {
                imagesToRemove.add(image);
            }
        }
        images.getImages().removeAll(imagesToRemove);
    }

    @RequestMapping(value = IMAGES_CONTENT_MAPPING, method = RequestMethod.GET)
    public ImageContent getImageContent(@RequestParam(value = "uuid") String uuid) {
        ImageContent imageContent = new ImageContent();
        imageContent.setContent(imageContentMap.get(uuid));
        return imageContent;
    }
}
