package com.bas.petclinic.dao.impl;

import com.bas.petclinic.dao.EmployeeDAO;
import com.bas.petclinic.model.Employee;
import com.bas.petclinic.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by dmitry on 3/10/17.
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Employee createEmployee(String firstName, String lastName, String middleName,
                                   User username, Integer experience) throws DataAccessException {
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
    public Employee getEmployeeByUserId(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery();
        Root<Employee> i = criteria.from(Employee.class);
        Employee employee = (Employee) entityManager.createQuery(
                criteria.select(i).where(
                        builder.equal(
                                i.get("username").get("id"),
                                builder.parameter(Long.class, "userId")
                        )
                )
        ).setParameter("userId", id).getSingleResult();
        return employee;
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) throws DataAccessException {
        entityManager.merge(employee);
        return employee;
    }

    @Override
    @Transactional
    public void deleteEmployeeById(Long id) throws DataAccessException {
        Employee employee = getEmployeeById(id);
        entityManager.remove(employee);
    }
}
