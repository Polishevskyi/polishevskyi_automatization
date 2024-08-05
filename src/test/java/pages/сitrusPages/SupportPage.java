package pages.сitrusPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SupportPage {
    private final SelenideElement supportTitle = $x("//div[@data-anim='false']//p[1]");

    public String getTitleSupportBlock() {
        return supportTitle.getText();
    }
}
