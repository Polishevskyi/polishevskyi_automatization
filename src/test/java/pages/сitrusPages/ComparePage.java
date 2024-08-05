package pages.сitrusPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ComparePage {
    private final SelenideElement compareTitle = $x("//div[@class='ComparePage_blank__6j0o1']//div[1]");

    public String getTitleCompareBlock() {
        return compareTitle.getText();
    }
}
