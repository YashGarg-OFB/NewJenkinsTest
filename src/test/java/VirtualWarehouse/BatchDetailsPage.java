package VirtualWarehouse;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static utils.Helper.generateInvoiceNumber;
import static utils.Helper.prop;

public class BatchDetailsPage extends BasePage {

    @FindBy(xpath = "//label[@class='uploadButton undefined']")
    WebElement UploadStiBtn;

    @FindBy(xpath = "//input[@id='tfid-0-2']")
    WebElement InvoiceInput;

    @FindBy(className = "react-datepicker__input-container" )
    WebElement DatePicker;

    @FindBy(css = "div.react-datepicker__current-month.react-datepicker__current-month--hasYearDropdown.react-datepicker__current-month--hasMonthDropdown")
    WebElement MonthYearBtn;

    @FindBy(xpath = "//div[contains(@class, 'react-datepicker__day--today')]")
    WebElement CurrDateBtn;

    @FindBy(xpath = "//input[@placeholder='Select invoice date']")
    WebElement InvoiceDate;

    @FindBy(xpath = "//button[@class='redirectBlock']")
    WebElement RedirectBlock;

    @FindBy(xpath = "//div[@class='lineItemCard']")
    List<WebElement> ItemList;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    WebElement SubmitBtn;

    @FindBy(xpath = "//button[@class='btn btn-primary' and contains(text(),'Move Items to Warehouse ')]")
    WebElement MoveToWarehouseBtn;

    @FindBy(xpath = "//div[@class=' css-1i8o47w']")
    WebElement MultipleUsedXpath;

    @FindBy(xpath = "//div[@id='react-select-ofbEntityAddress-option-0']")
    WebElement AddressValue;

    @FindBy(xpath = "//input[@id='ofbFulfillmentSpoc-input-tfid-0-2']")
    WebElement FulfillmentSpoc;

    @FindBy(xpath = "//div[@id='react-select-ofbFulfillmentSpoc-option-0']")
    WebElement FulfillmentSpocValue;

    @FindBy(xpath = "//input[@id='tfid-0-3']")
    WebElement VehicleInout;

    @FindBy(xpath = "//span[@class='value txt-blue']")
    WebElement CompleteDeliveryIn;

    @FindBy(xpath = "//input[@id='selectLsp-input-tfid-0-1']")
    WebElement LSPNameHint;

    @FindBy(xpath = "//div[@id='react-select-selectLsp-option-0']")
    WebElement LSPNameValue;

    @FindBy(xpath = "//div[@id='react-select-lspGst-option-0']")
    WebElement GSTValue;

    @FindBy(xpath = "//input[@id='ofbEntity-input-tfid-0-14']")
    WebElement OFBEntity;

    @FindBy(xpath = "//div[@id='react-select-ofbEntity-option-0']")
    WebElement OFBEntityvalue;

    @FindBy(xpath = "//input[@id='tfid-0-1']")
    WebElement PricePerUnit;

    @FindBy(xpath = "//div[@class=' css-17vhrl4']")
    List<WebElement> Options;

    @FindBy(xpath = "//input[@placeholder='Select Date']")
    WebElement DateInput;

    @FindBy(xpath = "//input[@id='ofbWarehouseSpoc-input-tfid-0-1']")
    WebElement WarehouseSpocInput;

    @FindBy(xpath = "//div[@id='react-select-ofbWarehouseSpoc-option-0']")
    WebElement WarehouseSpocValue;



    public static final By QTY_REMAINING= By.xpath(".//div[@class='qtyChip'][2]//span[@class='chip-value']");
    public static final By QTY_INPUT= By.xpath(".//input[@name='otherCharges']");
    public static final By RADIO_BTN= By.xpath(".//div[@class='radio-group']");

    public BatchDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public BatchDetailsPage sendDoc(String file)
    {
        wait.until(ExpectedConditions.visibilityOf(UploadStiBtn));

        WebElement uploadStiFile= UploadStiBtn.findElement(By.xpath(".//input"));
        uploadStiFile.sendKeys("/home/yash/Pictures/Screenshots/screenshot.png");
        return this;
    }

    public BatchDetailsPage sendInvoiceDate()
    {
        DatePicker.click();

        String month= MonthYearBtn.getText().split(" ")[0];
        String year= MonthYearBtn.getText().split(" ")[1];

        String date= CurrDateBtn.getText()+" "+ month+" "+year;

        InvoiceDate.sendKeys(date);
        return this;
    }

    public BatchDetailsPage sendInvoiceNumber()
    {
        WebElement invoiceNumber= driver.findElement(By.xpath("//input[@name='invoiceId']"));
        invoiceNumber.sendKeys(String.valueOf(generateInvoiceNumber()));
        return this;
    }

    public BatchDetailsPage clickOnRedirectBlock()
    {
        wait.until(ExpectedConditions.visibilityOf(RedirectBlock));
        wait.until(ExpectedConditions.elementToBeClickable(RedirectBlock));
        highlightElement(driver,RedirectBlock);
        RedirectBlock.click();
        return this;
    }

    public BatchDetailsPage fillItemDetails() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfAllElements(ItemList));

        for(WebElement item:ItemList)
        {
            highlightElement(driver,item);

            WebElement checkBox= item.findElement(By.xpath(".//label//input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkBox);

            WebElement quantityRemainingElement= item.findElement(QTY_REMAINING);
            highlightElement(driver,quantityRemainingElement);
            String qty= quantityRemainingElement.getText();

            WebElement quantityInput= item.findElement(QTY_INPUT);
            highlightElement(driver,quantityInput);
            quantityInput.sendKeys(qty);

            sleep(1);

            WebElement radioGroup= item.findElement(RADIO_BTN);
            highlightElement(driver,radioGroup);

            WebElement radioBtn= radioGroup.findElement(By.xpath(".//label"));
            radioBtn.click();
        }
        return this;
    }

    public BatchDetailsPage clickOnSubmitBtn() throws InterruptedException {
        sleep(2);
        wait.until(ExpectedConditions.visibilityOf(SubmitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver, SubmitBtn);
        SubmitBtn.click();
        return this;
    }

    public BatchDetailsPage clickOnMoveToWarehouse()
    {
        wait.until(ExpectedConditions.visibilityOf(MoveToWarehouseBtn));
        wait.until(ExpectedConditions.elementToBeClickable(MoveToWarehouseBtn));
        highlightElement(driver,MoveToWarehouseBtn);
        MoveToWarehouseBtn.click();
        return this;
    }

    public BatchDetailsPage selectAddress()
    {
        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXpath));
        wait.until(ExpectedConditions.elementToBeClickable(MultipleUsedXpath));
        highlightElement(driver,MultipleUsedXpath);
        MultipleUsedXpath.click();

        wait.until(ExpectedConditions.visibilityOf(AddressValue));
        wait.until(ExpectedConditions.elementToBeClickable(AddressValue));
        highlightElement(driver,AddressValue);
        AddressValue.click();
        return this;
    }

    public BatchDetailsPage selectFulfillmentSpoc()
    {
        WebElement fulfillmentSpoc= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(fulfillmentSpoc));
        highlightElement(driver,fulfillmentSpoc);
        fulfillmentSpoc.click();

        FulfillmentSpoc.sendKeys("Ayush Chaudhary");

        wait.until(ExpectedConditions.visibilityOf(FulfillmentSpocValue));
        wait.until(ExpectedConditions.elementToBeClickable(FulfillmentSpocValue));
        FulfillmentSpocValue.click();
        return this;
    }

    public BatchDetailsPage sendVehicleInput() throws InterruptedException {
        VehicleInout.sendKeys(prop.getProperty("test-vehicle"));
        return this;
    }

    public BatchDetailsPage clickOnCompleteDeliveryIn() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(CompleteDeliveryIn));
        wait.until(ExpectedConditions.elementToBeClickable(CompleteDeliveryIn));
        highlightElement(driver,CompleteDeliveryIn);
        CompleteDeliveryIn.click();

        return this;
    }

    public BatchDetailsPage switchToDeliveryIn()
    {
        for(String handle: driver.getWindowHandles())
            driver.switchTo().window(handle);

        wait.until(ExpectedConditions.visibilityOf(SubmitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();
        return this;
    }

    public BatchDetailsPage selectLsp()
    {
        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXpath));
        WebElement lspName= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(lspName));
        highlightElement(driver,lspName);
        lspName.click();

        LSPNameHint.sendKeys("l");

        wait.until(ExpectedConditions.visibilityOf(LSPNameValue));
        wait.until(ExpectedConditions.elementToBeClickable(LSPNameValue));
        highlightElement(driver,LSPNameValue);
        LSPNameValue.click();
        return this;
    }

    public BatchDetailsPage selectGst()
    {
        WebElement gstIN= Options.get(2);
        wait.until(ExpectedConditions.elementToBeClickable(gstIN));
        highlightElement(driver,gstIN);
        gstIN.click();

        wait.until(ExpectedConditions.visibilityOf(GSTValue));
        wait.until(ExpectedConditions.elementToBeClickable(GSTValue));
        highlightElement(driver,GSTValue);
        GSTValue.click();
        return this;
    }

    public BatchDetailsPage selectOfbEntity() throws InterruptedException {
        WebElement ofbEntity= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(ofbEntity));
        highlightElement(driver,ofbEntity);
        ofbEntity.click();

        wait.until(ExpectedConditions.visibilityOf(OFBEntity));
        OFBEntity.sendKeys("TK");

        wait.until(ExpectedConditions.visibilityOf(OFBEntityvalue));
        wait.until(ExpectedConditions.elementToBeClickable(OFBEntityvalue));
        highlightElement(driver,OFBEntityvalue);
        OFBEntityvalue.click();

        clickOnSubmitBtn();
        return this;
    }

    public BatchDetailsPage sendPricePerUnit() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(PricePerUnit));
        PricePerUnit.sendKeys("100");

        clickOnSubmitBtn();
        return this;
    }

    public BatchDetailsPage sendDate()
    {
        wait.until(ExpectedConditions.visibilityOf(DatePicker));
        DatePicker.click();

        String month= MonthYearBtn.getText().split(" ")[0];
        String year= MonthYearBtn.getText().split(" ")[1];
        String date= CurrDateBtn.getText()+" "+ month+" "+year;

        DateInput.sendKeys(date);
        return this;
    }

    public BatchDetailsPage selectWarehouseSpoc() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXpath));
        WebElement warehouseSpoc= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(warehouseSpoc));
        highlightElement(driver,warehouseSpoc);
        warehouseSpoc.click();

        WarehouseSpocInput.sendKeys("anas A");

        wait.until(ExpectedConditions.visibilityOf(WarehouseSpocValue));
        wait.until(ExpectedConditions.elementToBeClickable(WarehouseSpocValue));
        highlightElement(driver,WarehouseSpocValue);
        WarehouseSpocValue.click();
        return this;
    }


    public BatchDetailsPage uploadSti() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(UploadStiBtn));

        WebElement uploadStiFile= UploadStiBtn.findElement(By.xpath(".//input"));
        uploadStiFile.sendKeys("/home/yash/Pictures/Screenshots/screenshot.png");

        wait.until(ExpectedConditions.visibilityOf(InvoiceInput));
        InvoiceInput.sendKeys(String.valueOf(generateInvoiceNumber()));

        DatePicker.click();

        String month= MonthYearBtn.getText().split(" ")[0];
        String year= MonthYearBtn.getText().split(" ")[1];

        String date= CurrDateBtn.getText()+" "+ month+" "+year;

        InvoiceDate.sendKeys(date);

        wait.until(ExpectedConditions.visibilityOf(RedirectBlock));
        wait.until(ExpectedConditions.elementToBeClickable(RedirectBlock));
        highlightElement(driver,RedirectBlock);
        RedirectBlock.click();

        wait.until(ExpectedConditions.visibilityOfAllElements(ItemList));

        for(WebElement item:ItemList)
        {
            highlightElement(driver,item);

            WebElement checkBox= item.findElement(By.xpath(".//label//input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkBox);

            WebElement quantityRemainingElement= item.findElement(QTY_REMAINING);
            highlightElement(driver,quantityRemainingElement);
            String qty= quantityRemainingElement.getText();

            WebElement quantityInput= item.findElement(QTY_INPUT);
            highlightElement(driver,quantityInput);
            quantityInput.sendKeys(qty);

            sleep(2);

            WebElement radioGroup= item.findElement(RADIO_BTN);
            highlightElement(driver,radioGroup);

            WebElement radioBtn= radioGroup.findElement(By.xpath(".//label"));
            radioBtn.click();
        }

        wait.until(ExpectedConditions.visibilityOf(SubmitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver, SubmitBtn);
        SubmitBtn.click();

        sleep(2);

        wait.until(ExpectedConditions.visibilityOf(SubmitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();

        wait.until(ExpectedConditions.visibilityOf(MoveToWarehouseBtn));
        wait.until(ExpectedConditions.elementToBeClickable(MoveToWarehouseBtn));
        highlightElement(driver,MoveToWarehouseBtn);
        MoveToWarehouseBtn.click();
        return this;
    }

    public BatchDetailsPage moveToWarehouse()
    {
        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXpath));
        wait.until(ExpectedConditions.elementToBeClickable(MultipleUsedXpath));
        highlightElement(driver,MultipleUsedXpath);
        MultipleUsedXpath.click();

        wait.until(ExpectedConditions.visibilityOf(AddressValue));
        wait.until(ExpectedConditions.elementToBeClickable(AddressValue));
        highlightElement(driver,AddressValue);
        AddressValue.click();

        WebElement fulfillmentSpoc= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(fulfillmentSpoc));
        highlightElement(driver,fulfillmentSpoc);
        fulfillmentSpoc.click();

        FulfillmentSpoc.sendKeys("Ayush Chaudhary");

        wait.until(ExpectedConditions.visibilityOf(FulfillmentSpocValue));
        wait.until(ExpectedConditions.elementToBeClickable(FulfillmentSpocValue));
        FulfillmentSpocValue.click();

        VehicleInout.sendKeys(prop.getProperty("test-vehicle"));

        wait.until(ExpectedConditions.visibilityOf(SubmitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();

        wait.until(ExpectedConditions.visibilityOf(SubmitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();

        wait.until(ExpectedConditions.visibilityOf(CompleteDeliveryIn));
        wait.until(ExpectedConditions.elementToBeClickable(CompleteDeliveryIn));
        highlightElement(driver,CompleteDeliveryIn);
        CompleteDeliveryIn.click();

        wait.until(ExpectedConditions.visibilityOf(SubmitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        SubmitBtn.click();

        return this;
    }

    public BatchDetailsPage createDeliveryIn()
    {
        for(String handle: driver.getWindowHandles())
            driver.switchTo().window(handle);

        wait.until(ExpectedConditions.visibilityOf(SubmitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();

        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXpath));
        WebElement lspName= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(lspName));
        highlightElement(driver,lspName);
        lspName.click();

        LSPNameHint.sendKeys("l");

        wait.until(ExpectedConditions.visibilityOf(LSPNameValue));
        wait.until(ExpectedConditions.elementToBeClickable(LSPNameValue));
        highlightElement(driver,LSPNameValue);
        LSPNameValue.click();


        WebElement gstIN= Options.get(2);
        wait.until(ExpectedConditions.elementToBeClickable(gstIN));
        highlightElement(driver,gstIN);
        gstIN.click();

        wait.until(ExpectedConditions.visibilityOf(GSTValue));
        wait.until(ExpectedConditions.elementToBeClickable(GSTValue));
        highlightElement(driver,GSTValue);
        GSTValue.click();

        WebElement ofbEntity= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(ofbEntity));
        highlightElement(driver,ofbEntity);
        ofbEntity.click();

        wait.until(ExpectedConditions.visibilityOf(OFBEntity));
        OFBEntity.sendKeys("TK");

        wait.until(ExpectedConditions.visibilityOf(OFBEntityvalue));
        wait.until(ExpectedConditions.elementToBeClickable(OFBEntityvalue));
        highlightElement(driver,OFBEntityvalue);
        OFBEntityvalue.click();

        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();


        wait.until(ExpectedConditions.visibilityOf(PricePerUnit));
        PricePerUnit.sendKeys("100");

        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();


        wait.until(ExpectedConditions.visibilityOf(DatePicker));
        DatePicker.click();

        String month= MonthYearBtn.getText().split(" ")[0];
        String year= MonthYearBtn.getText().split(" ")[1];
        String date= CurrDateBtn.getText()+" "+ month+" "+year;

        DateInput.sendKeys(date);

        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXpath));
        WebElement warehouseSpoc= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(warehouseSpoc));
        highlightElement(driver,warehouseSpoc);
        warehouseSpoc.click();

        WarehouseSpocInput.sendKeys("anas A");

        wait.until(ExpectedConditions.visibilityOf(WarehouseSpocValue));
        wait.until(ExpectedConditions.elementToBeClickable(WarehouseSpocValue));
        highlightElement(driver,WarehouseSpocValue);
        WarehouseSpocValue.click();

        wait.until(ExpectedConditions.visibilityOf(SubmitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();

        return this;
    }
}
