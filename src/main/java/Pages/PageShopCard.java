package Pages;

import UIElements.UIProductTable;
import UIElements.UIToolbar;
import ValueObjects.Product;
import ValueObjects.TableRowNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Страница корзины
 */
public class PageShopCard extends PageBase {
    public PageShopCard(WebDriver driver) {
        super(driver, PageShopCard.class);
    }

    public UIProductTable getProductTable() {
        return new UIProductTable(getDriver().findElement(By.cssSelector("table.multicart__itemsTable")));
    }

    public UIToolbar getToolbar() {
        return new UIToolbar(getDriver().findElement(By.cssSelector("div.multicart__items__manageLine")));
    }

    public boolean isProductExistsInCard(Product product) {
        boolean tf = false;
        try {
            tf = getProductTable().isProductRowFoundInTable(product);
        } catch (NoSuchElementException e) {
        }
        return tf;
    }

    public PageShopCard selectProductRow(Product product) throws TableRowNotFoundException {
        if (isProductExistsInCard(product)) {
            getProductTable().getMatchingRow(product).selectRow();
        }
        return new PageShopCard(getDriver());
    }

    public PageShopCard deleteRow(Product product) throws TableRowNotFoundException {
        selectProductRow(product);
        clickElement(getToolbar().getDeleteButton());
        return new PageShopCard(getDriver());
    }

}
