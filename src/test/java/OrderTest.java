import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import page_object.BaseTest;
import page_object.MainPage;
import page_object.OrderPageAboutRent;
import page_object.OrderPageForWhom;

import static org.junit.Assert.assertEquals;
import static page_object.MainPage.orderButtonBlock;
import static page_object.MainPage.orderButtonHead;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    private final By orderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;

    private final String dateOrder;
    private final String rentalPeriod;
    private final String colorScooter;
    private final String comment;
    private final Boolean expected;


    public OrderTest(By orderButton, String name, String surname, String address, String metroStation, String phoneNumber, String dateOrder, String rentalPeriod, String colorScooter, String comment, Boolean expected) {
        this.orderButton = orderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;

        this.dateOrder = dateOrder;
        this.rentalPeriod = rentalPeriod;
        this.colorScooter = colorScooter;
        this.comment = comment;

        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        //Сгенерируй тестовые данные
        return new Object[][] {
                { orderButtonHead, "Иван", "Иванов", "ул Паркова д 1", "Кутузовская", "81234567891", "2023-05-25", "четверо суток", "чёрный жемчуг", "", true },
                { orderButtonHead, "Мария", "Петрова", "Московская ул, д 10", "Измайлово", "+79999999999", "2023-05-01", "сутки", "серая безысходность", "Позвонить за час до доставки", true },
                { orderButtonHead, "Александр", "Александров", "ул Космонавтов, д 5", "Преображенская площадь", "+79999999999", "2023-05-20", "семеро суток", "", "", true },
                { orderButtonHead, "Марина", "Морская", "ул Морская, д 15", "Кожуховская", "+79999999999", "2023-05-28", "двое суток", "two", "Офисное здание", true },
                { orderButtonBlock, "Иван", "Иванов", "ул Паркова д 1", "Кутузовская", "81234567891", "2023-05-25", "четверо суток", "чёрный жемчуг", "", true },
                { orderButtonBlock, "Мария", "Петрова", "Московская ул, д 10", "Измайлово", "+79999999999", "2023-05-01", "сутки", "серая безысходность", "Позвонить за час до доставки", true },
                { orderButtonBlock, "Александр", "Александров", "ул Космонавтов, д 5", "Преображенская площадь", "+79999999999", "2023-05-20", "семеро суток", "", "", true },
                { orderButtonBlock, "Марина", "Морская", "ул Морская, д 15", "Кожуховская", "+79999999999", "2023-05-28", "двое суток", "two", "Офисное здание", true },
        };
    }

    @Test
    public void checkCreateOrder() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.scrollToTheButtonToOrderInBlock(orderButton);
        objMainPage.clickCreateOrderButton(orderButton);

        OrderPageForWhom objOrderPageFromWhom = new OrderPageForWhom(driver);
        objOrderPageFromWhom.fillingFormForWhom(name, surname, address, metroStation, phoneNumber);

        OrderPageAboutRent objOrderPageAboutRent = new OrderPageAboutRent(driver);
        objOrderPageAboutRent.fillingFormAboutRent(dateOrder, rentalPeriod, colorScooter, comment);
        objOrderPageAboutRent.clickCreateOrderButton();
        objOrderPageAboutRent.agreeWindow();
        Boolean result = objOrderPageAboutRent.windowFormedOrder();

        assertEquals(expected, result);
    }
}
