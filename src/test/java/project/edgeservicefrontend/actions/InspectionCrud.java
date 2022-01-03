package project.edgeservicefrontend.actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import project.edgeservicefrontend.pages.CrudInspectionsPage;
import project.edgeservicefrontend.pages.HomePage;
import project.edgeservicefrontend.pages.ListInspectionsPage;

@Component
public class InspectionCrud {

    @Autowired
    private HomePage homePage;
    @Autowired
    private ListInspectionsPage listInspectionsPage;
    @Autowired
    private CrudInspectionsPage crudInspectionsPage;

    @Value("${app.url}")
    private String appUrl;

    @Autowired
    private ChromeDriver chromeDriver;


    public void PerformAddInspection() {
        chromeDriver.navigate().to(appUrl);

        homePage.ClickListInspections();
        listInspectionsPage.ClickNewInspection();
        crudInspectionsPage.FillForm();
    }

    public void PerformEditInspection() {
        chromeDriver.navigate().to(appUrl + "/inspections");

        listInspectionsPage.ClickEdit();
        crudInspectionsPage.ChangeForm();

    }

}
