package harvard.pages;

import harvard.UrlHelper;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

/**
 */
public abstract class AbstractPage implements Navigable {

    protected UrlHelper urls;
    protected WebDriver webDriver;

    @Autowired
    public AbstractPage(WebDriver webDriver, UrlHelper urlHelper) {
        this.webDriver = webDriver;
        this.urls = urlHelper;
    }

    public String getTitle() {
        return webDriver.getTitle();
    }
}
