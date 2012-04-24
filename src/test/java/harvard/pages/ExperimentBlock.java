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
        return webElement.findElement(By.xpath("//input[contains(@id, 'upperLeftLabel')]")).getAttribute("value");
    }

    public String getLowerLeftLabel() {
        return webElement.findElement(By.xpath("//input[contains(@id, 'lowerLeftLabel')]")).getAttribute("value");
    }

    public String getUpperRightLabel() {
        return webElement.findElement(By.xpath("//input[contains(@id, 'upperRightLabel')]")).getAttribute("value");
    }

    public String getLowerRightLabel() {
        return webElement.findElement(By.xpath("//input[contains(@id, 'lowerRightLabel')]")).getAttribute("value");
    }

    public void setUpperLeftLabel(String label) {
        webElement.findElement(By.xpath("//input[contains(@id, 'upperLeftLabel')]")).sendKeys(label);
    }

    public void setLowerLeftLabel(String label) {
        webElement.findElement(By.xpath("//input[contains(@id, 'lowerLeftLabel')]")).sendKeys(label);
    }

    public void setUpperRightLabel(String label) {
        webElement.findElement(By.xpath("//input[contains(@id, 'upperRightLabel')]")).sendKeys(label);
    }

    public void setLowerRightLabel(String label) {
        webElement.findElement(By.xpath("//input[contains(@id, 'lowerRightLabel')]")).sendKeys(label);
    }

}
