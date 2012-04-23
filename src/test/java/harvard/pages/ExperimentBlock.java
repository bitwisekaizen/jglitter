package harvard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 */
public class ExperimentBlock {

    private WebElement webElement;

    public ExperimentBlock(WebElement webElement) {
        this.webElement = webElement;
    }

    public String getUpperLeftLabel() {
        return webElement.findElement(By.xpath("//input[contains(@id, 'lowerLeftLabel')]")).getText();
    }

    public String getLowerLeftLabel() {
        return webElement.findElement(By.xpath("//input[contains(@id, 'lowerLeftLabel')]")).getText();
    }

    public String getUpperRightLabel() {
        return webElement.findElement(By.xpath("//input[contains(@id, 'upperRightLabel')]")).getText();
    }

    public String getLowerRightLabel() {
        return webElement.findElement(By.xpath("//input[contains(@id, 'lowerRightLabel')]")).getText();
    }
}
