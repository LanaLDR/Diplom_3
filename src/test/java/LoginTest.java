import API.User.User;
import API.User.UserClient;
import API.User.UserGenerator;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RecoverPasswordPage;
import PageObject.RegistrationPage;
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

public class LoginTest {
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
    @DisplayName("Пользователь может авторизоваться начиная с главной страницы через кнопку логина")
    public void userCanBeLoginFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.mainPageOpen();
        mainPage.clickLoginButton();
        loginPage.loginUser(user);
        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Пользователь может авторизоваться начиная с главной страницы через личный кабинет")
    public void userCanBeLoginFromAccountButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.mainPageOpen();
        mainPage.clickAccountButton();
        loginPage.loginUser(user);
        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Пользователь может авторизоваться начиная со страницы регистрации")
    public void userCanBeLoginFromRegisterPage() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        registrationPage.registrationPageOpen();
        registrationPage.clickLoginButton();
        loginPage.loginUser(user);
        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Пользователь может авторизоваться начиная со страницы восстановления пароля")
    public void userCanBeLogindFromRecoverPasswordPage() {
        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = new MainPage(driver);
        recoverPasswordPage.recoverPasswordPageOpen();
        recoverPasswordPage.clickLoginButton();
        loginPage.loginUser(user);
        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
        userClient.deleteUser(accessToken);
    }
}
