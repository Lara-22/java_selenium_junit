package UIElements;

import Pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Класс ячейки строки с чекбоксом
 */
public class UITDWithCheckbox {
    private WebElement element;

    public UITDWithCheckbox(WebElement element) {
        this.element = element;
    }

    /**
     * Получить элемент для отметки. В данном случае в самом элементе при выделении не меняются атрибуты самого инпута
     *
     * @return
     */
    private WebElement getInputCheckbox() {
        return element.findElement(By.cssSelector("span.ui-checkboxradio-icon"));
    }

    /*
    проверка, если чекбокс уже отмечен
     */
    public boolean isChecked() {
        return element.findElement(By.tagName("label")).getAttribute("class").contains("ui-checkboxradio-checked");
    }

    /**
     * отметить чекбокс
     */
    public void checkCheckbox() {
        if (!isChecked()) {
            PageBase.clickElement(getInputCheckbox());
        }
    }


}
