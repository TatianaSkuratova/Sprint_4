package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    // Поле для ввода имени
    private static final By INPUT_FIRST_NAME = By.xpath("//input[@placeholder='* Имя']");
    // Поле для ввода фамилии
    private static final By INPUT_LAST_NAME = By.xpath("//input[@placeholder='* Фамилия']");
    // Поле для ввода адреса
    private static final By INPUT_LOCATION = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле для выбора станции метро
    private static final By INPUT_METRO = By.className("select-search__input");
    // Выпадающий список со станциями метро
    private static final By LIST_METRO = By.className("select-search__options");
    // Значение станции метро
    private static String VALUE_METRO = "//div[@class='Order_Text__2broi' and text()= '%s']";
    // Кнопка Далее
    private static final By BUTTON_PROCESS = By.xpath(".//div[@class='Order_NextButton__1_rCA']//button");
    // Поле для ввода телефона
    private static final By INPUT_PHONE = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Поле для выбора даты
    private static final By INPUT_DATE = By.className("react-datepicker__input-container");
    // Календарь
    private static final By INPUT_CALENDAR = By.className("react-datepicker__month-container");
    // Значение даты в календаре
    private static final String VALUE_DAY = ".//div[@class='react-datepicker__week']/div[text()='%s']";
    // Поле для выбора срока аренды
    private static final By INPUT_RENT = By.className("Dropdown-root");
    // Значение срока аренды
    private static final String VALUE_RENT = ".//div[@class='Dropdown-menu']/div[text()='%s']";
    // Поле для выбора цвета самоката
    private static final String VALUE_COLOR = ".//div[@class = 'Order_Checkboxes__3lWSI']//label[@class='Checkbox_Label__3wxSf' and text()='%s']//input";
    // Значение цвета самоката
    private static By INPUT_NOTE = By.xpath(".//div[@class='Input_InputContainer__3NykH']//input[@placeholder='Комментарий для курьера']");
    // Кнопка Заказать
    private static By BUTTON_SEND = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");
    //Модальное окно с подтверждением заказа
    private static final By FORM_CONFIRM = By.className("Order_ModalHeader__3FDaJ");
    //Кнопка "Да"
    private static By BUTTON_CONFIRM = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Да']");
    //Окно с подтверждением заказа
    private By FORM_ORDER_CONFIRM = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()= 'Заказ оформлен']");
    private static int SHORT_WAIT = 3;
    private static int LONG_WAIT = 7;

    public OrderPage(WebDriver driver) {this.driver = driver;}
    public void enterFirstName(String name) {
        WebElement inputField = new WebDriverWait(driver, Duration.ofSeconds(SHORT_WAIT)).until(ExpectedConditions.visibilityOfElementLocated(INPUT_FIRST_NAME));
        inputField.clear();
        inputField.sendKeys(name);
    }
    public void enterLastName(String lastName) {
        WebElement inputField = new WebDriverWait(driver, Duration.ofSeconds(SHORT_WAIT)).until(ExpectedConditions.visibilityOfElementLocated(INPUT_LAST_NAME));
        inputField.clear();
        inputField.sendKeys(lastName);
    }
    public void enterLocation(String location) {
        WebElement inputField = new WebDriverWait(driver, Duration.ofSeconds(SHORT_WAIT)).until(ExpectedConditions.visibilityOfElementLocated(INPUT_LOCATION));
        inputField.clear();
        inputField.sendKeys(location);
    }
    public void enterMetro(String metro) {
        driver.findElement(INPUT_METRO).click();
        new WebDriverWait(driver, Duration.ofSeconds(LONG_WAIT)).until(ExpectedConditions.visibilityOfElementLocated(LIST_METRO));
        WebElement element = driver.findElement(By.xpath(String.format(VALUE_METRO, metro)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    public void enterPhone(String phone) {
        driver.findElement(INPUT_PHONE).clear();
        driver.findElement(INPUT_PHONE).sendKeys(phone);
    }
    public void clickButtonProcess() {
        driver.findElement(BUTTON_PROCESS).click();}
    public void enterDate(String day) {
        driver.findElement(INPUT_DATE).click();
        new WebDriverWait(driver, Duration.ofSeconds(SHORT_WAIT)).until(ExpectedConditions.visibilityOfElementLocated(INPUT_CALENDAR));
        driver.findElement(By.xpath(String.format(VALUE_DAY, day))).click();
    }
    public void enterRent(String rent) {
        driver.findElement(INPUT_RENT).click();
        WebElement inputFieldRent = new WebDriverWait(driver, Duration.ofSeconds(SHORT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(VALUE_RENT, rent))));
        inputFieldRent.click();
    }
    public void enterColor(String color) {
        driver.findElement(By.xpath(String.format(VALUE_COLOR, color))).click();}
    public void enterNote(String note) {
        driver.findElement(INPUT_NOTE).sendKeys(note);
    }
    public void clickButtonSend(){
        driver.findElement(BUTTON_SEND).click();
    }
    public void clickButtonConfirm(){
        driver.findElement(BUTTON_CONFIRM).click();
    }
    public boolean isVisibilityFormConfirm(String name, String lastName, String location, String metro, String phone, String day, String rent, String color, String note){
        this.enterFirstName(name);
        this.enterLastName(lastName);
        this.enterLocation(location);
        this.enterMetro(metro);
        this.enterPhone(phone);
        this.clickButtonProcess();
        this.enterDate(day);
        this.enterRent(rent);
        this.enterColor(color);
        this.enterNote(note);
        this.clickButtonSend();
        this.clickButtonConfirm();
        new WebDriverWait(driver, Duration.ofSeconds(LONG_WAIT)).until(ExpectedConditions.visibilityOfElementLocated(FORM_ORDER_CONFIRM));
        return driver.findElement(FORM_ORDER_CONFIRM).isDisplayed();
    }
}