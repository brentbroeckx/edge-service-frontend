package project.edgeservicefrontend.pages;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ListCarsPage {

    @Autowired
    private ChromeDriver chromeDriver;

    @PostConstruct
    public void InitListCarsPage() {
        PageFactory.initElements(chromeDriver, this);
    }

    public void ClickNewCar() {
        System.out.println("Clicked new car button");
    }

    public void ClickEdit() {
        System.out.println("Clicked edit for car");
    }

    public void ClickShowInspection() {
        System.out.println("Clicked show inspection for car");
    }

    public void ClickDelete() {
        System.out.print("Clicked delete car");
    }

}
