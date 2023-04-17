package page_object;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderPageAboutRent {
    private final WebDriver driver;

    //second step
    private static final By titleSecondStep = By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Про аренду']");
    private static final By whereTakeScooterField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private static final By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder' and text()='* Срок аренды']");
    private static final By rentalPeriodListOpen = By.xpath(".//div[@class='Dropdown-root is-open']");
    private static final By colorScooterField = By.xpath(".//div[@class='Dropdown-placeholder' and text()='* Срок аренды']");
    private static final By colorScooterChosen = By.xpath(".//div[@class='Order_Checkboxes__3lWSI Order_FilledContainer__2MKAk']");
    private static final By blackScooterColor = By.id("black");
    private static final By greyScooterColor = By.id("grey");
    private static final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    private static final By orderScooterButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    private static final By agreeFormTitle = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']");
    private static final By agreeButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    private static final By formedOrderTitle = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    public OrderPageAboutRent(WebDriver driver) {
        this.driver = driver;
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

    public void sendKeysEnter(By locator) {
        driver.findElement(locator).sendKeys(Keys.ENTER);
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

    public void selectColorInCheckbox(String colorScooter) {
        if ( colorScooter.equals("two") ) {
            driver.findElement(blackScooterColor).click();
            driver.findElement(greyScooterColor).click();
        } else if (colorScooter.equals("чёрный жемчуг")) {
            driver.findElement(blackScooterColor).click();
        } else {
            driver.findElement(greyScooterColor).click();
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


    public void fillingFormAboutRent(String dateOrder, String rentalPeriod, String colorScooter, String comment) {
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
        if (!colorScooter.equals("")) {
            selectColorInCheckbox(colorScooter);
            waitPresentOfElement(colorScooterChosen);
        }
        // Comment
        if (!comment.equals("")) {
            setField(commentField, comment);
        }
    }
}
