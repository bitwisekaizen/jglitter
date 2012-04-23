package harvard.pages;

import harvard.UrlHelper;
import org.openqa.selenium.By;
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

    public void createExperiment(String name, String description) {
        webDriver.findElement(By.id("nameTextBox")).sendKeys(name);
        webDriver.findElement(By.id("descriptionTextBox")).sendKeys(description);
        webDriver.findElement(By.id("createButton")).click();
    }

    public void selectExperiment(String name) {
        webDriver.findElement(By.xpath("//select[@id='experimentList']/option[text()='" + name + "']"));
    }

    public void deleteExperiment(String name) {
        selectExperiment(name);
        webDriver.findElement(By.id("deleteButton")).click();
    }
}
