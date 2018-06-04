package ru.andrewkolyanov.sbt;

import cucumber.api.java.en.Given;
import ru.andrewkolyanov.sbt.pages.Page;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SiteNavigationSteps {

    private Page page;

    @Given("^Перейти на сайт '(.*)'$")
    public void openSite(String url) {
        page = new Page(url);
    }

    @Given("^Перейти по ссылке '(.*)'$")
    public void clickOnLink(String linkText) {
        page = page.clickOnLink(linkText);
    }

    @Given("^В параметре '(.*)' ввести в поле '(.*)' значение '(.*)'$")
    public void typeValueToField(String filterTitle, String fieldTitle, String value) {
        page = page.typeValueToField(filterTitle, fieldTitle, value);
    }

    @Given("^В параметре '(.*)' выбрать значение '(.*)'$")
    public void selectCheckbox(String filterTitle, String checkboxLabel) {
        page = page.selectCheckbox(filterTitle, checkboxLabel);
    }

    @Given("^Проверить что количество элементов равно (.*)$")
    public void checkElementsCount(int expectedCount) {
        assertEquals("Количество элементов не равно ожидаемому", expectedCount, page.countSearchElements());
    }

    @Given("^Запомнить первый элемент$")
    public void rememberFirstElement() {
        page.rememberElement();
    }

    @Given("^Найти запомненное значение$")
    public void findRememberValue() {
        page = page.findRememberElement();
    }

    @Given("^Сопоставить запомненное значение с найденным$")
    public void matchValueWithFound() {
        assertFalse("Наименование элементов не совпадает", page.getFindElementTitle().equals(page.getRememberElementTitle()));
    }
}
