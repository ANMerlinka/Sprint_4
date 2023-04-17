package page_object;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderPageForWhom {
    private final WebDriver driver;

    private static final By pageFirstStepOrder = By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Для кого самокат']");
    //first step
    private static final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private static final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private static final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private static final By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    private static final By metroStationList = By.xpath(".//div[@class='select-search has-focus']");
    private static final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    private static final By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");


    public OrderPageForWhom(WebDriver driver) {
        this.driver = driver;
    }

    public void waitPresentOfElement(By locator) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void waitClickable(By locator) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(locator));
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


    public void fillingFormForWhom(String name, String surname, String address, String metroStation, String phoneNumber) {
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
        //Next
        clickButtonOrField(nextButton);
    }
}
