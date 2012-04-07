package lbtest;

import lbtest.marshallable.Images;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by IntelliJ IDEA. User: gavin Date: 4/7/12 Time: 12:50 PM To change this template use File | Settings | File
 * Templates.
 */
@Controller
public class TestController {

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public Images testGet() {
        return new Images();
    }
}
