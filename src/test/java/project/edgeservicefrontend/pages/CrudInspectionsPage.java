package project.edgeservicefrontend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import project.edgeservicefrontend.libraries.WebDriverLibrary;

import javax.annotation.PostConstruct;

@Component
public class CrudInspectionsPage {

    @FindBy(how = How.NAME, using = "licensePlate")
    public WebElement licensePlateSelect;

    @FindBy(how = How.NAME, using = "comment")
    public WebElement commentInput;

    @FindBy(how = How.ID, using = "radioTrue")
    public WebElement passedRadioTrueInput;

    @FindBy(how = How.ID, using = "Submit")
    public WebElement submitButton;

    @Autowired
    private ChromeDriver chromeDriver;

    @PostConstruct
    public void InitCrudInspectionsPage() {
        PageFactory.initElements(chromeDriver, this);
    }

    public void FillForm() {

        Select licensePlate = new Select(licensePlateSelect);
        licensePlate.selectByIndex(1);

        commentInput.sendKeys("Selenium Testing");
        passedRadioTrueInput.click();

        submitButton.click();

        System.out.println("Filling in form");
    }

    public void ChangeForm() {
        commentInput.clear();
        commentInput.sendKeys("Edit Selenium testing");
        passedRadioTrueInput.click();

        submitButton.click();
    }


}
