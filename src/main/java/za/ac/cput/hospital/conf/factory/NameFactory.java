package za.ac.cput.hospital.conf.factory;

import za.ac.cput.hospital.domain.Name;

/**
 * Created by student on 2015/06/23.
 */
public class NameFactory {

    public static Name createName(String firstName, String lastName) {
        return new Name.Builder(lastName).firstName(firstName).build();
    }

}
