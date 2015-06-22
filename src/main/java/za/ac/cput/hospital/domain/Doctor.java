package za.ac.cput.hospital.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by student on 2015/06/22.
 */
@Entity
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    private Name name;
    @Embedded
    private Demographic demographic;
    @Embedded
    private Contact contact;
    @Embedded
    private Address address;
    private String specialization;
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name="doctor_id")
    //private List<Appointment> appointmentList;
    @OneToMany(mappedBy = "doctor", targetEntity = Appointment.class, fetch = FetchType.EAGER)
    private List<Appointment> appointmentList;

}
