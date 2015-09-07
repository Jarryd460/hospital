package za.ac.cput.hospital.services;

import java.util.List;

/**
 * Created by student on 2015/09/05.
 */
public interface Services<S, ID> {

    public S findById(ID id);

    public S create(S entity);

    public S edit(S entity);

    public void delete(S entity);

    public List<S> findAll();

}
