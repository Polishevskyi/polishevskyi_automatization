package pages.ainPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SearchResultPage {
    private final SelenideElement firstArticleTitle = $x("//h2[contains(text(),'iPhone 14 не матиме слоту для SIM-карти в США')]");

    public String getTitleSearchResultPage() {
        return firstArticleTitle.getText();
    }
}
