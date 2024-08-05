package pages.сitrusPages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final SelenideElement appleTab = $x("//img[@alt='Apple']");
    private final SelenideElement supportTab = $x("//a[@href='/support/']");
    private final SelenideElement compareTab = $x("//a[@href='/compare/']//button[1]");

    public MainPage(String url) {
        Selenide.open(url);
    }

    public ApplePage clickOnAppleTab() {
        appleTab.click();
        return new ApplePage();
    }

    public SupportPage clickOnSupportTab() {
        supportTab.click();
        return new SupportPage();
    }

    public ComparePage clickOnCompareTab() {
        compareTab.click();
        return new ComparePage();
    }
}
