package project.edgeservicefrontend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import project.edgeservicefrontend.actions.CarsCrud;
import project.edgeservicefrontend.actions.InspectionCrud;
import project.edgeservicefrontend.pages.CrudInspectionsPage;
import project.edgeservicefrontend.pages.HomePage;
import project.edgeservicefrontend.pages.ListCarsPage;
import project.edgeservicefrontend.pages.ListInspectionsPage;

@SpringBootTest
class EdgeServiceFrontendApplicationTests {

    @Autowired
    private InspectionCrud inspectionCrud;

    @Autowired
    private CarsCrud carsCrud;

    @Value("${app.url}")
    private String appUrl;

    @Test
    void performFrontEndTests() {

        inspectionCrud.PerformAddInspection();
        inspectionCrud.PerformEditInspection();
        inspectionCrud.DeleteSeleniumInspection();
        carsCrud.PerformAddCar();
        carsCrud.PerformEditCar();
        carsCrud.DeleteSeleniumCar();



    }


}
