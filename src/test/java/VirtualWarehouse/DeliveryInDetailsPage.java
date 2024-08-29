package VirtualWarehouse;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class DeliveryInDetailsPage extends BasePage {


    @FindBy(xpath = "//div[@class='shipmentStatusJourney']")
    WebElement TrackingSection;
    
    @FindBy(xpath = "//div[@id='react-select-shipmentStatus-option-0']")
    WebElement NewStatus;
    
    @FindBy(xpath = "//span[@class='documentUploadBtn']")
    WebElement UploadDoc;

    @FindBy(xpath = "//div[@class='formItems']")
    WebElement Form;

    @FindBy(xpath = "//button[text()='Update Status']")
    WebElement UpdateStatus;

    @FindBy(xpath = "//input[@id='agreementCheckbox']")
    WebElement AgreementCheckbox;

    @FindBy(xpath = "//div[@class='amt']")
    WebElement ShipmentAmt;

    @FindBy(xpath = "//input[@class='inputCaptcha']")
    WebElement captchaInput;

    @FindBy(xpath = "//div[@class='lotDetailContainer']")
    List<WebElement> LotContainer;

    @FindBy(xpath = "//div[@class='actionItem']")
    List<WebElement>  ActionItem;

    @FindBy(xpath = "//button[text()='Share with 1 person(s)']")
    WebElement ShareBtn;

    @FindBy(xpath = "//button[text()='Save']")
    WebElement SaveBtn;

    @FindBy(xpath = "//div[@class='modalFooter']//span[@class='loading-icon m-r-10 hide']")
    WebElement LoadingIcon;

    @FindBy(xpath = "//button[@class='btn btn-primary' and contains(text(),'Next')]")
    WebElement NextBtn;

    @FindBy(xpath = "//button[text()='Share GRN']")
    WebElement ShareGRNBtn;

    @FindBy(xpath = "//input[@placeholder='Select Date']")
    WebElement DateInput;

    @FindBy(xpath = "//div[@class=' css-1i8o47w']")
    WebElement MultipleUsedXpath;

    @FindBy(xpath = "//input[@id='assignedTo-input-tfid-0-0']")
    WebElement PersonHint;

    @FindBy(xpath = "//div[@id='react-select-assignedTo-option-0']")
    WebElement PersonValue;

    @FindBy(className = "react-datepicker__input-container")
    WebElement DatePicker;

    @FindBy(css = "div.react-datepicker__current-month.react-datepicker__current-month--hasYearDropdown.react-datepicker__current-month--hasMonthDropdown")
    WebElement MonthYearBtn;

    @FindBy(xpath = "//div[contains(@class, 'react-datepicker__day--today')]")
    WebElement CurrDateBtn;

    @FindBy(xpath = "//input[@placeholder='Select Date']")
    WebElement DateInput2;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    WebElement SubmitBtn;


    public DeliveryInDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public DeliveryInDetailsPage clickOnUpdateStatus() throws InterruptedException {
        sleep(2);
        wait.until(ExpectedConditions.visibilityOf(TrackingSection));
        highlightElement(driver,TrackingSection);

        WebElement updateStatusBtn= TrackingSection.findElement(By.xpath(".//span"));
        wait.until(ExpectedConditions.elementToBeClickable(updateStatusBtn));
        highlightElement(driver,updateStatusBtn);
        updateStatusBtn.click();
        return this;
    }

    public DeliveryInDetailsPage selectNewStatus()
    {
        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXpath));
        WebElement newStatus= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(newStatus));
        highlightElement(driver,newStatus);
        newStatus.click();

        wait.until(ExpectedConditions.visibilityOf(NewStatus));
        wait.until(ExpectedConditions.elementToBeClickable(NewStatus));
        highlightElement(driver,NewStatus);
        NewStatus.click();
        return this;
    }

    public DeliveryInDetailsPage sendDate()
    {
        wait.until(ExpectedConditions.visibilityOf(DatePicker));
        DatePicker.click(); wait.until(ExpectedConditions.visibilityOf(DatePicker));
        DatePicker.click();

        String month= MonthYearBtn.getText().split(" ")[0];
        String year= MonthYearBtn.getText().split(" ")[1];

        String date= CurrDateBtn.getText()+" "+ month+" "+year;

        DateInput.sendKeys(date);
        return this;
    }

    public DeliveryInDetailsPage uploadFile(String file) throws InterruptedException {
        sleep(1);

        wait.until(ExpectedConditions.visibilityOf(UploadDoc));
        wait.until(ExpectedConditions.elementToBeClickable(UploadDoc));
        highlightElement(driver,UploadDoc);

        WebElement uploadDocInput= UploadDoc.findElement(By.xpath(".//input"));
        uploadDocInput.sendKeys(file);
        return this;
    }

    public DeliveryInDetailsPage fillFormDetails()
    {
        wait.until(ExpectedConditions.visibilityOf(Form));
        highlightElement(driver,Form);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//div[@class=' css-1i8o47w']")));
        List<WebElement> formDropdownOptions=  Form.findElements(By.xpath(".//div[@class=' css-1i8o47w']"));


        WebElement issuingPartyDropdown= formDropdownOptions.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(issuingPartyDropdown));
        highlightElement(driver,issuingPartyDropdown);
        issuingPartyDropdown.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-issuingParty-option-1']")));
        WebElement issuingPartyValue= Form.findElement(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-issuingParty-option-1']"));
        wait.until(ExpectedConditions.elementToBeClickable(issuingPartyValue));
        highlightElement(driver,issuingPartyValue);
        issuingPartyValue.click();

        WebElement receivingParty= formDropdownOptions.get(1);
        wait.until(ExpectedConditions.elementToBeClickable(receivingParty));
        highlightElement(driver,receivingParty);
        receivingParty.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-receivingParty-option-0']")));
        WebElement receivingPartyValue= Form.findElement(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-receivingParty-option-0']"));
        wait.until(ExpectedConditions.elementToBeClickable(receivingPartyValue));
        highlightElement(driver,receivingPartyValue);
        receivingPartyValue.click();
        return this;
    }

    public DeliveryInDetailsPage clickOnUpdateStatusBtn()
    {
        wait.until(ExpectedConditions.visibilityOf(UpdateStatus));
        wait.until(ExpectedConditions.elementToBeClickable(UpdateStatus));
        highlightElement(driver,UpdateStatus);
        UpdateStatus.click();
        return this;
    }

    public DeliveryInDetailsPage clickOnSubmit() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();
        return this;
    }

    public DeliveryInDetailsPage sendDate2()
    {
        wait.until(ExpectedConditions.visibilityOf(DatePicker));
        DatePicker.click();

        String month= MonthYearBtn.getText().split(" ")[0];
        String year= MonthYearBtn.getText().split(" ")[1];
        String date= CurrDateBtn.getText()+" "+ month+" "+year;

        DateInput2.sendKeys(date);
        return this;
    }

    public DeliveryInDetailsPage clickOnMarkDelivered()
    {
        wait.until(ExpectedConditions.visibilityOfAllElements(ActionItem));
        WebElement markDeliveredBtn= ActionItem.get(0);
        markDeliveredBtn= driver.findElement(By.xpath("//span[text()='Mark Delivered']"));
        wait.until(ExpectedConditions.elementToBeClickable(markDeliveredBtn));
        highlightElement(driver,markDeliveredBtn);
        markDeliveredBtn.click();
        System.out.println("Mark Delivered Button Clicked");
        return this;
    }

    public DeliveryInDetailsPage clickOnCheckbox()
    {
        wait.until(ExpectedConditions.visibilityOf(AgreementCheckbox));
        AgreementCheckbox.click();
        return this;
    }

    public DeliveryInDetailsPage fillCaptcha() throws InterruptedException {
        String shipmentAmt= ShipmentAmt.getText();
        captchaInput.sendKeys(shipmentAmt);
        clickOnSubmit();
        return this;
    }

    public DeliveryInDetailsPage fillItemDetails()
    {
        wait.until(ExpectedConditions.visibilityOfAllElements(LotContainer));
        List<WebElement> itemsList= LotContainer;

        for(WebElement item:itemsList)
        {
            highlightElement(driver,item);
            WebElement expectedQty= item.findElement(By.xpath(".//div[@class='col s12 m2']"));
            highlightElement(driver,expectedQty);
            String expectedQtyValue= expectedQty.getText();

            WebElement receivedQty= item.findElement(By.xpath(".//input[@id='tfid-0-0']"));
            receivedQty.sendKeys(expectedQtyValue);
        }
        return this;
    }

    public DeliveryInDetailsPage fillForm()
    {
        wait.until(ExpectedConditions.visibilityOf(Form));
        highlightElement(driver,Form);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=' css-1i8o47w']")));
        List<WebElement> formDropdownOptions=  Form.findElements(By.xpath(".//div[@class=' css-1i8o47w']"));


        WebElement issuingPartyDropdown= formDropdownOptions.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(issuingPartyDropdown));
        highlightElement(driver,issuingPartyDropdown);
        issuingPartyDropdown.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-issuingParty-option-0']")));
        WebElement issuingPartyValue= Form.findElement(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-issuingParty-option-0']"));
        wait.until(ExpectedConditions.elementToBeClickable(issuingPartyValue));
        highlightElement(driver,issuingPartyValue);
        issuingPartyValue.click();

        WebElement receivingParty= formDropdownOptions.get(1);
        wait.until(ExpectedConditions.elementToBeClickable(receivingParty));
        highlightElement(driver,receivingParty);
        receivingParty.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='reactSelectContainer']//div[@id='react-select-receivingParty-option-1']")));
        WebElement receivingPartyValue= Form.findElement(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-receivingParty-option-1']"));
        wait.until(ExpectedConditions.elementToBeClickable(receivingPartyValue));
        highlightElement(driver,receivingPartyValue);
        receivingPartyValue.click();
        return this;
    }

    public DeliveryInDetailsPage clickOnSaveBtn() throws InterruptedException {
        sleep(2);
        highlightElement(driver,SaveBtn);
        SaveBtn.click();
        return this;
    }

    public DeliveryInDetailsPage clickOnSave()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='input-group-addon addon-after']")));
        WebElement docUploaded= driver.findElement(By.xpath("//span[@class='input-group-addon addon-after']"));
        highlightElement(driver,docUploaded);

        highlightElement(driver,SaveBtn);
        SaveBtn.click();
        return this;
    }

    public DeliveryInDetailsPage clickOnNextBtn() throws InterruptedException {
        wait.until(ExpectedConditions.invisibilityOf(LoadingIcon));

        sleep(1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Next')]")));
        WebElement nextBtn= driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
        highlightElement(driver,nextBtn);
        nextBtn.click();

        sleep(2);
        return this;
    }

    public DeliveryInDetailsPage clickOnShareGRN()
    {
        wait.until(ExpectedConditions.visibilityOf(ShareGRNBtn));
        wait.until(ExpectedConditions.elementToBeClickable(ShareGRNBtn));
        highlightElement(driver,ShareGRNBtn);
        ShareGRNBtn.click();
        return this;
    }

    public DeliveryInDetailsPage selectPersonName()
    {
        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXpath));
        WebElement person_dropdown= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(person_dropdown));
        highlightElement(driver,person_dropdown);
        person_dropdown.click();

        wait.until(ExpectedConditions.visibilityOf(PersonHint));
        PersonHint.sendKeys("a");

        wait.until(ExpectedConditions.visibilityOf(PersonValue));
        wait.until(ExpectedConditions.elementToBeClickable(PersonValue));
        highlightElement(driver,PersonValue);
        PersonValue.click();
        return this;
    }

    public DeliveryInDetailsPage clickOnShareBtn()
    {
        wait.until(ExpectedConditions.visibilityOf(ShareBtn));
        wait.until(ExpectedConditions.elementToBeClickable(ShareBtn));
        highlightElement(driver,ShareBtn);
        ShareBtn.click();
        return this;
    }

    public DeliveryInDetailsPage updateTrackingDetails() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(TrackingSection));
        highlightElement(driver,TrackingSection);

        WebElement updateStatusBtn= TrackingSection.findElement(By.xpath(".//span"));
        wait.until(ExpectedConditions.elementToBeClickable(updateStatusBtn));
        highlightElement(driver,updateStatusBtn);
        updateStatusBtn.click();

        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXpath));
        WebElement newStatus= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(newStatus));
        highlightElement(driver,newStatus);
        newStatus.click();

        wait.until(ExpectedConditions.visibilityOf(NewStatus));
        wait.until(ExpectedConditions.elementToBeClickable(NewStatus));
        highlightElement(driver,NewStatus);
        NewStatus.click();


        wait.until(ExpectedConditions.visibilityOf(DatePicker));
        DatePicker.click(); wait.until(ExpectedConditions.visibilityOf(DatePicker));
        DatePicker.click();

        String month= MonthYearBtn.getText().split(" ")[0];
        String year= MonthYearBtn.getText().split(" ")[1];

        String date= CurrDateBtn.getText()+" "+ month+" "+year;

        DateInput.sendKeys(date);


        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);

//        month= MonthYearBtn.getText().split(" ")[0];
//        year= MonthYearBtn.getText().split(" ")[1];/
//        date= CurrDateBtn.getText()+" "+ month+" "+year;
//
//
//        DateInput.sendKeys(date);

        sleep(1);

        wait.until(ExpectedConditions.visibilityOf(UploadDoc));
        wait.until(ExpectedConditions.elementToBeClickable(UploadDoc));
        highlightElement(driver,UploadDoc);

        WebElement uploadDocInput= UploadDoc.findElement(By.xpath(".//input"));
        uploadDocInput.sendKeys("/home/yash/Pictures/Screenshots/screenshot.png");

        wait.until(ExpectedConditions.visibilityOf(Form));
        highlightElement(driver,Form);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//div[@class=' css-1i8o47w']")));
        List<WebElement> formDropdownOptions=  Form.findElements(By.xpath(".//div[@class=' css-1i8o47w']"));


        WebElement issuingPartyDropdown= formDropdownOptions.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(issuingPartyDropdown));
        highlightElement(driver,issuingPartyDropdown);
        issuingPartyDropdown.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-issuingParty-option-1']")));
        WebElement issuingPartyValue= Form.findElement(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-issuingParty-option-1']"));
        wait.until(ExpectedConditions.elementToBeClickable(issuingPartyValue));
        highlightElement(driver,issuingPartyValue);
        issuingPartyValue.click();

        WebElement receivingParty= formDropdownOptions.get(1);
        wait.until(ExpectedConditions.elementToBeClickable(receivingParty));
        highlightElement(driver,receivingParty);
        receivingParty.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-receivingParty-option-0']")));
        WebElement receivingPartyValue= Form.findElement(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-receivingParty-option-0']"));
        wait.until(ExpectedConditions.elementToBeClickable(receivingPartyValue));
        highlightElement(driver,receivingPartyValue);
        receivingPartyValue.click();

        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();


        sleep(2);

        wait.until(ExpectedConditions.visibilityOf(UpdateStatus));
        wait.until(ExpectedConditions.elementToBeClickable(UpdateStatus));
        highlightElement(driver,UpdateStatus);
        UpdateStatus.click();

        wait.until(ExpectedConditions.visibilityOf(TrackingSection));
        highlightElement(driver,TrackingSection);

        sleep(2);
        updateStatusBtn= TrackingSection.findElement(By.xpath(".//span"));
        wait.until(ExpectedConditions.elementToBeClickable(updateStatusBtn));
        highlightElement(driver,updateStatusBtn);
        updateStatusBtn.click();


        wait.until(ExpectedConditions.visibilityOf(DatePicker));
        DatePicker.click();

        month= MonthYearBtn.getText().split(" ")[0];
        year= MonthYearBtn.getText().split(" ")[1];
        date= CurrDateBtn.getText()+" "+ month+" "+year;


        DateInput2.sendKeys(date);

        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXpath));
        newStatus= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(newStatus));
        highlightElement(driver,newStatus);
        newStatus.click();

        wait.until(ExpectedConditions.visibilityOf(NewStatus));
        wait.until(ExpectedConditions.elementToBeClickable(NewStatus));
        highlightElement(driver,NewStatus);
        NewStatus.click();

        wait.until(ExpectedConditions.visibilityOf(UpdateStatus));
        wait.until(ExpectedConditions.elementToBeClickable(UpdateStatus));
        highlightElement(driver,UpdateStatus);
        UpdateStatus.click();

        wait.until(ExpectedConditions.visibilityOf(TrackingSection));
        highlightElement(driver,TrackingSection);

        sleep(2);
        updateStatusBtn= TrackingSection.findElement(By.xpath(".//span"));
        wait.until(ExpectedConditions.elementToBeClickable(updateStatusBtn));
        highlightElement(driver,updateStatusBtn);
        updateStatusBtn.click();

        sleep(3);
        DateInput2.sendKeys(date);

        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXpath));
        newStatus= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(newStatus));
        highlightElement(driver,newStatus);
        newStatus.click();

        wait.until(ExpectedConditions.visibilityOf(NewStatus));
        wait.until(ExpectedConditions.elementToBeClickable(NewStatus));
        highlightElement(driver,NewStatus);
        NewStatus.click();

        sleep(2);
        wait.until(ExpectedConditions.visibilityOf(UpdateStatus));
        wait.until(ExpectedConditions.elementToBeClickable(UpdateStatus));
        highlightElement(driver,UpdateStatus);
        UpdateStatus.click();


        wait.until(ExpectedConditions.visibilityOfAllElements(ActionItem));
        WebElement markDeliveredBtn= ActionItem.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(markDeliveredBtn));
        highlightElement(driver,markDeliveredBtn);
        markDeliveredBtn.click();

        wait.until(ExpectedConditions.visibilityOf(AgreementCheckbox));
        AgreementCheckbox.click();

        String shipmentAmt= ShipmentAmt.getText();

        captchaInput.sendKeys(shipmentAmt);

        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();


        wait.until(ExpectedConditions.visibilityOfAllElements(LotContainer));
        List<WebElement> itemsList= LotContainer;

        for(WebElement item:itemsList)
        {
            highlightElement(driver,item);
            WebElement expectedQty= item.findElement(By.xpath(".//div[@class='col s12 m2']"));
            highlightElement(driver,expectedQty);
            String expectedQtyValue= expectedQty.getText();

            WebElement receivedQty= item.findElement(By.xpath(".//input[@id='tfid-0-0']"));
            receivedQty.sendKeys(expectedQtyValue);
        }

        sleep(4);

        wait.until(ExpectedConditions.visibilityOf(UploadDoc));
        highlightElement(driver,UploadDoc);

        WebElement uploadSlipInput= UploadDoc.findElement(By.xpath(".//input"));
        uploadSlipInput.sendKeys("/home/yash/Pictures/Screenshots/screenshot.png");

        wait.until(ExpectedConditions.visibilityOf(Form));
        highlightElement(driver,Form);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=' css-1i8o47w']")));
        formDropdownOptions=  Form.findElements(By.xpath(".//div[@class=' css-1i8o47w']"));


        issuingPartyDropdown= formDropdownOptions.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(issuingPartyDropdown));
        highlightElement(driver,issuingPartyDropdown);
        issuingPartyDropdown.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-issuingParty-option-0']")));
        issuingPartyValue= Form.findElement(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-issuingParty-option-0']"));
        wait.until(ExpectedConditions.elementToBeClickable(issuingPartyValue));
        highlightElement(driver,issuingPartyValue);
        issuingPartyValue.click();

        receivingParty= formDropdownOptions.get(1);
        wait.until(ExpectedConditions.elementToBeClickable(receivingParty));
        highlightElement(driver,receivingParty);
        receivingParty.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='reactSelectContainer']//div[@id='react-select-receivingParty-option-1']")));
        receivingPartyValue= Form.findElement(By.xpath(".//div[@class='reactSelectContainer']//div[@id='react-select-receivingParty-option-1']"));
        wait.until(ExpectedConditions.elementToBeClickable(receivingPartyValue));
        highlightElement(driver,receivingPartyValue);
        receivingPartyValue.click();

        highlightElement(driver,SaveBtn);
        SaveBtn.click();

        wait.until(ExpectedConditions.invisibilityOf(LoadingIcon));

        sleep(2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Next')]")));
        WebElement nextBtn= driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
        highlightElement(driver,nextBtn);
        nextBtn.click();

        sleep(2);
//        wait.until(ExpectedConditions.visibilityOf(SaveBtn));
//        wait.until(ExpectedConditions.elementToBeClickable(SaveBtn));
//        highlightElement(driver,SaveBtn);
//        SaveBtn.click();


        wait.until(ExpectedConditions.visibilityOf(DatePicker));
        DatePicker.click();

        month= MonthYearBtn.getText().split(" ")[0];
        year= MonthYearBtn.getText().split(" ")[1];

        date= CurrDateBtn.getText()+" "+ month+" "+year;

        DateInput.sendKeys(date);



        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();


        wait.until(ExpectedConditions.visibilityOf(ShareGRNBtn));
        wait.until(ExpectedConditions.elementToBeClickable(ShareGRNBtn));
        highlightElement(driver,ShareGRNBtn);
        ShareGRNBtn.click();

        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXpath));
        WebElement person_dropdown= MultipleUsedXpath;
        wait.until(ExpectedConditions.elementToBeClickable(person_dropdown));
        highlightElement(driver,person_dropdown);
        person_dropdown.click();

        wait.until(ExpectedConditions.visibilityOf(PersonHint));
        PersonHint.sendKeys("a");

        wait.until(ExpectedConditions.visibilityOf(PersonValue));
        wait.until(ExpectedConditions.elementToBeClickable(PersonValue));
        highlightElement(driver,PersonValue);
        PersonValue.click();

        wait.until(ExpectedConditions.visibilityOf(ShareBtn));
        wait.until(ExpectedConditions.elementToBeClickable(ShareBtn));
        highlightElement(driver,ShareBtn);
        ShareBtn.click();

        return this;
    }

    public DeliveryInDetailsPage viewInventory() throws InterruptedException, IOException {

        sleep(4);

        wait.until(ExpectedConditions.visibilityOfAllElements(ActionItem));
        WebElement viewInventoryBtn= ActionItem.get(3);
        wait.until(ExpectedConditions.elementToBeClickable(viewInventoryBtn));
        highlightElement(driver,viewInventoryBtn);
        viewInventoryBtn.click();

        for(String handle: driver.getWindowHandles())
            driver.switchTo().window(handle);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='lineItem-parentCard']")));
        List<WebElement> items= driver.findElements(By.xpath("//div[@class='lineItem-parentCard']"));

        for(WebElement item:items)
        {
            scrollToElement(item);
            highlightElement(driver,item);

            WebElement inStockQty= item.findElement(By.xpath(".//div[@class='key']"));
            highlightElement(driver,inStockQty);

            Assert.assertTrue(inStockQty.getText().equalsIgnoreCase("100"));
        }

        return this;
    }
}