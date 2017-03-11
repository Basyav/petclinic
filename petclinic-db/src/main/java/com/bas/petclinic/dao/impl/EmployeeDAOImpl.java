package com.bas.petclinic.dao.impl;

import com.bas.petclinic.dao.EmployeeDAO;
import com.bas.petclinic.model.Employee;
import com.bas.petclinic.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dmitry on 3/10/17.
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Employee createEmployee(String firstName, String lastName, String middleName,
                                   User username, Byte experience) throws DataAccessException {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        if (middleName != null) {
            employee.setMiddleName(middleName);
        }
        else {
            employee.setMiddleName("");
        }
        employee.setUsername(username);
        employee.setExperience(experience);
        entityManager.persist(employee);
        return employee;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return  entityManager.find(Employee.class, id);
    }

    @Override
    public Employee updateEmployee(Employee employee) throws DataAccessException {
        entityManager.merge(employee);
        return employee;
    }

    @Override
    public void deleteEmployeeById(Long id) throws DataAccessException {
        Employee employee = getEmployeeById(id);
        entityManager.remove(employee);
    }
}
