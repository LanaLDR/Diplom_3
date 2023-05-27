package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private WebDriver driver;
    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }

    public static final String PROFILE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    private final By constructorButton = By.xpath(".//p[text() = 'Конструктор']"); // кнопка открытия конструктора
    private final By headerLogo = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']"); // Логотип в хедере
    private final By logoutButton = By.xpath(".//button[text() = 'Выход']"); // кнопка выхода из аккаунта

    @Step("Открытие страницы личного кабинета")
    public void profilePageOpen() {
        driver.get(PROFILE_URL);
    }

    @Step("Клик на кнопку 'Выход'")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("Клик на кнопку на лаготип в хедере")
    public void clickHeaderLogo() {
        driver.findElement(headerLogo).click();
    }

    @Step("Клик на кнопку 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Проверка открытия личного кабинета")
    public boolean isProfilePageOpen() {
        return driver.findElement(logoutButton).isDisplayed();
    }

}
