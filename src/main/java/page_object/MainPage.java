package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    protected static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    public static By orderButtonHead = By.xpath(".//button[@class='Button_Button__ra12g']");
    public static By orderButtonBlock = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM')]");
    private static By importantQuestions = By.xpath(".//div[text()='Вопросы о важном']");

    private By questionsText(String question) {
        String questionLocatorText = ".//div[@class='accordion__button'][contains(.,'"+ question +"')]";

        return By.xpath(questionLocatorText);
    }

    private By questionsTextAccordion(String question) {
        String accordionLocatorText = ".//div[@class='accordion__button'][contains(.,'"+question+"') and @aria-disabled='true']";

        return By.xpath(accordionLocatorText);
    }

    private By answerText(String answer) {
        String answerLocatorText = ".//div[@class='accordion__panel'][contains(.,'"+answer+"')][not (@hidden)]";

        return By.xpath(answerLocatorText);
    }

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // прокрутка до блока с Важными вопросами
    public void scrollToSectionImportantQuestions() {
        WebElement element = driver.findElement(importantQuestions );//текст вопросы о важном
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToTheButtonToOrderInBlock(By orderButton) {
        WebElement element = driver.findElement(orderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //ожидание появления элементов с вопросами
    public void waitClickable(By locator) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void waitPresentOfElement(By locator) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // открытие вопросов
    public void clickQuestion(By locator){
        driver.findElement(locator).click();
    }

    //получить текст ответа на вопрос
    public String getAnswerText(String answer){
        By answerLocator = answerText(answer);

        return driver.findElement(answerLocator).getText();
    }

    //кликнуть на кнопу "Заказать" в Заголовке
    public void clickCreateOrderButton(By orderButton) {
        driver.findElement(orderButton).click();
    }

    public void checkAnswer(String question, String answer) {
        By questionLocator = questionsText(question);
        By accordionLocator = questionsTextAccordion(question);
        By answerLocator = answerText(answer);

        scrollToSectionImportantQuestions();
        waitClickable(questionLocator);
        clickQuestion(questionLocator);
        waitPresentOfElement(accordionLocator);
        waitPresentOfElement(answerLocator);
    }

}
