package lbtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA. User: gavin Date: 4/7/12 Time: 12:50 PM To change this template use File | Settings | File
 * Templates.
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test")
    public void testGet() {

    }
}
