package VirtualWarehouse;

import base.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public class WelcomePage extends BasePage {

    @FindBy(xpath = "//ul[@class='navBar']")
    private WebElement Drawer;

    @FindBy(xpath = "//span[text()='Bulk Order']")
    private WebElement DrawerOptions;

    @FindBy(xpath = "//a[text()='Bulk Order Entity']")
    private WebElement BulkOrderEntity;

    public WelcomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public WelcomePage checkDrawer(){

        System.out.println("Checking Drawer");

        wait.until(ExpectedConditions.visibilityOf(Drawer));
        //highlightElement(driver, Drawer);

        System.out.println("Drawer Checked");

        wait.until(ExpectedConditions.visibilityOf(DrawerOptions));
        highlightElement(driver,DrawerOptions);
        DrawerOptions.click();
        wait.until(ExpectedConditions.visibilityOf(BulkOrderEntity));
        highlightElement(driver, BulkOrderEntity);
        wait.until(ExpectedConditions.elementToBeClickable(BulkOrderEntity));
        BulkOrderEntity.click();

        System.out.println("Bulk Order Clicked");
        return this;
    }
}

