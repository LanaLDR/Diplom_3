import API.User.User;
import API.User.UserClient;
import API.User.UserGenerator;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertTrue;

public class LogoutTest {
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
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете.")
    public void userCanBeLogout() {
        MainPage mainPage = new MainPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPageOpen();
        loginPage.loginUser(user);
        mainPage.clickAccountButton();
        profilePage.clickLogoutButton();
        assertTrue(loginPage.isLoginPageOpen());
    }

    @After
    public void tearDown() {
        driver.quit();
        userClient.deleteUser(accessToken);
    }
}
