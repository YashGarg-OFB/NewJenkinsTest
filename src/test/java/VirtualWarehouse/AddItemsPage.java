package VirtualWarehouse;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;

import static utils.Helper.*;


public class AddItemsPage extends BasePage {

    @FindBy(xpath = "//div[@class='baseCatWrapper']")
    private WebElement ItemSection;

    @FindBy(xpath = "//div[@class='catItem']")
    private List<WebElement> Items;

    @FindBy(xpath = ".//button[@class='btn btn-default']")
    private WebElement CartBtn;

    @FindBy(xpath = "//div[@class='cartBtn']")
    WebElement ViewAddedItems;

    @FindBy(xpath="//div[@class='leafCatWrapper']")
    private WebElement VarietySection;

    @FindBy(className = "subCategoryForm")
    private List<WebElement> SubcategoryForms;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private List<WebElement> SubmitBtn;

    @FindBy(xpath = "//div[text()='Other Configurations']")
    WebElement ScrollToElement;

    @FindBy(xpath = "//input[@id='tfid-0-6']")
    private WebElement AdvanceInput;

    @FindBy(xpath = "//input[@id='tfid-0-7']")
    WebElement OnDispatchInput;

    @FindBy(xpath = "//input[@id='tfid-0-8']")
    private WebElement OnDeliveryInput;

    @FindBy(xpath = "//input[@id='tfid-0-9']")
    WebElement CreditInput;

    @FindBy(xpath = "//div[@class=' css-1i8o47w']")
    WebElement MultipleUsedXPath;

    @FindBy(xpath = "//div[@class=' css-1ul4jvx-option']")
    List<WebElement> OFB_ADDRESS_VALUE;

    @FindBy(xpath = "//span[@class='checkboxCustom']")
    WebElement CheckBox;

    @FindBy(xpath = "//div[@class='lineItemWrapper']")
    List<WebElement> ItemsList;

    @FindBy(xpath = "//input[@id='verifierName-input-tfid-0-0']")
    WebElement Verifier;

    @FindBy(xpath = "//div[@id='react-select-verifierName-option-0']")
    WebElement VerifierName;

    @FindBy(xpath = "//button[text()='Send for Verification ']")
    WebElement SendForVerificationBtn;

    @FindBy(xpath = "//span[@class='backTxt']//span")
    WebElement PO_Element;

    @FindBy(xpath = "//ul[@class='nav-list']")
    List<WebElement> NavItems;

    @FindBy(xpath = ".//ul[@class='inner-nav-list']//li")
    WebElement SupplierPO;

    @FindBy(xpath = "//input[@class='form-control searchTxtField']")
    WebElement SearchInput;

    public static final By QTY_BASERATE= By.xpath(".//input[@class='form-control']");
    public static final By UNITS= By.xpath(".//div[@class='reactSelectContainer']");
    public static final By UNIT_VALUE= By.xpath(".//div[@id='react-select-Unit-option-0']");
    public static final By GST_DROPDOWN= By.xpath(".//div[@class='form-group form-group-depth-1 form-group-GST']//div[@class=' css-2b097c-container']");
    public static final By GST_VAL= By.xpath(".//div[@id='react-select-GST-option-0']");

    @FindBy(xpath = "//input[@placeholder='Start Date']")
    WebElement DATEINPUT;

    @FindBy(className = "react-datepicker__input-container")
    WebElement DATEPICKER;

    @FindBy(css = "div.react-datepicker__current-month.react-datepicker__current-month--hasYearDropdown.react-datepicker__current-month--hasMonthDropdown")
    WebElement MONTH_YEAR_BTN;

    @FindBy(xpath = "//div[contains(@class, 'react-datepicker__day--today')]")
    WebElement CURRDATE;

    public AddItemsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public AddItemsPage clickOnItem()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add New Products ']")));
        WebElement addNewProductsBtn= driver.findElement(By.xpath("//button[text()='Add New Products ']"));
        highlightElement(driver,addNewProductsBtn);
        addNewProductsBtn.click();

        System.out.println("Add New Products Button Clicked");

        wait.until(ExpectedConditions.visibilityOf(ItemSection));

        highlightElement(driver,Items.get(1));

        WebElement item1= Items.get(1).findElement(By.xpath(".//button[@class='btn btn-default']"));
        wait.until(ExpectedConditions.elementToBeClickable(item1));
        highlightElement(driver,item1);
        item1.click();

        System.out.println("Item Clicked");
        return this;
    }

    public AddItemsPage selectItems()
    {
        wait.until(ExpectedConditions.visibilityOf(VarietySection));
        highlightElement(driver,VarietySection);


        for(int i=1;i<=2;i++)
        {
            WebElement variety1= Items.get(i);
            highlightElement(driver,variety1);
            WebElement addToCartBtn= variety1.findElement(By.xpath(".//button[@class='btn btn-default']"));
            wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
            highlightElement(driver,addToCartBtn);
            addToCartBtn.click();
        }
        return this;
    }

    public AddItemsPage viewCart()
    {
        wait.until(ExpectedConditions.visibilityOf(ViewAddedItems));
        highlightElement(driver,ViewAddedItems);
        wait.until(ExpectedConditions.elementToBeClickable(ViewAddedItems));
        ViewAddedItems.click();
        return this;
    }

    public AddItemsPage fillItemDetails() throws InterruptedException {
        sleep(4);

        for(WebElement subcategoryForm:SubcategoryForms)
        {
            WebElement itemQty= subcategoryForm.findElements(QTY_BASERATE).get(0);
            itemQty.clear();
            itemQty.sendKeys("100");

            WebElement units= subcategoryForm.findElements(UNITS).get(0);

            scrollToElement(units);

            highlightElement(driver,units);
            units.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(UNIT_VALUE));
            WebElement unitValue= subcategoryForm.findElement(UNIT_VALUE);
            wait.until(ExpectedConditions.elementToBeClickable(unitValue));
            highlightElement(driver,unitValue);
            unitValue.click();


            WebElement baserate= subcategoryForm.findElement(By.xpath(".//input[@name='Base Rate']"));
            baserate.sendKeys("100");

            WebElement gstDropDown= subcategoryForm.findElement(GST_DROPDOWN);
            highlightElement(driver,gstDropDown);
            gstDropDown.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(GST_VAL));
            WebElement gstValue= subcategoryForm.findElement(GST_VAL);
            wait.until(ExpectedConditions.elementToBeClickable(gstValue));
            highlightElement(driver,gstValue);
            gstValue.click();




//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Gross Value']")));
//            WebElement grossValueElement= subcategoryForm.findElement(By.xpath(".//input[@name='Gross Value']"));

//            int grossValue= Integer.parseInt(grossValueElement.getAttribute("value"));
//            int totalVal= Integer.parseInt(baserate.getText())*Integer.parseInt(itemQty.getAttribute("value"));
//            int actualGrossValue= totalVal+ totalVal*(Integer.parseInt(gstValue.getAttribute("value").substring(0,gstValue.getAttribute("value").length()-1)));
//            System.out.println("GrossValue element text "+ grossValue);
//            System.out.println("Actual Computed "+ actualGrossValue);
//            Assert.assertEquals(grossValue,actualGrossValue);
        }
        return this;
    }

    public AddItemsPage clickOnSubmitButton() throws InterruptedException {
        sleep(3);

        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn.get(0)));
        highlightElement(driver,SubmitBtn.get(0));
        SubmitBtn.get(0).click();
        return this;
    }

    public AddItemsPage fillRateDetails() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(ScrollToElement));
        scrollToElement(ScrollToElement);
        AdvanceInput.clear();
        AdvanceInput.sendKeys("100");
        OnDispatchInput.clear();
        OnDispatchInput.sendKeys("0");
        OnDeliveryInput.clear();
        OnDeliveryInput.sendKeys("0");
        CreditInput.clear();
        CreditInput.sendKeys("0");
        return this;
    }

    public AddItemsPage selectOfbAddress() throws InterruptedException {
        scrollToElement(ScrollToElement);

        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXPath));
        wait.until(ExpectedConditions.elementToBeClickable(MultipleUsedXPath));
        highlightElement(driver,MultipleUsedXPath);
        scrollToElement(MultipleUsedXPath);
        MultipleUsedXPath.click();

        sleep(2);
        for(WebElement entityAddress:OFB_ADDRESS_VALUE)
        {
            scrollToElement(entityAddress);
            WebElement isActiveBadge= entityAddress.findElement(By.xpath(".//span[@class='badgeContainer ']"));
            highlightElement(driver,isActiveBadge);
            if(isActiveBadge.getText().equalsIgnoreCase("Active")) {
                entityAddress.click();
                break;
            }
        }
        return this;
    }

    public AddItemsPage sendDate() throws InterruptedException {
        sleep(2);

        DATEPICKER.click();

        String month= MONTH_YEAR_BTN.getText().split(" ")[0];
        String year= MONTH_YEAR_BTN.getText().split(" ")[1];
        currDate= CURRDATE.getText()+" "+ month+" "+year;

        DATEINPUT.sendKeys(currDate);
        return this;
    }

    public AddItemsPage clickOnCheckBox() throws InterruptedException {
        scrollToElement(driver.findElement(By.xpath("//div[@class='cardHeading']")));
        sleep(2);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", CheckBox);
        return this;
    }

    public AddItemsPage clickOnEditItem()
    {
        WebElement editItems= SubmitBtn.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(editItems));
        highlightElement(driver,editItems);
        editItems.click();
        return this;
    }

    public AddItemsPage sendHSNCode() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfAllElements(ItemsList));

        for(WebElement item:ItemsList)
        {
            WebElement hsnCodeInput= item.findElement(By.xpath(".//input[@placeholder='Enter HSN Code']"));
            hsnCodeInput.clear();
            hsnCodeInput.sendKeys("12345");
        }
        return this;
    }

    public AddItemsPage clickOnSaveBtn() throws InterruptedException {
        sleep(3);
        WebElement submit= SubmitBtn.get(1);
        wait.until(ExpectedConditions.elementToBeClickable(submit));
        highlightElement(driver,submit);
        submit.click();
        return this;
    }

    public AddItemsPage selectVerifier()
    {
        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXPath));
        wait.until(ExpectedConditions.elementToBeClickable(MultipleUsedXPath));
        highlightElement(driver,MultipleUsedXPath);
        MultipleUsedXPath.click();

        wait.until(ExpectedConditions.visibilityOf(Verifier));
        Verifier.sendKeys("Anshul Jain");

        wait.until(ExpectedConditions.visibilityOf(VerifierName));
        wait.until(ExpectedConditions.elementToBeClickable(VerifierName));
        highlightElement(driver,VerifierName);
        VerifierName.click();
        return this;
    }

    public AddItemsPage sendForVerification()
    {
        wait.until(ExpectedConditions.visibilityOf(SendForVerificationBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SendForVerificationBtn));
        highlightElement(driver,SendForVerificationBtn);
        SendForVerificationBtn.click();

        wait.until(ExpectedConditions.visibilityOf(PO_Element));
        poNumber= PO_Element.getText();
        return this;
    }

    public AddItemsPage addItems() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(ItemSection));

        highlightElement(driver,Items.get(1));

        WebElement item1= Items.get(1).findElement(By.xpath(".//button[@class='btn btn-default']"));
        wait.until(ExpectedConditions.elementToBeClickable(item1));
        highlightElement(driver,item1);
        item1.click();

        wait.until(ExpectedConditions.visibilityOf(VarietySection));
        highlightElement(driver,VarietySection);


        for(int i=1;i<=2;i++)
        {
            WebElement variety1= Items.get(i);
            highlightElement(driver,variety1);
            WebElement addToCartBtn= variety1.findElement(By.xpath(".//button[@class='btn btn-default']"));
            wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
            highlightElement(driver,addToCartBtn);
            addToCartBtn.click();
        }

        wait.until(ExpectedConditions.visibilityOf(ViewAddedItems));
        highlightElement(driver,ViewAddedItems);
        wait.until(ExpectedConditions.elementToBeClickable(ViewAddedItems));
        ViewAddedItems.click();

        sleep(4);

        for(WebElement subcategoryForm:SubcategoryForms)
        {
            WebElement itemQty= subcategoryForm.findElements(QTY_BASERATE).get(0);
            itemQty.clear();
            itemQty.sendKeys("100");

            WebElement units= subcategoryForm.findElements(UNITS).get(0);

            scrollToElement(units);

            highlightElement(driver,units);
            units.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(UNIT_VALUE));
            WebElement unitValue= subcategoryForm.findElement(UNIT_VALUE);
            wait.until(ExpectedConditions.elementToBeClickable(unitValue));
            highlightElement(driver,unitValue);
            unitValue.click();

//            WebElement baserate= subcategoryForm.findElements(QTY_BASERATE).get(1);
//            baserate.sendKeys("100");

            WebElement baserate= subcategoryForm.findElement(By.xpath(".//input[@name='Base Rate']"));
            baserate.sendKeys("100");

            WebElement gstDropDown= subcategoryForm.findElement(GST_DROPDOWN);
            highlightElement(driver,gstDropDown);
            gstDropDown.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(GST_VAL));
            WebElement gstValue= subcategoryForm.findElement(GST_VAL);
            wait.until(ExpectedConditions.elementToBeClickable(gstValue));
            highlightElement(driver,gstValue);
            gstValue.click();

//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Gross Value']")));
//            WebElement grossValueElement= subcategoryForm.findElement(By.xpath(".//input[@name='Gross Value']"));

//            int grossValue= Integer.parseInt(grossValueElement.getAttribute("value"));
//            int totalVal= Integer.parseInt(baserate.getText())*Integer.parseInt(itemQty.getAttribute("value"));
//            int actualGrossValue= totalVal+ totalVal*(Integer.parseInt(gstValue.getAttribute("value").substring(0,gstValue.getAttribute("value").length()-1)));
//            System.out.println("GrossValue element text "+ grossValue);
//            System.out.println("Actual Computed "+ actualGrossValue);
//            Assert.assertEquals(grossValue,actualGrossValue);
        }

        sleep(3);

        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn.get(0)));
        highlightElement(driver,SubmitBtn.get(0));
        SubmitBtn.get(0).click();


        wait.until(ExpectedConditions.visibilityOf(ScrollToElement));
        scrollToElement(ScrollToElement);

        AdvanceInput.sendKeys("100");

        OnDispatchInput.sendKeys("0");

        OnDeliveryInput.sendKeys("0");

        CreditInput.sendKeys("0");

        scrollToElement(ScrollToElement);


        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXPath));
        wait.until(ExpectedConditions.elementToBeClickable(MultipleUsedXPath));
        highlightElement(driver,MultipleUsedXPath);
        scrollToElement(MultipleUsedXPath);
        MultipleUsedXPath.click();

        sleep(2);
        for(WebElement entityAddress:OFB_ADDRESS_VALUE)
        {
            scrollToElement(entityAddress);
            WebElement isActiveBadge= entityAddress.findElement(By.xpath(".//span[@class='badgeContainer ']"));
            highlightElement(driver,isActiveBadge);
            if(isActiveBadge.getText().equalsIgnoreCase("Active")) {
                entityAddress.click();
                break;
            }
        }

        sleep(2);

        DATEPICKER.click();

        String month= MONTH_YEAR_BTN.getText().split(" ")[0];
        String year= MONTH_YEAR_BTN.getText().split(" ")[1];
        currDate= CURRDATE.getText()+" "+ month+" "+year;

        DATEINPUT.sendKeys(currDate);

        scrollToElement(driver.findElement(By.xpath("//div[@class='cardHeading']")));
        sleep(2);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", CheckBox);

        WebElement editItems= SubmitBtn.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(editItems));
        highlightElement(driver,editItems);
        editItems.click();

        wait.until(ExpectedConditions.visibilityOfAllElements(ItemsList));

        for(WebElement item:ItemsList)
        {
            WebElement hsnCodeInput= item.findElement(By.xpath(".//input[@placeholder='Enter HSN Code']"));
            hsnCodeInput.clear();
            hsnCodeInput.sendKeys("12345");
        }

        WebElement saveBtn= SubmitBtn.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));
        highlightElement(driver,saveBtn);
        saveBtn.click();

        sleep(3);
        WebElement submit= SubmitBtn.get(1);
        wait.until(ExpectedConditions.elementToBeClickable(submit));
        highlightElement(driver,submit);
        submit.click();


        wait.until(ExpectedConditions.visibilityOf(MultipleUsedXPath));
        wait.until(ExpectedConditions.elementToBeClickable(MultipleUsedXPath));
        highlightElement(driver,MultipleUsedXPath);
        MultipleUsedXPath.click();

        wait.until(ExpectedConditions.visibilityOf(Verifier));
        Verifier.sendKeys("Anshul Jain");

        wait.until(ExpectedConditions.visibilityOf(VerifierName));
        wait.until(ExpectedConditions.elementToBeClickable(VerifierName));
        highlightElement(driver,VerifierName);
        VerifierName.click();

        wait.until(ExpectedConditions.visibilityOf(SendForVerificationBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SendForVerificationBtn));
        highlightElement(driver,SendForVerificationBtn);
        SendForVerificationBtn.click();

        wait.until(ExpectedConditions.visibilityOf(PO_Element));
        poNumber= PO_Element.getText();

        return this;
    }

    public AddItemsPage verifyPO() throws InterruptedException, IOException {

        loadPropFile();

        prevToken= changeTokenCookie(driver,prop.getProperty("supplier-auth-token"));
        //addCookie(driver,prop.getProperty("supplier-auth-token"));

        wait.until(ExpectedConditions.visibilityOfAllElements(NavItems));
        WebElement verifySection= NavItems.get(8);
        highlightElement(driver,verifySection);
        verifySection.click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//ul[@class='inner-nav-list']//li")));
        WebElement supplierPOBtn= verifySection.findElement(By.xpath(".//ul[@class='inner-nav-list']//li"));
        wait.until(ExpectedConditions.elementToBeClickable(supplierPOBtn));
        highlightElement(driver,supplierPOBtn);
        supplierPOBtn.click();

        wait.until(ExpectedConditions.visibilityOf(SearchInput));
        SearchInput.sendKeys(poNumber);

        System.out.println("PO Number is "+ poNumber);

        sleep(3);
        SearchInput.sendKeys(Keys.ENTER);

        return this;
    }
}
