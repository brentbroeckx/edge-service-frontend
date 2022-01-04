package project.edgeservicefrontend.actions;

import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import project.edgeservicefrontend.pages.CrudCarsPage;
import project.edgeservicefrontend.pages.HomePage;
import project.edgeservicefrontend.pages.ListCarsPage;

@Component
public class CarsCrud {

//    @Autowired
//    private HomePage homePage;
//
//    @Autowired
//    private ListCarsPage listCarsPage;
//
//    @Autowired
//    private CrudCarsPage crudCarsPage;

    @Value("${app.url}")
    private String appUrl;

//    @Autowired
//    private ChromeDriver chromeDriver;
//
//    public void PerformAddCar() {
//        chromeDriver.navigate().to(appUrl);
//
//        homePage.ClickListCars();
//        listCarsPage.ClickNewCar();
//        crudCarsPage.FillForm();
//    }
//
//    public void PerformEditCar() {
//        chromeDriver.navigate().to(appUrl + "/cars");
//        listCarsPage.ClickEdit();
//        crudCarsPage.ChangeForm();
//    }
//
//    public void DeleteSeleniumCar() {
//        chromeDriver.navigate().to(appUrl + "/cars");
//        listCarsPage.ClickDelete();
//    }

}
