package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;


public class MainPage {
    public static final long SHORT_WAIT = 3;
    private WebDriver driver;
    // Мапа для определения кнопки Заказать
    private HashMap<String, By> buttonsOrder = new HashMap<>();
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    //Кнопка для подтверждения cookie
    private static final By BUTTON_COOKIE = By.id("rcc-confirm-button");
    //кнопка Заказать в заголовке
    private static final By ORDER_BUTTON_HEADER = By.xpath(".//div[@class='Header_Nav__AGCXC']//button[@class='Button_Button__ra12g']");
    // кнопка Заказать в середине страницы
    private static final By ORDER_BUTTON_BODY = By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button");
    //Блок Вопросы о важном
    private static final By QUESTIONS_MAJOR = By.className("Home_FAQ__3uVm4");
    // Элемент с текстом вопроса
    private static final String QUESTION = ".//div[@class=\"accordion__button\" and text()='%s']";
    // Элемент с текстом ответа
    private static final String ANSWER = ".//div[@class=\"accordion__button\" and text()='%s']/parent::div/parent::div/div[@class=\"accordion__panel\"]/p";;

    public MainPage(WebDriver driver){
        this.driver = driver;
        this.buttonsOrder.put("Кнопка в заголовке", ORDER_BUTTON_HEADER);
        this.buttonsOrder.put("Кнопка на странице", ORDER_BUTTON_BODY);
    }
    public MainPage open(){
        driver.get(PAGE_URL);
        return this;
    }

    //Клик на кнопку для подтверждения cookie
    public void clickOnConfirmCookie(){
        if (driver.findElement(BUTTON_COOKIE).isDisplayed()){
            driver.findElement(BUTTON_COOKIE).click();
        }
    }
    //Клик на кнопку "Заказать"
    public void clickOnOrderButton (String button){
        WebElement element = driver.findElement(buttonsOrder.get(button));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(SHORT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonsOrder.get(button)));
        element.click();
    };
    //Скролл до блока с вопросами
    public void scrollToQuestions(){
        WebElement element = driver.findElement(QUESTIONS_MAJOR);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // Метод получения ответа на вопрос
    public String getAnswer(String question){
        this.scrollToQuestions();
        driver.findElement(By.xpath(String.format(QUESTION, question))).click();
        WebElement answer = new WebDriverWait(driver, Duration.ofSeconds(SHORT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(ANSWER, question))));
        return answer.getText();
    }
}