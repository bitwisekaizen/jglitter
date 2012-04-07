package harvard;

import harvard.marshallable.Images;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ImagesController {

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public Images testGet() {
        return new Images();
    }
}
