package pages.ainPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TopByViewsPage {
    private final SelenideElement titleTopByViews = $x("//ul[@class='breadcrumbs']/following-sibling::h1[1]");

    public String getTitleTopByViewsPage() {
        return titleTopByViews.getText();
    }
}
