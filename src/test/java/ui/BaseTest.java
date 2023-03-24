package ui;

import static framework.BrowserFactory.getBrowser;

import framework.BasePage;
import framework.BrowserFactory.Browsers;
import framework.MainPage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Log4j2
public class BaseTest {

  @BeforeMethod(alwaysRun = true)
  public synchronized void setUp() {

    final MainPage mainPage = new MainPage();

    //frame Locator
    final By frameLocator = By.id("framelive");

    int width = Integer.parseInt(System.getProperty("browser.width"));
    int height = Integer.parseInt(System.getProperty("browser.height"));
    String browser = System.getProperty("browser.type");

    log.info("Tests will run at {}x{}", width, height);

    WebDriver driver = getBrowser(Browsers.valueOf(browser));

    driver.get("https://demo.prestashop.com/");
    driver.manage().window().setSize(new Dimension(width, height));
    BasePage.setDriverThreadLocal(driver);
    mainPage.waitUntilHomePageLoad();
    driver.switchTo().frame("framelive");
  }

  @AfterMethod(alwaysRun = true)
  public void quite() {
    if (BasePage.getDriverThreadLocal() != null) {
      BasePage.getDriver().quit();
      BasePage.getDriverThreadLocal().remove();
    }
  }
}
