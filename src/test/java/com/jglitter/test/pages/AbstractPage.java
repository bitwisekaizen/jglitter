package com.jglitter.test.pages;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractPage implements Navigable {

    protected WebDriver webDriver;

    @Autowired
    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getTitle() {
        return webDriver.getTitle();
    }
}
