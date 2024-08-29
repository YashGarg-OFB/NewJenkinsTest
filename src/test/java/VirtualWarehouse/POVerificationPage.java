package VirtualWarehouse;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;

import static utils.Helper.changeTokenCookie;
import static utils.Helper.prevToken;


public class POVerificationPage extends BasePage {


    @FindBy(xpath = "//input[@class='form-control searchTxtField']")
    WebElement SearchInput;

    @FindBy(xpath = "//div[@class='supplierWrapper']")
    WebElement RequestedPO;

    @FindBy(xpath = ".//button[@class='btn btn-primary approveBtn']")
    WebElement ApproveBtn;

    @FindBy(xpath = "//div[@class='actionWrapper']//button[@class='btn btn-primary']")
    WebElement SubmitBtn2;

    @FindBy(xpath = "//ul[@class='nav-list']")
    List<WebElement> NavItems;

    @FindBy(xpath = ".//ul[@class='inner-nav-list']//li[2]")
    WebElement PurchaseOrder;


    public POVerificationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public POVerificationPage clickOnApproveBtn()
    {
        wait.until(ExpectedConditions.visibilityOf(RequestedPO));
        wait.until(ExpectedConditions.elementToBeClickable(RequestedPO));
        highlightElement(driver,RequestedPO);


        wait.until(ExpectedConditions.visibilityOf(ApproveBtn));
        wait.until(ExpectedConditions.elementToBeClickable(ApproveBtn));
        highlightElement(driver,ApproveBtn);
        ApproveBtn.click();
        return this;
    }

    public POVerificationPage clickOnSubmitBtn()
    {
        wait.until(ExpectedConditions.visibilityOf(SubmitBtn2));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn2));
        highlightElement(driver,SubmitBtn2);
        SubmitBtn2.click();
        return this;
    }

    public POVerificationPage clickOnBulkOrder() throws InterruptedException {
        sleep(2);

        prevToken= changeTokenCookie(driver,prevToken);


        wait.until(ExpectedConditions.visibilityOfAllElements(NavItems));

        WebElement bulkOrder= NavItems.get(3);
        wait.until(ExpectedConditions.elementToBeClickable(bulkOrder));
        highlightElement(driver,bulkOrder);
        bulkOrder.click();
        return this;
    }

    public POVerificationPage clickOnPurchaseOrder()
    {
        wait.until(ExpectedConditions.visibilityOf(PurchaseOrder));
        WebElement purchaseOrders= driver.findElement(By.xpath("//ul[@class='inner-nav-list']//li[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(purchaseOrders));
        highlightElement(driver,purchaseOrders);
        purchaseOrders.click();
        return this;
    }

    public POVerificationPage verifyPO() throws InterruptedException, IOException {
        wait.until(ExpectedConditions.visibilityOf(RequestedPO));
        wait.until(ExpectedConditions.elementToBeClickable(RequestedPO));
        highlightElement(driver,RequestedPO);


        wait.until(ExpectedConditions.visibilityOf(ApproveBtn));
        wait.until(ExpectedConditions.elementToBeClickable(ApproveBtn));
        highlightElement(driver,ApproveBtn);
        ApproveBtn.click();

        wait.until(ExpectedConditions.visibilityOf(SubmitBtn2));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn2));
        highlightElement(driver,SubmitBtn2);
        SubmitBtn2.click();

        sleep(2);

        prevToken= changeTokenCookie(driver,prevToken);


        wait.until(ExpectedConditions.visibilityOfAllElements(NavItems));

        WebElement bulkOrder= NavItems.get(3);
        wait.until(ExpectedConditions.elementToBeClickable(bulkOrder));
        highlightElement(driver,bulkOrder);
        bulkOrder.click();

        wait.until(ExpectedConditions.visibilityOf(PurchaseOrder));
        WebElement purchaseOrders= bulkOrder.findElement(By.xpath("//ul[@class='inner-nav-list']//li[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(purchaseOrders));
        highlightElement(driver,purchaseOrders);
        purchaseOrders.click();

        return this;
    }
}
