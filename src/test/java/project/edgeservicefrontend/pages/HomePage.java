package project.edgeservicefrontend.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HomePage {



    @FindBy(how = How.LINK_TEXT, using = "List cars")
    public WebElement linkListCars;

    @FindBy(how = How.LINK_TEXT, using = "List inspections")
    public WebElement linkListInspections;

//    @Autowired
//    private WebDriver chromeDriver;

//    @PostConstruct
//    public void InitHomePage() {
//        PageFactory.initElements(chromeDriver, this);
//    }
//
//    public void ClickListCars() {
//        linkListCars.click();
//        System.out.println("Clicked the list cars link");
//    }
//
//    public void ClickListInspections() {
//        linkListInspections.click();
//        System.out.println("Clicked the list inspections link");
//
//    }

}
