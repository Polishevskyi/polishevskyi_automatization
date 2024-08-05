package pages.ainPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TechnologyTabPage {
    private final SelenideElement technologyTitle = $x("//ul[@class='breadcrumbs']/following-sibling::h1[1]");

    public String getTitleSearchResultPage() {
        return technologyTitle.getText();
    }
}
