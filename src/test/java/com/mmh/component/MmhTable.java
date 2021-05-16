package com.mmh.component;

import com.mmh.utils.MmhHtmlElementManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MmhTable extends BaseComponent {
    private By table;

    public MmhTable(MmhHtmlElementManager elementManager, By table) {
        super(elementManager);
        this.table = table;
    }

    private WebElement getTable() {
        return elementManager.findElement(table);
    }

    public boolean isCollapsed() {
        return getTable().getAttribute("class").contains("collapsed");
    }

    public List<WebElement> getAllRows() {
        return getTable().findElements(By.cssSelector("tbody>tr:not(.child)"));
    }

    private List<WebElement> getColumnHeaders() {
        return getTable().findElements(By.cssSelector("th"));
    }

    private Integer getColumnIndex(List<WebElement> allColumnsHeader, String columnName) {
        String headerText = allColumnsHeader.get(9).getAttribute("innerText");
        return IntStream.range(0, allColumnsHeader.size())
                .filter(i -> allColumnsHeader.get(i).getAttribute("innerText").contains(columnName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No header found containing " + columnName));
    }

    public WebElement getCell(Integer rowIndex, String columnName) {
        WebElement cell;
        WebElement row = getAllRows().get(0);
        List<WebElement> cells = row.findElements(By.cssSelector("td"));
        Integer index = getColumnIndex(getColumnHeaders(), columnName);
        cell = cells.get(index);
        if (cell.getAttribute("style").contains("display: none")) {
            if (!row.getAttribute("class").contains("parent")) {
                cells.get(0).click();
                elementManager.waitForAnimation();
            }
            JavascriptExecutor js = (JavascriptExecutor) elementManager.getDriver();
            String dtCellIndex = js.executeScript("return arguments[0]._DT_CellIndex", cell).toString();
            Matcher matcher = elementManager.matchPattern(dtCellIndex, "\\{column=(\\d+), row=(\\d+)\\}");
            Assert.assertTrue(matcher.find(), "No row and column index found from _DT_CellIndex attribute");
            String data_dt_column = matcher.group(1);
            String data_dt_row = matcher.group(2);
            cell = elementManager.findElement(By.cssSelector("ul>li[data-dt-row='" + data_dt_row + "'][data-dt-column='"
                    + data_dt_column + "']>span:nth-child(2)"));
        }
        return cell;
    }

    public WebElement getCellFromFirstRow(String columnName) {
        return getCell(0, columnName);
    }
}
//get header and get index from the column name
//get the inner html of collapsed column and set that html
