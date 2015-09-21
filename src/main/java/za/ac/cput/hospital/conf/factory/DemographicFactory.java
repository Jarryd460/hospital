package za.ac.cput.hospital.conf.factory;

import za.ac.cput.hospital.domain.Demographic;
import za.ac.cput.hospital.domain.Race;
import za.ac.cput.hospital.domain.Sex;

import java.util.Date;

/**
 * Created by student on 2015/06/23.
 */
public class DemographicFactory {

    public static Demographic createDemographic(Sex gender, Race race, String dateOfBirth) {
        return new Demographic.Builder(dateOfBirth).gender(gender).race(race).build();
    }

}
