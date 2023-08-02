import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_object.MainPage;

import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class
TestQuestions {
    private  WebDriver driver;
    private String question;
    private String answer;
    public TestQuestions(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    @Before
    public void before(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setBinary(new File("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"));
        driver = new ChromeDriver(chromeOptions);
        //driver = new FirefoxDriver();
    }
    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }
    @After
    public void after(){
        driver.quit();
    }
    //Проверка вопросов о важном в Chrome
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
