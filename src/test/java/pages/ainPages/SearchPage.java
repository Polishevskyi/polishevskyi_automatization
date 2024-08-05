package pages.ainPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {
    private final SelenideElement searchField = $x("//input[@placeholder='Пошук']");
    private final SelenideElement searchBtn = $x("//button[@type='submit']//*[name()='svg']");

    public SearchPage addTextToSearchField(String text) {
        searchField.click();
        searchField.setValue(text);
        return this;
    }

    public SearchResultPage clickOnSearchBtn() {
        searchBtn.click();
        return new SearchResultPage();
    }
}
