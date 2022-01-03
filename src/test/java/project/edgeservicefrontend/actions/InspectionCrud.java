package project.edgeservicefrontend.actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
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

//    @Autowired
//    private ChromeDriver chromeDriver;



//    public InspectionCrud(HomePage homePage, ListInspectionsPage listInspectionsPage, CrudInspectionsPage crudInspectionsPage) {
//        this.homePage = homePage;
//        this.listInspectionsPage = listInspectionsPage;
//        this.crudInspectionsPage = crudInspectionsPage;
//    }

    public void PerformAddInspection() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        WebDriver chromeDriver = new ChromeDriver();

        chromeDriver.navigate().to(appUrl);

        homePage.ClickListInspections();
        listInspectionsPage.ClickNewInspection();
        crudInspectionsPage.FillForm();
    }
}
