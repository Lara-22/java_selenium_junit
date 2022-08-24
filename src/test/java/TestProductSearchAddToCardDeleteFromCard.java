import Pages.PageMain;
import Pages.PageShopCard;
import ValueObjects.Product;
import ValueObjects.TableRowNotFoundException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestProductSearchAddToCardDeleteFromCard {
    private Logger logger = LoggerFactory.getLogger(TestProductSearchAddToCardDeleteFromCard.class);

    private PageMain pageMain;
    private PageShopCard pageShopCard;
    private WebDriver driver;

    private Product product = Product.newBuilder()
            .setProductIndex("589889")
            .setProductName("Струйное МФУ Canon PIXMA MG2540S принтер/копир/сканер")
            .build();

    @BeforeAll
    public static void setDriverProps() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.onlinetrade.ru/");
        pageMain = new PageMain(driver);
    }

    /**
     * Сценарий:
     * На главной странице заполнить поиск
     * Нажать кнопу искать
     * Найти отфильстрованный товар
     * Нажать кнопку закрыть в диалоге добавления товара
     * перейти в корзину
     * проверить, что товар в корзине
     * удалить товар из корзины
     * проверит что, товар удалился
     */
    @Test
    public void testSearchProductAddToCardDeleteFromCard() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, TableRowNotFoundException {
        logger.debug("Product: {}", product);
        pageMain = pageMain.fillSearchInput(product.getProductIndex())
                .pressSearchButton()
                .addProductToCard(product)
                .clickClosingButton()
        ;
        pageShopCard = pageMain.navigatePageShopCard().navigatePageShopCard();// первый клик открывает страницу регистрации
        assertTrue(pageShopCard.isProductExistsInCard(product), "Product is not in product card");
        pageShopCard = pageShopCard
                .deleteRow(product)
        ;
        assertFalse(pageShopCard.isProductExistsInCard(product), "Product wasn't deleted product card");
    }


    @AfterEach
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

}
