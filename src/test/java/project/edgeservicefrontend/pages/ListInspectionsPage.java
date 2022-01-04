package project.edgeservicefrontend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.edgeservicefrontend.libraries.WebDriverLibrary;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ListInspectionsPage {

    @Autowired
    private WebDriver chromeDriver;

    @FindBy(how = How.LINK_TEXT, using = "New Inspection")
    public WebElement linkNewInspection;

    @FindBy(how = How.LINK_TEXT, using = "Edit")
    public WebElement linkEdit;

    @FindBy(how = How.LINK_TEXT, using = "Show Car")
    public WebElement linkShowCar;


    @PostConstruct
    public void InitListInspectionsPage() {
        PageFactory.initElements(chromeDriver, this);
    }

    public void ClickNewInspection() {
        linkNewInspection.click();
        System.out.println("Clicked new inspection button");
    }

    public void ClickEdit() {

        List<WebElement> allvalue = chromeDriver.findElements(By.xpath("/html/body/table/tbody/tr"));
        String searchValue = "Selenium Testing";
        Integer editIndex = 0;

        for(int i =0;i<allvalue.size();i++){
            if (allvalue.get(i).getText().contains(searchValue)) {
                editIndex = i + 1;
            }
        }

        WebElement editButton = chromeDriver.findElement(By.xpath("/html/body/table/tbody/tr[" + editIndex + "]/td[4]/form/a[1]"));
        editButton.click();

        System.out.println("Clicked edit for inspection");
    }

    public void ClickShowCar() {
        linkShowCar.click();
        System.out.println("Clicked show car for inspection");
    }

    public void ClickDelete() {
        List<WebElement> allvalue = chromeDriver.findElements(By.xpath("/html/body/table/tbody/tr"));
        String searchValue = "Edit Selenium testing";
        Integer editIndex = 0;

        for(int i =0;i<allvalue.size();i++){
            if (allvalue.get(i).getText().contains(searchValue)) {
                editIndex = i + 1;
            }
        }
        WebElement deleteButton = chromeDriver.findElement(By.xpath("/html/body/table/tbody/tr[" + editIndex + "]/td[4]/form/button"));
        deleteButton.click();

        System.out.print("Clicked delete inspection");
    }

}
