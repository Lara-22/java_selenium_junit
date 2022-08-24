package UIElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UIToolbar {
    private WebElement element;

    public UIToolbar(WebElement element) {
        this.element = element;
    }

    public WebElement getDeleteButton() {
        return element.findElement(By.name("delete"));
    }
}
