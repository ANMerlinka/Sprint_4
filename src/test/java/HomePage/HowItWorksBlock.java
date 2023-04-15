package HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class HowItWorksBlock {
    private WebDriver driver;
    private String name;
    private String surname;
    private String address;
    private String metroStation;
    private String phoneNumber;

    private String dateOrder;
    private String rentalPeriod;
    private String colorScooter;
    private String comment;

    private String expected;

    @Before
    public void setUp() {
        // драйвер для браузера Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public HowItWorksBlock(String name, String surname, String address, String metroStation, String phoneNumber, String dateOrder, String rentalPeriod, String colorScooter, String comment, String expected) {
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
                { "Иван", "Иванов", "ул Паркова д 1", "Кутузовская", "81234567891", "2023-05-25", "четверо суток", "чёрный жемчуг", "", "true" },
                { "Мария", "Петрова", "Московская ул, д 10", "Измайлово", "+79999999999", "2023-05-01", "сутки", "серая безысходность", "Позвонить за час до доставки", "true" },
                { "Александр", "Александров", "ул Космонавтов, д 5", "Преображенская площадь", "+79999999999", "2023-05-20", "семеро суток", "", "", "true" },
                { "Марина", "Морская", "ул Морская, д 15", "Кожуховская", "+79999999999", "2023-05-28", "двое суток", "two", "Офисное здание", "true" },
        };
    }

    class creatingOrderFromBlock {
        private WebDriver driver;

        private String name;
        private String surname;
        private String address;
        private String metroStation;
        private String phoneNumber;

        private String dateOrder;
        private String rentalPeriod;
        private String colorScooter;
        private String comment;

        public By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
        public By pageFirstStepOrder = By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Для кого самокат']");
        //first step
        public By nameField = By.xpath(".//input[@placeholder='* Имя']");
        public By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
        public By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
        public By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
        public By metroStationList = By.xpath(".//div[@class='select-search has-focus']");
        public By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

        public By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");

        //second step
        public By titleSecondStep = By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Про аренду']");
        public By whereTakeScooterField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
        public By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder' and text()='* Срок аренды']");
        public By rentalPeriodListOpen = By.xpath(".//div[@class='Dropdown-root is-open']");
        public By colorScooterField = By.xpath(".//div[@class='Dropdown-placeholder' and text()='* Срок аренды']");
        public By colorScooterChosen = By.xpath(".//div[@class='Order_Checkboxes__3lWSI Order_FilledContainer__2MKAk']");
        public By blackScooterColor = By.id("black");
        public By greyScooterColor = By.id("grey");
        public By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

        public By orderScooterButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

        public By agreeFormTitle = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']");
        public By agreeButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

        public By formedOrderTitle = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

        public creatingOrderFromBlock(WebDriver driver, String name, String surname, String address, String metroStation, String phoneNumber, String dateOrder, String rentalPeriod, String colorScooter, String comment) {
            this.driver = driver;

            this.name = name;
            this.surname = surname;
            this.address = address;
            this.metroStation = metroStation;
            this.phoneNumber = phoneNumber;

            this.dateOrder = dateOrder;
            this.rentalPeriod = rentalPeriod;
            this.colorScooter = colorScooter;
            this.comment = comment;
        }

        public void scrollToOrderButton(By locator) {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        }

        public void waitClickable(By locator) {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.elementToBeClickable(locator));
        }

        public void waitPresentOfElement(By locator) {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
        }

        public void clickButtonOrField(By locator) {
            driver.findElement(locator).click();
        }

        public void setField(By locator, String value) {
            driver.findElement(locator).sendKeys(value);
        }

        public void selectElementFromList(String text, String tagName) {
            List<WebElement> options = driver.findElements(By.tagName(tagName));
            for (WebElement option : options) {
                if (option.getText().equals(text)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", option);
                    option.click();
                    break;
                }
            }
        }

        public void sendKeysEnter(By locator) {
            driver.findElement(locator).sendKeys(Keys.ENTER);
        }

        public void selectColorInCheckbox(String colorScooter) {
            if ((colorScooter != "чёрный жемчуг") && (colorScooter != "серая безысходность") && (colorScooter != "two")) {
                return;
            } else if (colorScooter == "чёрный жемчуг") {
                driver.findElement(blackScooterColor).click();
            } else if (colorScooter == "серая безысходность") {
                driver.findElement(greyScooterColor).click();
            } else {
                driver.findElement(blackScooterColor).click();
                driver.findElement(greyScooterColor).click();
            }
        }


        public void fillingFormFirstStep(String name, String surname, String address, String metroStation, String phoneNumber) {
            // подождали доступность кноки Заказа
            scrollToOrderButton(orderButton);
            waitClickable(orderButton);
            // нажали на Заказ
            clickButtonOrField(orderButton);
            // Заполнение первой части формы
            waitPresentOfElement(pageFirstStepOrder);
            // Name
            clickButtonOrField(nameField);
            setField(nameField, name);
            // Surname
            clickButtonOrField(surnameField);
            setField(surnameField, surname);
            // Address
            clickButtonOrField(addressField);
            setField(addressField, address);
            // metroStation
            clickButtonOrField(metroStationField);
            waitClickable(metroStationList);
            selectElementFromList(metroStation, "li");
            // phoneNumber
            clickButtonOrField(phoneNumberField);
            setField(phoneNumberField, phoneNumber);
        }

        public void movingToNextStep() {
            // Далее
            clickButtonOrField(nextButton);
        }

        public void fillingFormSecondStep(String dateOrder, String rentalPeriod, String colorScooter, String comment) {
            waitPresentOfElement(titleSecondStep);
            // Date
            clickButtonOrField(whereTakeScooterField);
            setField(whereTakeScooterField, dateOrder);
            sendKeysEnter(whereTakeScooterField);
            // Rental period
            clickButtonOrField(rentalPeriodField);
            waitPresentOfElement(rentalPeriodListOpen);
            selectElementFromList(rentalPeriod, "div");
            // Color
            if (colorScooter != "") {
                selectColorInCheckbox(colorScooter);
                waitPresentOfElement(colorScooterChosen);
            }
            // Comment
            if (comment != "") {
                setField(commentField, comment);
            }

        }

        public void clickCreateOrderButton() {
            driver.findElement(orderScooterButton).click();
        }

        public void agreeWindow() {
            waitPresentOfElement(agreeFormTitle);
            clickButtonOrField(agreeButton);
        }

        public boolean isElementPresent(By locator) {
            boolean isPresent = true;
            try {
                driver.findElement(locator);
            } catch (NoSuchElementException e) {
                isPresent = false;
            }
            return isPresent;
        }

        public Boolean windowFormedOrder() {
            return isElementPresent(formedOrderTitle);
        }

    }


    @Test
    public void creationOrderFromBlock() {
        creatingOrderFromBlock objCreationOrderFromBlock = new creatingOrderFromBlock(driver, name, surname, address, metroStation, phoneNumber, dateOrder, rentalPeriod, colorScooter, comment);

        objCreationOrderFromBlock.fillingFormFirstStep(name, surname, address, metroStation, phoneNumber);
        objCreationOrderFromBlock.movingToNextStep();

        objCreationOrderFromBlock.fillingFormSecondStep(dateOrder, rentalPeriod, colorScooter, comment);
        objCreationOrderFromBlock.clickCreateOrderButton();
        objCreationOrderFromBlock.agreeWindow();

        Boolean result = objCreationOrderFromBlock.windowFormedOrder();

        assertEquals(expected, result);
    }


}
