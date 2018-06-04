package ru.andrewkolyanov.sbt;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import ru.andrewkolyanov.sbt.pages.Page;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SiteNavigationSteps {

    private Page page;
    private List<String> searchResult;
    private String firstSearchElementTitle;

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
        page = page.typeValueToFilterField(filterTitle, fieldTitle, value);
    }

    @Given("^В параметре '(.*)' выбрать значение '(.*)'$")
    public void selectCheckbox(String filterTitle, String checkboxLabel) {
        page = page.selectCheckbox(filterTitle, checkboxLabel);
    }

    @Then("^Проверить что количество элементов равно (.*)$")
    public void checkElementsCount(int expectedCount) {
        searchResult = page.findElements();
        assertEquals("Количество элементов не равно ожидаемому", expectedCount, searchResult.size());
    }

    @Given("^Запомнить первый элемент$")
    public void rememberFirstElement() {
        firstSearchElementTitle = searchResult.get(0);
    }

    @Given("^Найти запомненное значение$")
    public void findRememberValue() {
        page = page.find(firstSearchElementTitle);
    }

    @Then("^Сопоставить запомненное значение с найденным$")
    public void matchValueWithFound() {
        assertTrue(page.getFindElementTitle().contains(firstSearchElementTitle));
    }
}
