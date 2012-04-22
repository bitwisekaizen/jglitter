package harvard;

import harvard.marshallable.Image;
import harvard.marshallable.Images;
import harvard.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class ImagesController {

    @Autowired
    private ImagesService imagesService;

    public static final String IMAGES_MAPPING = "/images";
    public static final String IMAGES_CONTENT_MAPPING = "/images/content";

    @RequestMapping(value = IMAGES_MAPPING, method = RequestMethod.GET)
    public Images getAllImages() {
        return imagesService.getAllImages();
    }

    @RequestMapping(value = IMAGES_MAPPING, method = RequestMethod.POST)
    public Image uploadImage(@RequestParam(value = "file") MultipartFile file) throws IOException {
        return imagesService.addImage(file.getBytes());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = IMAGES_MAPPING, method = RequestMethod.DELETE)
    public void deleteImage(@RequestParam(value = "uuid") String uuid) {
        imagesService.removeImage(uuid);
    }

    @RequestMapping(value = IMAGES_CONTENT_MAPPING, method = RequestMethod.GET)
    @ResponseBody
    public byte[] getImageContent(@RequestParam(value = "uuid") String uuid) {
        return imagesService.getImageContent(uuid).getContent();
    }
}
