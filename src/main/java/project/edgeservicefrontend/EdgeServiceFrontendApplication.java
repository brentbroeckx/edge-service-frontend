package project.edgeservicefrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EdgeServiceFrontendApplication {

    @Bean
    public RestTemplate getRestTemplate(){return new RestTemplate();}

    public static void main(String[] args) {
        SpringApplication.run(EdgeServiceFrontendApplication.class, args);
    }

}
