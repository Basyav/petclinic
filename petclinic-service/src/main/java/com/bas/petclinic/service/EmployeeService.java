package com.bas.petclinic.service;

import com.bas.petclinic.dto.EmployeeDTO;

/**
 *
 */
public interface EmployeeService {

    EmployeeDTO getEmployeeByUserId(Long id);

}
