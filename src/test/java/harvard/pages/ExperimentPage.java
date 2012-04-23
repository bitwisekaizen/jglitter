package harvard.pages;

import harvard.UrlHelper;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 */
@Component
public class ExperimentPage extends AbstractPage {

    @Autowired
    public ExperimentPage(WebDriver webDriver, UrlHelper urlHelper) {
        super(webDriver, urlHelper);
    }

    public void go() {
        webDriver.get(urls.views().experiments());
    }
}
