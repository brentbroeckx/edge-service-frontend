package project.edgeservicefrontend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.openqa.selenium.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import project.edgeservicefrontend.model.CarInfo;
import project.edgeservicefrontend.model.Inspection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class FrontendController {

    @Autowired
    private RestTemplate restTemplate;

    /*@Value("${SAFETY_EDGE_SERVICE_BASEURL:localhost:8050}")*/
    @Value("safety-edge-service-server-albertbaffour.cloud.okteto.net")
    private String safetyEdgeBaseUrl;

    @GetMapping("/")
    private String homepage() {
        return "homepage";
    }

    @GetMapping("/cars")
    private String all_cars_page(final Model model) {

        ResponseEntity<List<CarInfo>> cars =
                restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/cars",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CarInfo>>() {
                        });


        model.addAttribute("cars", cars.getBody());

        return "list_cars";
    }

    @GetMapping("/inspections")
    private String all_inspections_page(final Model model) {

        ResponseEntity<List<Inspection>> inspections =
                restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/inspections",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Inspection>>() {
                        });


        model.addAttribute("inspections", inspections.getBody());

        return "list_inspections";
    }

    @GetMapping("/car/add")
    public String addCarPage(final Model model) {
        CarInfo car = new CarInfo("", "", "", "", CarInfo.PortierOptie.VIERDEURS);

        model.addAttribute("car", car);
        return "crud_car";
    }

    @GetMapping("/car/edit/{licensePlate}")
    public String editCar(@PathVariable String licensePlate, final Model model) {
        ResponseEntity<CarInfo> car =
                restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/cars/license_plate/{licensePlate}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<CarInfo>() {
                }, licensePlate);

        model.addAttribute("car", car.getBody());
        return "crud_car";
    }

    @PostMapping("/car")
    public String addCar(@ModelAttribute CarInfo car, final Model model) {

        String carLicense = car.getLicensePlate();
        System.out.println(carLicense);

        if (car.getLicensePlate() != "" && car.getMerk() != "" && car.getType() != "") {

            ResponseEntity<CarInfo> responseEntityCar =
                    restTemplate.exchange("http://" + safetyEdgeBaseUrl + "/cars/license_plate/{licensePlate}",
                            HttpMethod.GET, null, new ParameterizedTypeReference<CarInfo>() {
                            }, carLicense);

            if (responseEntityCar != null) {
                CarInfo updateCar = responseEntityCar.getBody();
                updateCar.setLicensePlate(car.getLicensePlate());
                updateCar.setMerk(car.getMerk());
                updateCar.setType(car.getType());
                updateCar.setEuroNorm(car.getEuroNorm());
                updateCar.setPortier(car.getPortier());

                restTemplate.exchange("http://" + safetyEdgeBaseUrl + "/cars",
                        HttpMethod.PUT, new HttpEntity<>(updateCar),CarInfo.class);
            } else {
                CarInfo newCar = car;
                CarInfo carInfo =
                        restTemplate.postForObject("http://" + safetyEdgeBaseUrl + "/cars",
                                newCar,CarInfo.class);
            }

            return "list_cars";
        } else {
            model.addAttribute("car", car);
            model.addAttribute("error", "Licenseplate, merk and type are required");
            return "crud_car";
        }


    }



}
