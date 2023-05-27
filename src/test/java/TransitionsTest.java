import API.User.User;
import API.User.UserClient;
import API.User.UserGenerator;
import PageObject.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertTrue;

public class TransitionsTest {
    private WebDriver driver;
    private UserClient userClient;
    private User user;
    private String accessToken;

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
        userClient = new UserClient();
        user = UserGenerator.getRandomUser();
        accessToken = userClient.createUser(user).extract().path("accessToken");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPageOpen();
        loginPage.loginUser(user);
    }

    @Test
    @DisplayName("Пользователь может перейти на страницу личного кабинета")
    public void userCanBeTransitFromMainPageToProfile() {
        MainPage mainPage = new MainPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.clickAccountButton();
        assertTrue(profilePage.isProfilePageOpen());
    }

    @Test
    @DisplayName("Пользователь может перейти на главную страницу из личного кабинета")
    @Description("Открывается главная страница при клике на 'Конструктор' в личном кабинете")
    public void userCanBeTransitFromProfileToConstructor() {
        MainPage mainPage = new MainPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.clickAccountButton();
        profilePage.clickConstructorButton();
        assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Пользователь может перейти на главную страницу из личного кабинета")
    @Description("Открывается главная страница при клике на 'Конструктор' в личном кабинете")
    public void userCanBeTransitFromProfileToLogo() {
        MainPage mainPage = new MainPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        mainPage.clickAccountButton();
        profilePage.clickHeaderLogo();
        assertTrue(mainPage.isMainPageOpen());
    }

    @After
    public void tearDown() {
        driver.quit();
        userClient.deleteUser(accessToken);
    }
}
