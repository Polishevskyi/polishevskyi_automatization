package pages.ainPages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final SelenideElement searchField = $x("(//*[name()='rect'])[2]");
    private final SelenideElement technologyBtn = $x("//a[@rel='dofollow'][contains(text(),'Технології')]");
    private final SelenideElement firstTopByViews = $x("//div[@class='side-widget side-widget--top-news']//li[1]//a");

    public MainPage(String url) {
        Selenide.open(url);
    }

    public SearchPage clickOnSearchBtn() {
        searchField.click();
        return new SearchPage();
    }

    public TechnologyTabPage clickOnTechnologyTab() {
        technologyBtn.click();
        return new TechnologyTabPage();
    }

    public TopByViewsPage clickOnFirstTopByViewsArticle() {
        firstTopByViews.click();
        return new TopByViewsPage();
    }

    public String getTitleTopByViewsMainPage() {
        return firstTopByViews.getText();
    }
}
