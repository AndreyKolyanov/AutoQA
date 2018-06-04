package ru.andrewkolyanov.sbt;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Driver {

    private static final Logger LOG = LoggerFactory.getLogger(Driver.class);

    private static WebDriver driver;

    public synchronized static WebDriver getCurrentDriver() {
        if (driver == null) {
            try {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            } finally{
                Runtime.getRuntime().addShutdownHook(
                        new Thread(new BrowserCleanup()));
            }
        }
        return driver;
    }

    private static class BrowserCleanup implements Runnable {
        public void run() {
            LOG.info("Closing the browser");
            close();
        }
    }

    public static void close() {
        try {
            getCurrentDriver().quit();
            driver = null;
            LOG.info("closing the browser");
        } catch (UnreachableBrowserException e) {
            LOG.info("cannot close browser: unreachable browser");
        }
    }
}
