package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage {
    private WebDriver driver;
    public RecoverPasswordPage(WebDriver driver){
        this.driver = driver;
    }

    public static final String RECOVER_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    private final By nameFiledInput = By.xpath(".//label[text() = 'Имя']/../input[@name = 'name']"); // поле ввода Имени
    private final By emailFieldInput = By.xpath(".//label[text() = 'Email']/../input[@name = 'name']"); // поле ввода email
    private final By passwordFieldInput = By.xpath(".//input[@name = 'Пароль']"); // поле ввода пароля
    private final By registrationButton = By.xpath(".//button[text() = 'Зарегистрироваться']"); // кнопка регистрации
    private final By incorrectPasswordText = By.xpath(".//p[text() = 'Некорректный пароль']");
    private final By loginLinkButton = By.xpath(".//a[contains(@href, '/login') and text() = 'Войти']"); //Ссылка на вход

    @Step("Открытие страницы восстановления пароля")
    public void recoverPasswordPageOpen() {
        driver.get(RECOVER_PASSWORD_URL);
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginLinkButton).click();
    }
}
