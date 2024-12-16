package org.mdasolutions.web.Services;

import org.mdasolutions.web.Entities.Services;
import org.mdasolutions.web.Repos.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServicesService {

    @Autowired
    private ServiceRepo serviceRepo;

    public List<Services> getAllServices() {
        return serviceRepo.findAll();
    }

    public void addService(Services service) {
        serviceRepo.save(service);
    }
}
