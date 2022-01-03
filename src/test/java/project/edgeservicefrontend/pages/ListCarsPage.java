package project.edgeservicefrontend.pages;

import org.openqa.selenium.By;
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
public class ListCarsPage {

    @Autowired
    private ChromeDriver chromeDriver;

    @FindBy(how = How.LINK_TEXT, using = "New car")
    private WebElement linkNewCar;

    @FindBy(how = How.LINK_TEXT, using = "Edit")
    private WebElement linkEdit;

    @PostConstruct
    public void InitListCarsPage() {
        PageFactory.initElements(chromeDriver, this);
    }

    public void ClickNewCar() {
        linkNewCar.click();
        System.out.println("Clicked new car button");
    }

    public void ClickEdit() {
        List<WebElement> allvalue = chromeDriver.findElements(By.xpath("/html/body/table/tbody/tr"));
        String searchValue = "SELENIUM";
        Integer editIndex = 0;

        for(int i =0;i<allvalue.size();i++){
            if (allvalue.get(i).getText().contains(searchValue)) {
                editIndex = i + 1;
            }
        }
        WebElement editButton = chromeDriver.findElement(By.xpath("/html/body/table/tbody/tr[" + editIndex + "]/td[4]/form/a[1]"));
        editButton.click();

        System.out.println("Clicked edit for car");
    }

    public void ClickShowInspection() {
        System.out.println("Clicked show inspection for car");
    }

    public void ClickDelete() {
        List<WebElement> allvalue = chromeDriver.findElements(By.xpath("/html/body/table/tbody/tr"));
        String searchValue = "EDIT SELENIUM";
        Integer editIndex = 0;

        for(int i =0;i<allvalue.size();i++){
            if (allvalue.get(i).getText().contains(searchValue)) {
                editIndex = i + 1;
            }
        }
        WebElement deleteButton = chromeDriver.findElement(By.xpath("/html/body/table/tbody/tr[" + editIndex + "]/td[4]/form/button"));
        deleteButton.click();
        System.out.print("Clicked delete car");
    }

}
