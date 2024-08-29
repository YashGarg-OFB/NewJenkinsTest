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

public class BatchCreationPage extends BasePage {


    @FindBy(xpath = "//table[@class='customTable']//tbody")
    private WebElement Table;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    WebElement SubmitBtn;


    @FindBy(xpath = "//div[@class='cartBtn']")
    WebElement CartBtn;

    @FindBy(xpath = "//div[@class='modalWrapper']//button[@class='btn btn-primary']")
    WebElement GoToBatchBtn;


    public BatchCreationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public BatchCreationPage fillItemDetails() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(Table));
        highlightElement(driver,Table);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//tr")));
        List<WebElement> products= Table.findElements(By.xpath(".//tr"));


//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='customTable']//thead//th//label//input")));
//        WebElement selectAllItems= driver.findElement(By.xpath("//table[@class='customTable']//thead//th//label//input"));
//        wait.until(ExpectedConditions.elementToBeClickable(selectAllItems));
//        //selectAllItems.click();
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectAllItems);

        sleep(2);

        for(WebElement product:products)
        {
            List<WebElement> productOptions= product.findElements(By.xpath(".//td"));
            highlightElement(driver,productOptions.get(0));
            WebElement checkBox= productOptions.get(0).findElement(By.xpath(".//label//input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkBox);

            String pendingQty= productOptions.get(2).getText();

            WebElement qty_input= productOptions.get(4).findElement(By.xpath(".//input[@id='indSelect-batchLineItems']"));
            qty_input.sendKeys(pendingQty);
        }
        return this;
    }

    public BatchCreationPage clickOnSubmitBtn()
    {
        wait.until(ExpectedConditions.visibilityOf(SubmitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();
        return this;
    }

    public BatchCreationPage clickOnCartBtn()
    {
        wait.until(ExpectedConditions.visibilityOf(CartBtn));
        wait.until(ExpectedConditions.elementToBeClickable(CartBtn));
        highlightElement(driver,CartBtn);
        CartBtn.click();
        return this;
    }

    public BatchCreationPage clickOnGoToBatch()
    {
        wait.until(ExpectedConditions.visibilityOf(GoToBatchBtn));
        wait.until(ExpectedConditions.elementToBeClickable(GoToBatchBtn));
        highlightElement(driver,GoToBatchBtn);
        GoToBatchBtn.click();
        return this;
    }


    public BatchCreationPage selectItems() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(Table));
        highlightElement(driver,Table);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//tr")));
        List<WebElement> products= Table.findElements(By.xpath(".//tr"));


//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='customTable']//thead//th//label//input")));
//        WebElement selectAllItems= driver.findElement(By.xpath("//table[@class='customTable']//thead//th//label//input"));
//        wait.until(ExpectedConditions.elementToBeClickable(selectAllItems));
//        //selectAllItems.click();
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectAllItems);

        sleep(2);

        for(WebElement product:products)
        {
            List<WebElement> productOptions= product.findElements(By.xpath(".//td"));
            highlightElement(driver,productOptions.get(0));
            WebElement checkBox= productOptions.get(0).findElement(By.xpath(".//label//input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkBox);

            String pendingQty= productOptions.get(2).getText();

            WebElement qty_input= productOptions.get(4).findElement(By.xpath(".//input[@id='indSelect-batchLineItems']"));
            qty_input.sendKeys(pendingQty);
        }

        wait.until(ExpectedConditions.visibilityOf(SubmitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();

        wait.until(ExpectedConditions.visibilityOf(CartBtn));
        wait.until(ExpectedConditions.elementToBeClickable(CartBtn));
        highlightElement(driver,CartBtn);
        CartBtn.click();

        wait.until(ExpectedConditions.visibilityOf(SubmitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
        highlightElement(driver,SubmitBtn);
        SubmitBtn.click();

        wait.until(ExpectedConditions.visibilityOf(GoToBatchBtn));
        wait.until(ExpectedConditions.elementToBeClickable(GoToBatchBtn));
        highlightElement(driver,GoToBatchBtn);
        GoToBatchBtn.click();

        return this;
    }
}
