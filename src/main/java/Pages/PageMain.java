package Pages;

import UIElements.UIProductAddedToCardDialog;
import UIElements.UISearchListingElement;
import UIElements.UISearchingProductList;
import ValueObjects.Product;
import ValueObjects.TableRowNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * главная сраница https://www.onlinetrade.ru/
 */
public class PageMain extends PageBase<PageMain> {
    public PageMain(WebDriver driver) {
        super(driver, PageMain.class);
    }

    public UISearchingProductList getSearchingProductList() {
        return new UISearchingProductList(getDriver().findElement(By.className("searchlisting")));
    }

    public boolean isSearchingProductExist(Product product) {
        boolean tf = false;
        try {
            tf = getSearchingProductList().isSearchingElementExist(product);
        } catch (NoSuchElementException e) {

        }
        return tf;
    }

    public UIProductAddedToCardDialog addProductToCard(Product product) throws TableRowNotFoundException {
        UISearchListingElement searchListingElement = getSearchingProductList().getSearchingListMatchingElement(product);
        PageBase.executeScriptScroll(searchListingElement.getAddToCardButton(), getDriver());
        clickElement(searchListingElement.getAddToCardButton());
        waitForElementToBecomeVisible(getDriver(), By.id("popup_buy"));
        return new UIProductAddedToCardDialog(getDriver().findElement(By.id("popup_buy")), PageMain.class, getDriver());

    }
}
