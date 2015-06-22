package za.ac.cput.hospital.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by student on 2015/06/22.
 */
@Entity
public class Patient implements Serializable {

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
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name="patient_id")
    //private List<Appointment> appointmentList;
    @OneToMany(mappedBy = "patient", targetEntity = Appointment.class, fetch = FetchType.EAGER)
    private List<Appointment> appointmentList;

}
