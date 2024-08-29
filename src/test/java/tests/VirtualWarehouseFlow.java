package tests;

import VirtualWarehouse.*;
import VirtualWarehouse.WelcomePage;
import base.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static utils.Helper.loadPropFile;
import static utils.Helper.prop;

public class VirtualWarehouseFlow extends BaseTest {

    @Test
    public void goOnWelcomePage() throws IOException {

        driver.get(baseUrl);
        loadPropFile();
        WelcomePage welcomePage= new WelcomePage(driver);
        welcomePage.checkDrawer();
    }

    @Test(dependsOnMethods = "goOnWelcomePage")
    public void goOnBulkOrderListingPage() {
        BulkOrderEntitiesListingPage bulkOrderEntitiesListingPage= new BulkOrderEntitiesListingPage(driver);
        bulkOrderEntitiesListingPage.selectEntity();
    }

    @Test(dependsOnMethods = "goOnBulkOrderListingPage")
    public void goOnBulkOrderDetailsPage() throws InterruptedException {
        BulkOrderEntityDetailsPage bulkOrderEntityDetailsPage= new BulkOrderEntityDetailsPage(driver);
        bulkOrderEntityDetailsPage
                .clickOnPOBtn()
                .selectSupplier()
                .selectBillingAddress()
                .selectPickupAddress()
                .clickOnRadioBtn()
                .clickOnAddItemsBtn();
    }

    @Test(dependsOnMethods = "goOnBulkOrderDetailsPage")
    public void goOnAddItemsPage() throws InterruptedException, IOException {
        AddItemsPage addItemsPage= new AddItemsPage(driver);
        addItemsPage
                .clickOnItem()
                .selectItems()
                .viewCart()
                .fillItemDetails()
                .clickOnSubmitButton()
                .fillRateDetails()
                .selectOfbAddress()
                .sendDate()
                .clickOnCheckBox()
                .clickOnEditItem()
                .sendHSNCode()
                .clickOnSubmitButton()
                .clickOnSaveBtn()
                .selectVerifier()
                .sendForVerification()
                .verifyPO();
    }

    @Test(dependsOnMethods = "goOnAddItemsPage")
    public void VerifyPO() throws IOException, InterruptedException {
        POVerificationPage poVerificationPage= new POVerificationPage(driver);
        poVerificationPage
                .clickOnApproveBtn()
                .clickOnSubmitBtn()
                .clickOnBulkOrder()
                .clickOnPurchaseOrder();

    }

    @Test(dependsOnMethods = "VerifyPO")
    public void goOnPOListingPage() throws InterruptedException {
        POListingPage poListingPage= new POListingPage(driver);
        poListingPage.searchPO();
    }

    @Test(dependsOnMethods = "goOnPOListingPage")
    public void goOnPODetailsPage() {
        PODetailsPage poDetailPage= new PODetailsPage(driver);
        poDetailPage.createbatch();
    }

    @Test(dependsOnMethods = "goOnPODetailsPage")
    public void goOnBatchCreationPage() throws InterruptedException {
        BatchCreationPage batchCreationPage= new BatchCreationPage(driver);
        batchCreationPage
                .fillItemDetails()
                .clickOnSubmitBtn()
                .clickOnCartBtn()
                .clickOnSubmitBtn()
                .clickOnGoToBatch();
    }

    @Test(dependsOnMethods = "goOnBatchCreationPage")
    public void goOnBatchDetailsPage() throws InterruptedException, IOException {
        BatchDetailsPage batchDetailsPage= new BatchDetailsPage(driver);
        batchDetailsPage
                .sendDoc(prop.getProperty("file-path"))
                .sendInvoiceDate()
                .sendInvoiceNumber()
                .clickOnRedirectBlock()
                .fillItemDetails()
                .clickOnSubmitBtn()
                .clickOnSubmitBtn()
                .clickOnMoveToWarehouse()
                .selectAddress()
                .selectFulfillmentSpoc()
                .sendVehicleInput()
                .clickOnSubmitBtn()
                .clickOnSubmitBtn()
                .clickOnCompleteDeliveryIn()
                .clickOnSubmitBtn()
                .switchToDeliveryIn()
                .selectLsp()
                .selectGst()
                .selectOfbEntity()
                .clickOnSubmitBtn()
                .sendPricePerUnit()
                .clickOnSubmitBtn()
                .sendDate()
                .selectWarehouseSpoc()
                .clickOnSubmitBtn();

    }

    @Test(dependsOnMethods = "goOnBatchDetailsPage")
    public void goOnDeliveryInDetailsPage() throws InterruptedException, IOException {

        DeliveryInDetailsPage deliveryInDetailsPage= new DeliveryInDetailsPage(driver);
        deliveryInDetailsPage
                .clickOnUpdateStatus()
                .selectNewStatus()
                .sendDate()
                .uploadFile(prop.getProperty("file-path"))
                .fillFormDetails()
                .clickOnSubmit()
                .clickOnUpdateStatusBtn()
                .clickOnUpdateStatus()
                .sendDate2()
                .selectNewStatus()
                .clickOnUpdateStatusBtn()
                .clickOnMarkDelivered()
                .clickOnCheckbox()
                .fillCaptcha()
                .clickOnSubmit()
                .fillItemDetails()
                .uploadFile(prop.getProperty("file-path"))
                .fillForm()
                .clickOnSaveBtn()
                .clickOnNextBtn()
                .sendDate()
                .clickOnSubmit()
                .clickOnShareGRN()
                .selectPersonName()
                .clickOnShareBtn()
                .viewInventory();
    }
}
