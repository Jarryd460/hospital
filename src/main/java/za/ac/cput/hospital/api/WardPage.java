package za.ac.cput.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.hospital.domain.Patient;
import za.ac.cput.hospital.domain.Ward;
import za.ac.cput.hospital.model.WardResource;
import za.ac.cput.hospital.services.WardService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
@RestController
@RequestMapping(value="/ward/**")
public class WardPage {

    @Autowired
    private WardService service;

    @RequestMapping(value="/wards", method= RequestMethod.GET)
    public List<WardResource> getWards() {
        List<WardResource> hateos = new ArrayList<WardResource>();
        List<Ward> wards = service.getWards();
        for (Ward ward : wards) {
            WardResource res = new WardResource
                    .Builder(ward.getCapacity())
                    .patientList(ward.getPatientList())
                    .build();
            Link wardsLink = new
                    Link("http://localhost:8080/ward/"+res.getResId().toString())
                    .withRel("wards");
            res.add(wardsLink);
            hateos.add(res);
        }
        return hateos;
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public List<Patient> getPatients(@PathVariable Long id) {
        return service.getPatients(id);
    }

}
