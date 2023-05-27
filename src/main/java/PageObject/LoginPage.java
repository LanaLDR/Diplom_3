package PageObject;

import API.User.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    private WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    private final By emailFieldInput = By.xpath(".//label[text() = 'Email']/../input[@name = 'name']"); // поле ввода email
    private final By passwordFieldInput = By.xpath(".//input[@name = 'Пароль']"); // поле ввода пароля
    private final By loginButton = By.xpath(".//button[text() = 'Войти']"); //Кнопка входа
    private final By registrationLinkButton =
            By.xpath(".//a[contains(@href, '/register') and text() = 'Зарегистрироваться']"); //Ссылка на регистрацию
    private final By recoverPasswordLinkButton =
            By.xpath(".//a[contains(@href, '/forgot-password') and text() = 'Восстановить пароль']"); //Ссылка на восстановление пароля

    @Step("Открытие страницы логина")
    public void loginPageOpen() {
        driver.get(LOGIN_PAGE_URL);
    }
    @Step("Заполнение поля email")
    public void setEmailField(String email) {
        driver.findElement(emailFieldInput).click();
        driver.findElement(emailFieldInput).sendKeys(email);
    }

    @Step("Заполение поля пароль")
    public void setPasswordField(String password) {
        driver.findElement(passwordFieldInput).click();
        driver.findElement(passwordFieldInput).sendKeys(password);
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик на ссылку 'Зарегистрироваться'")
    public void clickRegistrationLinkButton() {
        driver.findElement(registrationLinkButton).click();
    }

    @Step("Польный логин пользователя")
    public void loginUser(User user) {
        new WebDriverWait(driver, 50)
                .until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        setEmailField(user.getEmail());
        setPasswordField(user.getPassword());
        clickLoginButton();
    }

    @Step("Проверка что открылась страница авторизации")
    public boolean isLoginPageOpen() {
        return driver.findElement(loginButton).isDisplayed();
    }

}
