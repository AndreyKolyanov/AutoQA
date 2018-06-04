package ru.andrewkolyanov.sbt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.andrewkolyanov.sbt.Driver;

import java.util.List;

public class Page {

    private static final Logger LOG = LoggerFactory.getLogger(Page.class);

    private WebDriver driver;
    private String rememberElementTitle;

    public Page(String url) {
        this.driver = Driver.getCurrentDriver();
        driver.get(url);
    }

    public Page clickOnLink(String linkText) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By by = By.linkText(linkText);
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        for (WebElement element: elements) {
            try {
                element.click();
            } catch (Exception e) {
                LOG.error("Can't go to link " + linkText, e);
            }
        }
        return this;
    }

    public Page typeValueToField(String filterTitle, String fieldTitle, String value) {
        String locator = "//span[@sign-title='%s']//input";
        WebElement filter = findFilterByName(filterTitle);
        WebElement input = filter.findElement(By.xpath(String.format(locator, fieldTitle)));
        input.sendKeys(value);
        return this;
    }

    public Page selectCheckbox(String filterTitle, String checkboxLabel) {
        WebElement filter = findFilterByName(filterTitle);
        WebElement checkbox = filter.findElement(By.linkText(checkboxLabel));
        checkbox.click();
        return this;
    }

    public int countSearchElements() {
        return driver.findElements(By.cssSelector(".n-snippet-list > div.n-snippet-card2")).size();
    }

    public void rememberElement() {
        rememberElementTitle = driver.findElement(By.cssSelector(".n-snippet-list:first-child .n-snippet-card2__title a")).getText();
    }

    public Page findRememberElement() {
        WebElement search = driver.findElement(By.cssSelector("#header-search"));
        search.sendKeys(getRememberElementTitle());
        driver.findElement(By.xpath("//span[text()='Найти']/..")).click();
        return this;
    }

    public String getFindElementTitle() {
        return driver.findElement(By.cssSelector("h1.title")).getText();
    }

    public String getRememberElementTitle() {
        return rememberElementTitle;
    }

    private WebElement findFilterByName(String name) {
        String locator = "//span[starts-with(.,'%s')]/../../..";
        return driver.findElement(By.xpath(String.format(locator, name)));
    }
}
