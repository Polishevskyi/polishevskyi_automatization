package web.ain;

import pages.ainPages.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;
import web.BaseUiTest;

public class AinTests extends BaseUiTest {

    private final static String BASE_URL = "https://ain.ua/";
    private final static String SEARCH_TEXT = "iPhone 14 не матиме слоту для SIM-карти в США";
    private final static String TECHNOLOGY_TITLE = "Технології";

    @Test
    @Owner("Polishevskyi")
    @Description("Test verify text after search")
    public void verifyTitleAfterSearch() {
        Assert.assertTrue(new MainPage(BASE_URL)
                .clickOnSearchBtn()
                .addTextToSearchField(SEARCH_TEXT)
                .clickOnSearchBtn()
                .getTitleSearchResultPage()
                .contains(SEARCH_TEXT));
    }

    @Test
    @Owner("Polishevskyi")
    @Description("Test verify title after opening Technology tab")
    public void verifyTechnologyTitle() {
        Assert.assertTrue(new MainPage(BASE_URL)
                .clickOnTechnologyTab()
                .getTitleSearchResultPage()
                .contains(TECHNOLOGY_TITLE));
    }

    @Test
    @Owner("Polishevskyi")
    @Description("Test verify title after opening first Top by Views article")
    public void verifyTopByViewsTitle() {
        Assert.assertEquals(new MainPage(BASE_URL)
                .clickOnFirstTopByViewsArticle()
                .getTitleTopByViewsPage(), new MainPage(BASE_URL)
                .getTitleTopByViewsMainPage()
                .trim().replaceFirst("^\\d\\s+", ""), "The titles don't match!");
    }
}
