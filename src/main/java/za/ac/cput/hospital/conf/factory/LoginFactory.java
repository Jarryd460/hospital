package za.ac.cput.hospital.conf.factory;

import za.ac.cput.hospital.domain.Login;

/**
 * Created by student on 2015/08/10.
 */
public class LoginFactory {

    public static Login createLogin(String userName, String password) {
        return new Login.Builder(userName, password).build();
    }

}
