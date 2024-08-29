package VirtualWarehouse;

import base.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Slf4j
public class BulkOrderEntityDetailsPage extends BasePage {

    @FindBy(xpath = "//div[@class='actionsWrapper']")
    private WebElement ActionWrapper;

    @FindBy(xpath = "//span[text()='Issue PO']")
    private WebElement Po_Btn;

    @FindBy(xpath = "//div[@class=' css-17vhrl4']")
    private WebElement Options;

    @FindBy(xpath = "//div[@class='radio-group']")
    List<WebElement> radioGrp;

    @FindBy(xpath = "//input[@id='selectSupplier-input-tfid-0-0']")
    private WebElement SupplierNameHint;

    @FindBy(xpath = "//div[@id='react-select-selectSupplier-option-0']")
    private WebElement SupplierNameValue;

    @FindBy(xpath = "//div[@class=' css-1ul4jvx-option']")
    WebElement PickupAddress;

    @FindBy(xpath = "//div[@class=' css-1m5nn3x']//div")
    private WebElement BillingAddress;

    @FindBy(xpath = "//div[@class=' css-17vhrl4']")
    private List<WebElement> SupplierOptions;

    @FindBy(xpath = "//button[@class='btn btn-primary addContactPersonBtn']")
    private WebElement AddItemsBtn;

    public BulkOrderEntityDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public BulkOrderEntityDetailsPage clickOnPOBtn() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(ActionWrapper));
        highlightElement(driver,ActionWrapper);
        wait.until(ExpectedConditions.elementToBeClickable(ActionWrapper));



        sleep(2);

        wait.until(ExpectedConditions.elementToBeClickable(Po_Btn));
        highlightElement(driver,Po_Btn);
        Po_Btn.click();

        System.out.println("Po_Btn clicked");
        return this;
    }

    public BulkOrderEntityDetailsPage selectSupplier()
    {
        wait.until(ExpectedConditions.visibilityOf(Options));
        highlightElement(driver,Options);
        wait.until(ExpectedConditions.elementToBeClickable(Options));
        Options.click();

        SupplierNameHint.sendKeys("abcs");

        wait.until(ExpectedConditions.visibilityOf(SupplierNameValue));
        highlightElement(driver,SupplierNameValue);
        wait.until(ExpectedConditions.elementToBeClickable(SupplierNameValue));
        SupplierNameValue.click();

        System.out.println("Supplier Selected");
        return this;
    }

    public BulkOrderEntityDetailsPage selectBillingAddress()
    {
        wait.until(ExpectedConditions.visibilityOfAllElements(SupplierOptions));

        WebElement billingAddress= SupplierOptions.get(1);
        wait.until(ExpectedConditions.elementToBeClickable(billingAddress));
        highlightElement(driver,billingAddress);
        billingAddress.click();

        wait.until(ExpectedConditions.visibilityOf(BillingAddress));
        wait.until(ExpectedConditions.elementToBeClickable(BillingAddress));
        highlightElement(driver,BillingAddress);
        BillingAddress.click();

        System.out.println("Billing Address Selected");
        return this;
    }

    public BulkOrderEntityDetailsPage selectPickupAddress()
    {
        WebElement pickupAddress= SupplierOptions.get(2);
        highlightElement(driver,pickupAddress);
        pickupAddress.click();

        wait.until(ExpectedConditions.visibilityOf(PickupAddress));
        highlightElement(driver,PickupAddress);
        PickupAddress.click();

        System.out.println("Picking Address Selected");
        return this;
    }

    public BulkOrderEntityDetailsPage clickOnRadioBtn()
    {
        WebElement radiogroup= radioGrp.get(2);
        highlightElement(driver,radiogroup);

        WebElement radioGrpValue= radiogroup.findElement(By.xpath(".//label"));
        radioGrpValue.click();

        System.out.println("Radio Btn Clicked");
        return this;
    }

    public BulkOrderEntityDetailsPage clickOnAddItemsBtn()
    {
        wait.until(ExpectedConditions.elementToBeClickable(AddItemsBtn));
        highlightElement(driver,AddItemsBtn);
        AddItemsBtn.click();

        System.out.println("Add Items Btn Clicked");
        return this;
    }

    public BulkOrderEntityDetailsPage issuePO() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(ActionWrapper));
        highlightElement(driver,ActionWrapper);
        wait.until(ExpectedConditions.elementToBeClickable(ActionWrapper));
        //ActionWrapper.click();

        sleep(2);

        wait.until(ExpectedConditions.elementToBeClickable(Po_Btn));
        highlightElement(driver,Po_Btn);
        Po_Btn.click();

        wait.until(ExpectedConditions.visibilityOf(Options));
        highlightElement(driver,Options);
        wait.until(ExpectedConditions.elementToBeClickable(Options));
        Options.click();

        SupplierNameHint.sendKeys("abcs");

        wait.until(ExpectedConditions.visibilityOf(SupplierNameValue));
        highlightElement(driver,SupplierNameValue);
        wait.until(ExpectedConditions.elementToBeClickable(SupplierNameValue));
        SupplierNameValue.click();

        wait.until(ExpectedConditions.visibilityOfAllElements(SupplierOptions));

        WebElement billingAddress= SupplierOptions.get(1);
        wait.until(ExpectedConditions.elementToBeClickable(billingAddress));
        highlightElement(driver,billingAddress);
        billingAddress.click();

        wait.until(ExpectedConditions.visibilityOf(BillingAddress));
        wait.until(ExpectedConditions.elementToBeClickable(BillingAddress));
        highlightElement(driver,BillingAddress);
        BillingAddress.click();

        WebElement pickupAddress= SupplierOptions.get(2);
        highlightElement(driver,pickupAddress);
        pickupAddress.click();

        wait.until(ExpectedConditions.visibilityOf(PickupAddress));
        highlightElement(driver,PickupAddress);
        PickupAddress.click();


        WebElement radiogroup= radioGrp.get(2);
        highlightElement(driver,radiogroup);

        WebElement radioGrpValue= radiogroup.findElement(By.xpath(".//label"));
        radioGrpValue.click();

//        WebElement incentiveReason= driver.findElement(By.id("reason-tfid-0-0"));
//        wait.until(ExpectedConditions.visibilityOf(incentiveReason));
//        highlightElement(driver,incentiveReason);
//        incentiveReason.click();
//
//        WebElement incentiveReasonValue= driver.findElement(By.id("react-select-reason-option-0"));
//        wait.until(ExpectedConditions.elementToBeClickable(incentiveReasonValue));
//        highlightElement(driver,incentiveReasonValue);
//        incentiveReasonValue.click();

//        driver.findElement(By.xpath("//span[text()='Incoming']")).click();
//
//        driver.findElement(By.xpath("//span[text()='Without Tax']")).click();
//
//        WebElement incentiveLevel= driver.findElements(By.xpath("//span[text()='Item Level']")).get(1);
//        incentiveLevel.click();


        wait.until(ExpectedConditions.elementToBeClickable(AddItemsBtn));
        highlightElement(driver,AddItemsBtn);
        AddItemsBtn.click();

        return this;
    }
}
