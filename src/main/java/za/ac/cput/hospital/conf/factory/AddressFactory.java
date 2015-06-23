package za.ac.cput.hospital.conf.factory;

import za.ac.cput.hospital.domain.Address;

/**
 * Created by student on 2015/06/23.
 */
public class AddressFactory {

    public static Address createAddress(String address, String province, String city, String country, String zipCode) {
        return new Address.Builder(address).province(province).city(city).country(country).zipCode(zipCode).build();
    }

}
