package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;

/**
 * Общие элементы и действия для всех страниц
 */
public class PageBase<T> {
    private WebDriver driver;
    private Class clazz;
    @FindBy(name = "query")
    private WebElement searchInput;
    @FindBy(css = "input[title='Искать']")
    private WebElement searchSubmitButton;

    @FindBy(className = "ic__set__multicartWhite")
    private WebElement cardButton;

    public PageBase(WebDriver driver, Class clazz) {
        this.driver = driver;
        this.clazz = clazz;
        PageFactory.initElements(driver, this);
    }

    /*Утилиты начало***/
    public static void editTextNoEnter(WebElement input, String text) {
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.DELETE);
        input.sendKeys(text);

    }

    public static void clickElement(WebElement element) {
        element.click();
    }

    /**
     * Используется для создания сущностей для задаваемых классов, например, когда элемент одинаково отрабатывает для разных страниц
     *
     * @param driver
     * @param clazz
     * @param <E>
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static <E> E getUtility(WebDriver driver, Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (E) clazz.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }

    /**
     * прокрутка до элемента
     * @param element
     * @param driver
     */
    public static void executeScriptScroll(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "arguments[0].scrollIntoView();";
        js.executeScript(script, element);

    }
    /*
    ожидание появление элемента
     */
    public static void waitForElementToBecomeVisible(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    /** Утилиты конец**/
    // Поиск и переход в корзину вынесены в базовую страницу т.к доступы с нескольких страниц сайта
    /**
     * Заполнение поля поиска
     *
     * @param searchText
     */
    public T fillSearchInput(String searchText) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        editTextNoEnter(searchInput, searchText);
        return getUtility(driver, clazz);
    }

    /**
     * поиск
     * @return
     */
    public PageMain pressSearchButton() {
        clickElement(searchSubmitButton);
        return new PageMain(driver);
    }


    /**
     * переход на страницу потребительской корзины через иконку корзины вверху страницы
     *
     * @return
     */
    public PageShopCard navigatePageShopCard() {
        executeScriptScroll(cardButton, driver);
        clickElement(cardButton);
        return new PageShopCard(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

}
