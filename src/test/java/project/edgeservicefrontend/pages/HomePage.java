package project.edgeservicefrontend.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component
public class HomePage {

    @FindBy(how = How.LINK_TEXT, using = "List Cars")
    public WebElement linkListCars;

    @FindBy(how = How.LINK_TEXT, using = "List Inspections")
    public WebElement linkListInspections;

    public void ClickListCars() {
//        linkListCars.click();
        System.out.println("Clicked the list cars link");
    }

    public void ClickListInspections() {
//        linkListInspections.click();
        System.out.println("Clicked the list inspections link");

    }

}
