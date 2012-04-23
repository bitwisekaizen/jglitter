package harvard.pages;

import harvard.UrlHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public boolean containsExperiment(String name) {
        try {
            selectExperiment(name);
            return true;
        } catch (WebDriverException e) {
            return false;
        }

    }

    public void addBlock() {
        webDriver.findElement(By.id("addBlockButton")).click();
    }

    public List<ExperimentBlock> getBlocks() {
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@id='blockAccordion']/div"));
        List<ExperimentBlock> blocks = new ArrayList<ExperimentBlock>();
        for (WebElement element : elements) {
            blocks.add(new ExperimentBlock(element));
        }
        return blocks;
    }
}
