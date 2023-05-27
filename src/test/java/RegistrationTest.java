import API.User.User;
import API.User.UserClient;
import API.User.UserGenerator;
import PageObject.LoginPage;
import PageObject.MainPage;
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


public class RegistrationTest {
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
    }

    @Test
    @DisplayName("Пользователь может зарегистрироваться начиная с главной страницы")
    public void userCanBeRegisteredFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.mainPageOpen();
        mainPage.clickLoginButton();
        loginPage.clickRegistrationLinkButton();
        registrationPage.registrationUser(user);
        loginPage.loginUser(user);
        assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Пользователь может зарегистрироваться начиная со страницы логина")
    public void userCanBeRegisteredFromLoginPage() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPageOpen();
        loginPage.clickRegistrationLinkButton();
        registrationPage.registrationUser(user);
        loginPage.loginUser(user);
        assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Пользователь может зарегистрироваться начиная со страницы регистрации")
    public void userCanBeRegisteredFromRegisterPage() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        registrationPage.registrationPageOpen();
        registrationPage.registrationUser(user);
        loginPage.loginUser(user);
        assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Пользователь не может зарегистрироваться начиная с коротким паролем")
    public void userCantBeRegisteredWithWrongPassword() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationPageOpen();
        user = UserGenerator.getRandomUserWithWrongPassword();
        registrationPage.registrationUser(user);
        assertTrue(registrationPage.isIncorrectPasswordDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
        accessToken = userClient.loginUser(user).extract().path("accessToken");
        if (accessToken != null) userClient.deleteUser(accessToken);
    }
}
