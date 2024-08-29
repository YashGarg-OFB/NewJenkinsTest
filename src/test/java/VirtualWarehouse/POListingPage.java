package VirtualWarehouse;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static utils.Helper.poNumber;


public class POListingPage extends BasePage {

    @FindBy(xpath = "//input[@id='tfid-0-0']")
    WebElement PO_Search;

    @FindBy(xpath = "//div[@class='cardItemWrapper wareHousePOItemList']")
    WebElement ListSection;

    @FindBy(xpath = "//div[@class='listItemRow']" )
    List<WebElement> ListItems;


    public POListingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public POListingPage searchPO() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(PO_Search));
        PO_Search.sendKeys(poNumber);

        sleep(2);
        PO_Search.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOf(ListSection));
        wait.until(ExpectedConditions.elementToBeClickable(ListSection));
        highlightElement(driver,ListSection);

        wait.until(ExpectedConditions.visibilityOfAllElements(ListItems));
        highlightElement(driver,ListItems.get(0));
        ListItems.get(0).click();

        return this;
    }


}
