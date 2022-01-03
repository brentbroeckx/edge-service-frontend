package project.edgeservicefrontend.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CrudCarsPage {

    @FindBy(how = How.ID, using = "licensePlate")
    public WebElement licensePlateInput;

    @FindBy(how = How.NAME, using = "merk")
    public WebElement merkInput;

    @FindBy(how = How.NAME, using = "type")
    public WebElement typeInput;

    @FindBy(how = How.NAME, using = "euroNorm")
    public WebElement euroNormInput;

    @FindBy(how = How.NAME, using = "portier")
    public WebElement portierSelect;

    @FindBy(how = How.ID, using = "Submit")
    public WebElement submitButton;

    @Autowired
    private ChromeDriver chromeDriver;

    @PostConstruct
    public void InitCrudCarsPage() {
        PageFactory.initElements(chromeDriver, this);
    }

    public void FillForm() {

        licensePlateInput.sendKeys("1-SELENIUM-7357");
        merkInput.sendKeys("SELENIUM");
        typeInput.sendKeys("TEST");
        euroNormInput.sendKeys("4");

        Select portier = new Select(portierSelect);
        portier.selectByIndex(1);

        submitButton.click();

    }

    public void ChangeForm() {
        merkInput.clear();
        merkInput.sendKeys("EDIT SELENIUM");
        typeInput.clear();
        typeInput.sendKeys("EDITING TEST");

        Select portier = new Select(portierSelect);
        portier.selectByIndex(0);

        submitButton.click();
    }

}
