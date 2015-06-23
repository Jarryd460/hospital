package za.ac.cput.hospital.conf.factory;

import za.ac.cput.hospital.domain.Contact;

/**
 * Created by student on 2015/06/23.
 */
public class ContactFactory {

    public static Contact createContact(String homePhoneNumber, String mobilePhoneNumber, String alternativePhoneNumber) {
        return new Contact.Builder(mobilePhoneNumber).homePhoneNumber(homePhoneNumber).alternativePhoneNumber(alternativePhoneNumber).build();
    }

}
