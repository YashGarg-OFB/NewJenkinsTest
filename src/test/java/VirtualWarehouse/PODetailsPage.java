package VirtualWarehouse;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PODetailsPage extends BasePage {

    @FindBy(xpath = "//div[@class='actionsWrapper']")
    WebElement ActionWrapper;

    @FindBy(xpath = "//div//span[text()='Create Batch']")
    WebElement CreateBatchBtn;


    public PODetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }


    public PODetailsPage createbatch()
    {

        wait.until(ExpectedConditions.visibilityOf(ActionWrapper));
        highlightElement(driver,ActionWrapper);

        wait.until(ExpectedConditions.elementToBeClickable(CreateBatchBtn));
        highlightElement(driver,CreateBatchBtn);
        CreateBatchBtn.click();

        return this;
    }

}
