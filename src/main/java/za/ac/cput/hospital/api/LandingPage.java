package za.ac.cput.hospital.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.hospital.domain.Doctor;

/**
 * Created by student on 2015/09/20.
 */
@RestController
@RequestMapping("/")
public class LandingPage {
    @RequestMapping(method = RequestMethod.GET)
    public Doctor getDoctor(){
        Doctor doctor = new Doctor.Builder(null)
                .specialization("Radiologist")
                .build();
        return doctor;
    }
}
