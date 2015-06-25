package za.ac.cput.hospital.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.hospital.conf.factory.ContactFactory;

/**
 * Created by student on 2015/06/23.
 */
public class ContactTest {

    @Test
    public void testCreate() {
        Contact contact = ContactFactory.createContact("0123936357", "0821239876", "0761231234");
        Assert.assertEquals(contact.getHomePhoneNumber(), "0123936357");
        Assert.assertEquals(contact.getMobilePhoneNumber(), "0821239876");
        Assert.assertEquals(contact.getAlternativePhoneNumber(), "0761231234");
    }

    @Test
    public void testUpdate() {
        Contact contact = ContactFactory.createContact("0123936357", "0821239876", "0761231234");
        Contact contactCopy = new Contact.Builder(contact.getMobilePhoneNumber()).copy(contact).homePhoneNumber("0215674321").build();
        Assert.assertEquals(contactCopy.getHomePhoneNumber(), "0215674321");
        Assert.assertEquals(contactCopy.getMobilePhoneNumber(), "0821239876");
        Assert.assertEquals(contactCopy.getAlternativePhoneNumber(), "0761231234");
    }

}
