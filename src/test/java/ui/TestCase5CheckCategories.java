package ui;

import framework.MainPage;
import framework.MainPage.SortEnum;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

@Log4j2
public class TestCase5CheckCategories extends BaseTest {

  private final MainPage mainPage = new MainPage();

  @Test
//Check categories
  public void checkNameNearCartButton() {
    SoftAssertions softAssertions = new SoftAssertions();

    //Check that 'MEN' and 'WOMEN' sub menu items appears in "CLOTHES" menu
    log.info("Check that 'MEN' and 'WOMEN' sub menu items appears in CLOTHES menu");
    List<String> expectedClotheList = new ArrayList<>();
    expectedClotheList.add("MEN");
    expectedClotheList.add("WOMEN");

    List<String> actualClotheList = mainPage.getCategoriesList(SortEnum.Clothes);

    softAssertions.assertThat(actualClotheList)
        .as("EXPECTED" + expectedClotheList)
        .isEqualTo(expectedClotheList);

//Check that 'STATIONERY' and 'HOME ACCESSORIES' sub menu items appears in "ACCESSORIES" menu
    log.info("Check that 'STATIONERY' and 'HOME ACCESSORIES' sub menu items appears in "
        + "ACCESSORIES menu");
    List<String> expectedAccessoriesList = new ArrayList<>();
    expectedAccessoriesList.add("STATIONERY");
    expectedAccessoriesList.add("HOME ACCESSORIES");
    List<String> actualAccessoriesList = mainPage.getCategoriesList(SortEnum.Accessor);
    softAssertions.assertThat(actualAccessoriesList)
        .as("EXPECTED" + expectedClotheList)
        .isEqualTo(expectedAccessoriesList);

//Check nothing appears in "ART" menu
    log.info("Check nothing appears in ART menu");
    List<String> actualArtList = mainPage.getCategoriesList(SortEnum.Art);

    softAssertions.assertThat(actualArtList)
        .as("EXPECTED empty fild")
        .isEmpty();

    softAssertions.assertAll();
  }
}
