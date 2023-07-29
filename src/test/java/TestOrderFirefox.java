import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.MainPage;
import pageobject.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestOrderFirefox {
    private WebDriver driver;
    private final String button, name, lastName,location, metro, phone, day, rent, color, note;

    public TestOrderFirefox(String button, String name, String lastName, String location, String metro, String phone, String day, String rent, String color, String note) {
        this.button = button;
        this.name = name;
        this.lastName = lastName;
        this.location = location;
        this.metro = metro;
        this.phone = phone;
        this.day = day;
        this.rent = rent;
        this.color = color;
        this.note = note;
    }

    @Before
    public void before(){
        driver = new FirefoxDriver();
    }

    @After
    public void after(){
        driver.quit();
    }
    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {"Кнопка в заголовке", "Вася", "Иванов", "Москва", "Черкизовская", "79275445544", "23", "сутки", "серая безысходность", "комментарий"},
                {"Кнопка на странице", "Петя", "Петров", "Волгоград", "Достоевская", "79275445588", "1", "четверо суток", "чёрный жемчуг", "комментарий"},
        };
    }

    //Заказ самоката в Firefox
    @Test
    public void setOrder(){
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOnConfirmCookie();
        mainPage.clickOnOrderButton(button);
        OrderPage orderPage = new OrderPage(driver);
        assertTrue(orderPage.isVisibilityFormConfirm(name, lastName, location, metro, phone, day, rent, color, note));
    }
}
