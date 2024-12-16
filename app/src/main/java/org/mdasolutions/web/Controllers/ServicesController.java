package org.mdasolutions.web.Controllers;

import org.mdasolutions.web.Entities.Services;
import org.mdasolutions.web.Services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @GetMapping("/services")
    public String getServicesPage(Model model) {
        List<Services> servicesList = servicesService.getAllServices();

        model.addAttribute("services", servicesList);

        return "services";
    }
}
