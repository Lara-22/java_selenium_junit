package UIElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Карточка продукта, в списке, выдаваемым при поиске
 */
public class UISearchListingElement {
    private WebElement element;

    public UISearchListingElement(WebElement element) {
        this.element = element;
    }

    public String getProductName() {
        return element.findElement(By.cssSelector("div.indexGoods__item__descriptionCover > a")).getText().trim();
    }

    public WebElement getAddToCardButton() {
        return element.findElement(By.cssSelector("a[data-handler = 'buy']"));
    }
}
