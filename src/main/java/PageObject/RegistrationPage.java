package PageObject;

import API.User.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegistrationPage {
    private WebDriver driver;
    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }

    public static final String REGISTRATION_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    private final By nameFiledInput = By.xpath(".//label[text() = 'Имя']/../input[@name = 'name']"); // поле ввода Имени
    private final By emailFieldInput = By.xpath(".//label[text() = 'Email']/../input[@name = 'name']"); // поле ввода email
    private final By passwordFieldInput = By.xpath(".//input[@name = 'Пароль']"); // поле ввода пароля
    private final By registrationButton = By.xpath(".//button[text() = 'Зарегистрироваться']"); // кнопка регистрации
    private final By incorrectPasswordText = By.xpath(".//p[text() = 'Некорректный пароль']");
    private final By loginLinkButton = By.xpath(".//a[contains(@href, '/login') and text() = 'Войти']"); //Ссылка на вход

    @Step("Открытие страницы регистрации")
    public void registrationPageOpen() {
        driver.get(REGISTRATION_PAGE_URL);
    }

    @Step("Заполнить поле имя")
    public void setNameFiled(String name) {
        driver.findElement(nameFiledInput).click();
        driver.findElement(nameFiledInput).sendKeys(name);
    }

    @Step("Заполнить поле email")
    public void setEmailField(String email) {
        driver.findElement(emailFieldInput).click();
        driver.findElement(emailFieldInput).sendKeys(email);
    }

    @Step("Заполнить поле пароль")
    public void setPasswordField(String password) {
        driver.findElement(passwordFieldInput).click();
        driver.findElement(passwordFieldInput).sendKeys(password);
    }

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginLinkButton).click();
    }

    @Step("Сообщение о некорректном пароле отображается")
    public boolean isIncorrectPasswordDisplayed() {
       return driver.findElement(incorrectPasswordText).isDisplayed();
    }

    @Step("Полная регистрация пользователя")
    public void registrationUser(User user) {
        setNameFiled(user.getName());
        setEmailField(user.getEmail());
        setPasswordField(user.getPassword());
        clickRegistrationButton();
    }
}
