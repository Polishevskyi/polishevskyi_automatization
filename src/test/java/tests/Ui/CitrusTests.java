package tests.Ui;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import lib.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.сitrusPages.MainPage;

public class CitrusTests extends BaseTest {

    private final static String BASE_URL = "https://www.ctrs.com.ua/";
    private final static String IPHONE_TITLE = "iPhone";
    private final static String SUPPORT_TITLE = "Любий Клієнте!";
    private final static String COMPARE_TITLE = "Порівняння не заповнено";

    @Test
    @Owner("Polishevskyi")
    @Description("Test verify iphone block into Apple tab")
    public void verifyTitleAfterSearch() {
        Assert.assertTrue(new MainPage(BASE_URL)
                .clickOnAppleTab()
                .getTitleAppleBlock()
                .contains(IPHONE_TITLE));
    }

    @Test
    @Owner("Polishevskyi")
    @Description("Test verify Support block")
    public void verifySupportTab() {
        Assert.assertTrue(new MainPage(BASE_URL)
                .clickOnSupportTab()
                .getTitleSupportBlock()
                .contains(SUPPORT_TITLE));
    }

    @Test
    @Owner("Polishevskyi")
    @Description("Test verify Compare block")
    public void verifyCompareTab() {
        Assert.assertTrue(new MainPage(BASE_URL)
                .clickOnCompareTab()
                .getTitleCompareBlock()
                .contains(COMPARE_TITLE));
    }
}
