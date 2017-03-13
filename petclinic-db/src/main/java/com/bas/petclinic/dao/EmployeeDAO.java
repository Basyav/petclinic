package com.bas.petclinic.dao;

import com.bas.petclinic.model.Employee;
import com.bas.petclinic.model.User;
import org.springframework.dao.DataAccessException;

/**
 * Created by dmitry on 3/10/17.
 */

public interface EmployeeDAO {

    Employee createEmployee(String firstName, String lastName, String middleName,
                            User username, Integer experience) throws DataAccessException;

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Employee employee) throws DataAccessException;

    void deleteEmployeeById(Long id) throws DataAccessException;
}
