package pages.сitrusPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ApplePage {
    private final SelenideElement appleBlock = $x("//span[text()='iPhone']");

    public String getTitleAppleBlock() {
        return appleBlock.getText();
    }
}
