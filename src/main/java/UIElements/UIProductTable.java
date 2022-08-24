package UIElements;

import ValueObjects.Product;
import ValueObjects.TableRowNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UIProductTable {
    private WebElement element;

    public UIProductTable(WebElement element) {
        this.element = element;
    }

    /**
     * все отобрадаемые на странице строки с продуктами
     * @return
     */
    public List<UIProductRow> getProductRows() {
        List<WebElement> elementList = element.findElements(By.cssSelector("tbody.multicart__item tr"));
        List<UIProductRow> productRows = new ArrayList<UIProductRow>();
        for (WebElement element : elementList) {
            productRows.add(new UIProductRow(element));
        }
        return productRows;
    }

    /**
     * поиск строки таблицы, соответствующей передавемому продукту
     * @param product
     * @return
     * @throws TableRowNotFoundException
     */
    public UIProductRow getMatchingRow(Product product) throws TableRowNotFoundException {
        List<UIProductRow> ss = getProductRows();
        List<UIProductRow> result = getProductRows().stream().filter(x -> x.getProductName().equals(product.getProductName())).collect(Collectors.toList());
        if (result.isEmpty())
            throw new TableRowNotFoundException("Product " + product.getProductName() + " not found");
        return result.get(0);
    }

    /**
     * проверка, если передаваемый продукт присутсввует в таблице
     * @param product
     * @return
     */
    public boolean isProductRowFoundInTable(Product product) {
        boolean tf = false;
        try {
            getMatchingRow(product);
            tf = true;
        } catch (TableRowNotFoundException e) {
            e.printStackTrace();
        }
        return tf;
    }


}
