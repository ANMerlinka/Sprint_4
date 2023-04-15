package HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class ImportantQuestions {
    private WebDriver driver;
    private String question;
    private String answer;

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

    public ImportantQuestions(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }


    @Parameterized.Parameters
    public static Object[][] data() {
        //Сгенерируй тестовые данные
        return new Object[][] {
                { "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой." },
                { "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим." },
                { "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30." },
                { "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее." },
                { "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010." },
                { "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится." },
                { "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои." },
                { "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области." },
        };
    }

    class importantQuestionsSection {
        private WebDriver driver;
        private String question;
        private String answer;

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

        public importantQuestionsSection(WebDriver driver, String question, String answer){
            this.driver = driver;
            this.question = question;
            this.answer = answer;
        }

        public void scrollToSectionImportantQuestions() {
            WebElement element = driver.findElement(questionsText(question));
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

        public void clickQuestion(){
            driver.findElement(questionsText(question)).click();
        }

        public String getTextAnswer(){
            By answerLocator = answerText(answer);

            return driver.findElement(answerLocator).getText();
        }


        public void checkAnswer(String question, String answer) {
            By questionLocator = questionsText(question);
            By accordionLocator = questionsTextAccordion(question);
            By answerLocator = answerText(answer);

            scrollToSectionImportantQuestions();
            waitClickable(questionLocator);
            clickQuestion();
            waitPresentOfElement(accordionLocator);
            waitPresentOfElement(answerLocator);
        }
    }

    @Test
    public void checkImportantQuestions() {
        importantQuestionsSection objImportantQuestionsSection = new importantQuestionsSection(driver, question, answer);
        objImportantQuestionsSection.checkAnswer(question, answer);

        String text = objImportantQuestionsSection.getTextAnswer();

        MatcherAssert.assertThat( text, is(answer) );
    }

}
