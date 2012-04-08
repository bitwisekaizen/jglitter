package harvard;

import harvard.marshallable.Image;
import harvard.marshallable.Images;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
        images.addImage(image);
        return image;
    }
}
