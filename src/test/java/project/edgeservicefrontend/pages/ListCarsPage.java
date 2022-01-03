package project.edgeservicefrontend.pages;

import org.springframework.stereotype.Component;

@Component
public class ListCarsPage {

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
