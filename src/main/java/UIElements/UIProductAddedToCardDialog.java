package UIElements;

import Pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;

/**
 * Диалог, появляющийся после добавления товара в корзину
 */
public class UIProductAddedToCardDialog {
    private WebElement element;
    private Class clazz; // диалог появляется на некскольких страницах (в списке продуктов, на странице продукта)
    private WebDriver driver;

    public UIProductAddedToCardDialog(WebElement element, Class clazz, WebDriver driver) {
        this.element = element;
        this.clazz = clazz;
        this.driver = driver;
    }

    /**
     * функция закрытия диалога добавления товара в корзину
     *
     * @param <T>
     * @return
     */
    public <T extends PageBase> T clickClosingButton() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        PageBase.clickElement(element.findElement(By.cssSelector("a[title='Закрыть окно']")));
        return PageBase.getUtility(driver, clazz);
    }
}
