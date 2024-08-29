package VirtualWarehouse;

import base.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Slf4j
public class BulkOrderEntitiesListingPage extends BasePage {

    @FindBy(xpath = "//div[@class='listItemRow']")
    private List<WebElement> Entities;

    public BulkOrderEntitiesListingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public BulkOrderEntitiesListingPage selectEntity(){
        wait.until(ExpectedConditions.visibilityOfAllElements(Entities));

        WebElement entity= Entities.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(entity));
        highlightElement(driver,entity);
        entity.click();

        System.out.println("Entity Selected");
        return this;
    }
}
