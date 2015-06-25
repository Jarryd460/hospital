package za.ac.cput.hospital.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.hospital.conf.factory.DemographicFactory;

import java.util.Date;

/**
 * Created by student on 2015/06/23.
 */
public class DemographicTest {

    @Test
    public void testCreate() {
        Demographic demographic = DemographicFactory.createDemographic(Sex.Male, Race.Coloured, new Date(1992, 04, 29));
        Assert.assertEquals(demographic.getGender(), Sex.Male);
        Assert.assertEquals(demographic.getRace(), Race.Coloured);
        Assert.assertEquals(demographic.getDateOfBirth(), new Date(1992, 04, 29));
    }

    @Test
    public void testUpdate() {
        Demographic demographic = DemographicFactory.createDemographic(Sex.Male, Race.Coloured, new Date(1992, 04, 29));
        Demographic demographicCopy = new Demographic.Builder(demographic.getDateOfBirth()).copy(demographic).race(Race.Black).build();
        Assert.assertEquals(demographicCopy.getGender(), Sex.Male);
        Assert.assertEquals(demographicCopy.getRace(), Race.Black);
        Assert.assertEquals(demographicCopy.getDateOfBirth(), new Date(1992, 04, 29));
    }

}
