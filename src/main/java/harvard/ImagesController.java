package harvard;

import harvard.marshallable.Image;
import harvard.marshallable.Images;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ImagesController {

    public final static String IMAGES_MAPPING = "/images";

    private Images images = new Images();

    @RequestMapping(value = IMAGES_MAPPING, method = RequestMethod.GET)
    public Images testGet() {
        return images;
    }

    @RequestMapping(value = IMAGES_MAPPING, method = RequestMethod.POST)
    public Image uploadImage(@RequestParam(value = "file") MultipartFile file) {
        Image image = new Image();
        image.setUuid(UUID.randomUUID().toString());
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
}
