package ru.andrewkolyanov.sbt;

import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import ru.andrewkolyanov.sbt.drivers.MyFirefoxDriver;

public class SiteNavigationSteps {

    @Given("^Перейти на сайт '(.*)'$")
    public void openSite(String url) {
        WebDriver driver = MyFirefoxDriver.getCurrentDriver();
        driver.get(url);
    }
    @Given("^Перейти по ссылке '(.*)'$")
    public void clickOnLink(String linkText) {
        System.out.println("Перейти по ссылке: " + linkText);
    }

    @Given("^Ввести в поле '(.*)' значение '(.*)'$")
    public void typeValueToField(String fieldTitle, String value) {
        System.out.println("Ввести в поле '"+ fieldTitle + "' значение '" + value + "'");
    }

    @Given("^Выбрать параметр '(.*)'$")
    public void selectCheckbox(String checkboxLabel) {
        System.out.println("Выбрать параметр " + checkboxLabel);
    }

    @Given("^Нажать на кнопку '(.*)'$")
    public void clickOnTheButton(String buttonText) {
        System.out.println("Нажать на кнопку " + buttonText);
    }

    @Given("^Проверить что количество элементов равно (.*)$")
    public void checkElementsCount(int expectedCount) {
        System.out.println("Проверить что количество элементов равно " + expectedCount);
    }

    @Given("^Запомнить первый элемент$")
    public void rememberFirstElement() {
        System.out.println("Запомнить первый элемент");
    }

    @Given("^Найти запомненное значение$")
    public void findRememberValue() {
        System.out.println("Найти запомненное значение");
    }

    @Given("^Сопоставить запомненное значение с найденным$")
    public void matchValueWithFound() {
        System.out.println("Сопоставить запомненное значение с найденным");
    }
}
