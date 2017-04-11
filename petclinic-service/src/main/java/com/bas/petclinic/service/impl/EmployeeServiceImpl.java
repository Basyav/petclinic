package com.bas.petclinic.service.impl;

import com.bas.petclinic.dao.EmployeeDAO;
import com.bas.petclinic.dto.EmployeeDTO;
import com.bas.petclinic.mapper.EmployeeMapper;
import com.bas.petclinic.model.Employee;
import com.bas.petclinic.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public EmployeeDTO getEmployeeByUserId(Long id) {
        Employee employee = employeeDAO.getEmployeeByUserId(id);
        return employeeMapper.toEmployeeDTO(employee);
    }
}
