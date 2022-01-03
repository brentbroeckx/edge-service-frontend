package project.edgeservicefrontend.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component
public class ListInspectionsPage {

    @FindBy(how = How.LINK_TEXT, using = "New Inspection")
    public WebElement linkNewInspection;

    @FindBy(how = How.LINK_TEXT, using = "Edit")
    public WebElement linkEdit;

    @FindBy(how = How.LINK_TEXT, using = "Show Car")
    public WebElement linkShowCar;

    @FindBy(how = How.LINK_TEXT, using = "Delete Inspection")
    public WebElement linkDeleteInspection;

    public void ClickNewInspection() {
//        linkNewInspection.click();
        System.out.println("Clicked new inspection button");
    }

    public void ClickEdit() {
//        linkEdit.click();
        System.out.println("Clicked edit for inspection");
    }

    public void ClickShowCar() {
//        linkShowCar.click();
        System.out.println("Clicked show car for inspection");
    }

    public void ClickDelete() {
//        linkDeleteInspection.click();
        System.out.print("Clicked delete inspection");
    }

}
