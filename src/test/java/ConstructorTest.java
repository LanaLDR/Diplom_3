import PageObject.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class ConstructorTest {
    private WebDriver driver;

    @BeforeClass
    public static void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver"); Для запуска в яндекс браузере - раскоментировать
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.manage().timeouts().pageLoadTimeout(100, SECONDS);
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void constructorTypeBunWorked() {
        MainPage mainPage = new MainPage(driver);
        mainPage.mainPageOpen();
        mainPage.clickConstructorTypeFillings(); //Иначе по умолчению выбранные булочки не кликабельны :)
        mainPage.clickConstructorTypeBun();
        assertEquals("Булки", mainPage.getCurrentConstructorTypeText());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void constructorTypeSaucesWorked() {
        MainPage mainPage = new MainPage(driver);
        mainPage.mainPageOpen();
        mainPage.clickConstructorTypeSauces();
        assertEquals("Соусы", mainPage.getCurrentConstructorTypeText());
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void constructorTypeFillingsWorked() {
        MainPage mainPage = new MainPage(driver);
        mainPage.mainPageOpen();
        mainPage.clickConstructorTypeFillings();
        assertEquals("Начинки", mainPage.getCurrentConstructorTypeText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
