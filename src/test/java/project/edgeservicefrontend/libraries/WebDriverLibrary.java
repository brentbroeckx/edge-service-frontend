package project.edgeservicefrontend.libraries;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Component
public class WebDriverLibrary {

//    @Bean
//    public static WebDriver getChromeDriver() {
//        WebDriver driver = null;
//        WebDriverManager.chromedriver().browserVersion("96.0.4664.45").setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("enable-automation");
//        options.addArguments("--no-sandbox");
//        driver = new ChromeDriver();
//
//        return driver;
//
//    }

}
