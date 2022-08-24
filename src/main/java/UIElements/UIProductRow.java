package UIElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Строка продукта в корзине
 */
public class UIProductRow {
    private WebElement element;

    public UIProductRow(WebElement element) {
        this.element = element;
    }

    public List<WebElement> getAllTdElements() {
        return element.findElements(By.cssSelector("td"));
    }

    public void selectRow() {
        UITDWithCheckbox tdWithCheckbox = new UITDWithCheckbox(getAllTdElements().get(0));
        tdWithCheckbox.checkCheckbox();
    }

    public String getProductName() {
        return getAllTdElements().get(2).findElement(By.cssSelector("a.semibold")).getText().trim();
    }

}
