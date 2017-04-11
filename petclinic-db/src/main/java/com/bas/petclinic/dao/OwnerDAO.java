package com.bas.petclinic.dao;

import com.bas.petclinic.model.Owner;
import com.bas.petclinic.model.User;
import org.springframework.dao.DataAccessException;

/**
 * DAO for bean Owner
 */
public interface OwnerDAO {

    Owner createOwner(String firstName, String lastName, String middleName,
                      User username, String address) throws DataAccessException;

    Owner getOwnerById(Long id);

    Owner getOwnerByUserId(Long id);

    Owner updateOwner(Owner owner) throws DataAccessException;

    void deleteOwnerById(Long id) throws DataAccessException;
}
