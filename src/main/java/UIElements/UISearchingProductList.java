package UIElements;

import ValueObjects.Product;
import ValueObjects.TableRowNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * список карточек продукта, выдаваемых при поиске
 */
public class UISearchingProductList {
    private WebElement element;

    public UISearchingProductList(WebElement element) {
        this.element = element;
    }

    public List<UISearchListingElement> getSearchingProductList() {
        List<WebElement> list = element.findElements(By.className("indexGoods__item"));
        List<UISearchListingElement> searchingElementList = new ArrayList<>();
        for (WebElement element : list) {
            searchingElementList.add(new UISearchListingElement(element));
        }
        return searchingElementList;
    }

    public UISearchListingElement getSearchingListMatchingElement(Product product) throws TableRowNotFoundException {
        List<UISearchListingElement> result = getSearchingProductList().stream().filter(x -> x.getProductName().equals(product.getProductName())).collect(Collectors.toList());
        if (result.isEmpty())
            throw new TableRowNotFoundException("Product " + product.getProductName() + "is missing in product list");
        return result.get(0);
    }

    public boolean isSearchingElementExist(Product product) {
        boolean tf = false;
        try {
            getSearchingListMatchingElement(product);
            tf = true;
        } catch (TableRowNotFoundException e) {
            e.printStackTrace();
        }
        return tf;
    }
}
