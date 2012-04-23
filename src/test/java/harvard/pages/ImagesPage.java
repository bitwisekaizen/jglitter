package harvard.pages;

import harvard.UrlHelper;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 */
@Component
public class ImagesPage extends AbstractPage {

    @Autowired
    public ImagesPage(WebDriver webDriver, UrlHelper urlHelper) {
        super(webDriver, urlHelper);
    }

    public void go() {
        webDriver.get(urls.views().images());
    }
}
