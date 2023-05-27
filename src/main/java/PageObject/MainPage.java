package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";
    private final By loginButton = By.xpath(".//button[text() = 'Войти в аккаунт']"); // Кнопка логина
    private final By createOrderButton = By.xpath(".//button[text() = 'Оформить заказ']"); // Кнопка оформления заказа
    private final By personalAccountButton = By.xpath(".//p[text() = 'Личный Кабинет']"); // Кнопка личного кабинета
    private final By mainPageMainTitle = By.xpath(".//h1[text() = 'Соберите бургер']"); // Загаловок 'Соберите бургер'
    private final By currentConstructorType = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]"); // локатор выбранного типа
    private final By constructorTypeBun = By.xpath(".//span[text() = 'Булки']"); // локатор типа "Булки"
    private final By constructorTypeSauces = By.xpath(".//span[text() = 'Соусы']"); // локатор типа "Соусы"
    private final By constructorTypeFillings = By.xpath(".//span[text() = 'Начинки']"); // локатор типа "Начинки"



    @Step("Открытие главной страницы")
    public void mainPageOpen() {
        driver.get(MAIN_URL);
    }

    @Step("Проверка что кнопка офрмления заказа отображается")
    public boolean isCreateOrderButtonDisplayed() {
        return driver.findElement(createOrderButton).isDisplayed();
    }

    @Step("Клик на кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Проверка что главная страница открылась")
    public boolean isMainPageOpen() {
        return driver.findElement(mainPageMainTitle).isDisplayed();
    }

    @Step("Клик на кнопку 'Личный кабинет'")
    public void clickAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Получение текста выбранного типа конструктора")
    public String getCurrentConstructorTypeText() {
        return driver.findElement(currentConstructorType).getText();
    }

    @Step("Выбор типа конструктора 'Булочки'")
    public void clickConstructorTypeBun() {
        driver.findElement(constructorTypeBun).click();
    }

    @Step("Выбор типа конструктора 'Соусы'")
    public void clickConstructorTypeSauces() {
        driver.findElement(constructorTypeSauces).click();
    }

    @Step("Выбор типа конструктора 'Начинки'")
    public void clickConstructorTypeFillings() {
        driver.findElement(constructorTypeFillings).click();
    }
}
