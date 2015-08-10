package za.ac.cput.hospital.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.hospital.domain.Doctor;
import za.ac.cput.hospital.domain.Hospital;
import za.ac.cput.hospital.domain.Ward;
import za.ac.cput.hospital.model.HospitalResource;
import za.ac.cput.hospital.services.HospitalService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/08/02.
 */
@RestController
@RequestMapping(value="/hospital/**")
public class HospitalPage {

    @Autowired
    private HospitalService service;

    @RequestMapping(value="/hospitals", method= RequestMethod.GET)
    public List<HospitalResource> getHospitals() {
        List<HospitalResource> hateos = new ArrayList<HospitalResource>();
        List<Hospital> hospitals = service.getHospitals();
        for (Hospital hospital : hospitals) {
            HospitalResource res = new HospitalResource
                    .Builder(hospital.getName())
                    .contactNumber(hospital.getContactNumber())
                    .address(hospital.getAddress())
                    .wardList(hospital.getWardList())
                    .doctorList(hospital.getDoctorList())
                    .resid(hospital.getId())
                    .build();
            Link hospitalsLink = new
                    Link("http://localhost:8080/hospital/"+res.getResId().toString())
                    .withRel("hospitals");
            res.add(hospitalsLink);
            hateos.add(res);
        }
        return hateos;
    }

    @RequestMapping(value="/d{id}", method= RequestMethod.GET)
    public List<Doctor> getDoctors(@PathVariable Long id) {
        return service.getDoctors(id);
    }

    @RequestMapping(value="/w{id}", method= RequestMethod.GET)
    public List<Ward> getWards(@PathVariable Long id) {
        return service.getWards(id);
    }

}
