package project.edgeservicefrontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import project.edgeservicefrontend.model.CarInfo;
import project.edgeservicefrontend.model.Inspection;

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



}
