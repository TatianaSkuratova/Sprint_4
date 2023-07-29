import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.MainPage;

import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestQuestionsChrome {
    private  WebDriver driver;
    private String question;
    private String answer;
    public TestQuestionsChrome(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    @Before
    public void before(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setBinary(new File("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"));
        driver = new ChromeDriver(chromeOptions);
    }
    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
               // { "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                //{ "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }
    @After
    public void after(){
        driver.quit();
    }
    @Test
    public void checkAnswer(){
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOnConfirmCookie();
        String actualText = mainPage.getAnswer(question);
        String msgError = "Текст ответа для вопроса: '"+ question + "' не совпадает с ожидаемым результатом!\n Текущий текст: " + actualText + "\n Проверяемый текст: " + answer;
        assertEquals(msgError,actualText ,answer);
    }
}
