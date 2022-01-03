package project.edgeservicefrontend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.JsonObjectSerializer;
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

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    public String addEditCar(HttpServletRequest request, final Model model) {
        String licensePlate = request.getParameter("licensePlate");
        String merk = request.getParameter("merk");
        String type = request.getParameter("type");
        String euroNorm = request.getParameter("euroNorm");
        CarInfo.PortierOptie portierOptie = CarInfo.PortierOptie.valueOf(request.getParameter("portier"));

        CarInfo car = new CarInfo(merk, type, licensePlate, euroNorm, portierOptie);

        if (car.getLicensePlate() != "" && car.getMerk() != "" && car.getType() != "") {

            ResponseEntity<CarInfo> responseEntityCar =
                    restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/cars/license_plate/{licensePlate}",
                            HttpMethod.GET, null, new ParameterizedTypeReference<CarInfo>() {
                            }, licensePlate);



            if (responseEntityCar.getBody() != null) {
                CarInfo updateCar = responseEntityCar.getBody();
                updateCar.setLicensePlate(car.getLicensePlate());
                updateCar.setMerk(car.getMerk());
                updateCar.setType(car.getType());
                updateCar.setEuroNorm(car.getEuroNorm());
                updateCar.setPortier(car.getPortier());

                    restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/cars",
                            HttpMethod.PUT, new HttpEntity<>(updateCar),new ParameterizedTypeReference<CarInfo>() {
                            });
            } else {
                CarInfo newCar = car;
                CarInfo carInfo =
                        restTemplate.postForObject("https://" + safetyEdgeBaseUrl + "/cars",
                                newCar,CarInfo.class);
            }

            ResponseEntity<List<CarInfo>> cars =
                    restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/cars",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<CarInfo>>() {
                            });


            model.addAttribute("cars", cars.getBody());

            return "list_cars";
        } else {
            model.addAttribute("car", car);
            model.addAttribute("error", "Licenseplate, merk and type are required");
            return "crud_car";
        }


    }

    @DeleteMapping("/car/delete/{licensePlate}")
    public String deleteCar(@PathVariable String licensePlate, final Model model) {

        restTemplate.delete("https://" + safetyEdgeBaseUrl + "/cars/license_plate/{licensePlate}"  ,licensePlate);

        ResponseEntity<List<CarInfo>> cars =
                restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/cars",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CarInfo>>() {
                        });


        model.addAttribute("cars", cars.getBody());
        return "list_cars";

    }


    /* INSPECTION FUNCTIONS */

    @GetMapping("/inspections")
    private String all_inspections_page(final Model model) {

        ResponseEntity<List<Inspection>> inspections =
                restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/inspections",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Inspection>>() {
                        });


        model.addAttribute("inspections", inspections.getBody());

        return "list_inspections";
    }

    @GetMapping("/inspection/add")
    public String addInspection(final Model model) {
        LocalDate today = LocalDate.now();
        Inspection inspection = new Inspection(null, "", "", false, today );

        ResponseEntity<List<CarInfo>> cars =
                restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/cars",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CarInfo>>() {
                        });

        List<String> licensePlates = new ArrayList<String>();

        for (CarInfo car: cars.getBody()) {
            if(car.getLicensePlate() != null) {
                licensePlates.add(car.getLicensePlate());
            }
        }

        model.addAttribute("inspection", inspection);
        model.addAttribute("licensePlates", licensePlates);
        return "crud_inspection";
    }

    @GetMapping("/inspection/edit/{inspectionNumber}")
    public String editInspection(@PathVariable String inspectionNumber, final Model model) {
        ResponseEntity<Inspection> inspection =
                restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/inspections/inspection_number/{inspectionNumber}",
                        HttpMethod.GET, null, new ParameterizedTypeReference<Inspection>() {
                        }, inspectionNumber);

        ResponseEntity<List<CarInfo>> cars =
                restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/cars",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CarInfo>>() {
                        });

        List<String> licensePlates = new ArrayList<>();

        for (CarInfo car: cars.getBody()) {
            if(car.getLicensePlate() != null) {
                licensePlates.add(car.getLicensePlate());
            }
        }

        model.addAttribute("inspection", inspection.getBody());
        model.addAttribute("licensePlates", licensePlates);
        return "crud_inspection";
    }

    @PostMapping("/inspection")
    public String addEditInspection(HttpServletRequest request, final Model model) {
        Long inspectionNumber = null;
        if (request.getParameter("inspectionNumber") != "" && request.getParameter("inspectionNumber") != null) {
            inspectionNumber = Long.valueOf(request.getParameter("inspectionNumber"));
        }
        String licensePlate = request.getParameter("licensePlate");
        String comment = request.getParameter("comment");
        Boolean passed = Boolean.valueOf(request.getParameter("passed"));
        LocalDate inspectionDate = LocalDate.parse(request.getParameter("inspectionDate"));

        Inspection inspection = new Inspection(inspectionNumber, licensePlate, comment, passed, inspectionDate);

        if (inspection.getInspectionNumber() != null && inspection.getPassed() != null && inspectionDate != null) {

            ResponseEntity<Inspection> responseEntityInspection =
                    restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/inspections/inspection_number/{inspectionNumber}",
                            HttpMethod.GET, null, new ParameterizedTypeReference<Inspection>() {
                            }, inspection.getInspectionNumber());



            if (responseEntityInspection.getBody() != null) {
                Inspection updateInspection = responseEntityInspection.getBody();
                updateInspection.setLicensePlate(licensePlate);
                updateInspection.setComment(comment);
                updateInspection.setPassed(passed);
                updateInspection.setInspectionDate(inspectionDate);

                restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/inspections",
                        HttpMethod.PUT, new HttpEntity<>(updateInspection),new ParameterizedTypeReference<Inspection>() {
                        });
            }

            ResponseEntity<List<Inspection>> inspections =
                    restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/inspections",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Inspection>>() {
                            });


            model.addAttribute("inspections", inspections.getBody());

            return "list_inspections";
        } else if (inspection.getInspectionNumber() == null) {
            Inspection newInspection = inspection;
            Inspection inspectionPost =
                    restTemplate.postForObject("https://" + safetyEdgeBaseUrl + "/inspections",
                            newInspection,Inspection.class);

            ResponseEntity<List<Inspection>> inspections =
                    restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/inspections",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Inspection>>() {
                            });


            model.addAttribute("inspections", inspections.getBody());

            return "list_inspections";
        } else {
            model.addAttribute("inspection", inspection);
            return "crud_inspection";
        }
    }

    @DeleteMapping("/inspection/delete/{inspectionNumber}")
    public String deleteInspection(@PathVariable() String inspectionNumber ,final Model model) {

        restTemplate.delete("https://" + safetyEdgeBaseUrl + "/inspections/inspection_number/{inspectionNumber}" , inspectionNumber);

        ResponseEntity<List<Inspection>> inspections =
                    restTemplate.exchange("https://" + safetyEdgeBaseUrl + "/inspections",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Inspection>>() {
                            });


        model.addAttribute("inspections", inspections.getBody());
        return "list_inspections";
    }



}
