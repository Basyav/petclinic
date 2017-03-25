package com.bas.petclinic.mapper;

import com.bas.petclinic.dto.EmployeeDTO;
import com.bas.petclinic.model.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

/**
 * Translates between Employee and EmployeeDTO
 */
@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    @Mapping(source = "experience", target = "experience")
    public abstract EmployeeDTO toEmployeeDTO(Employee employee);

    @InheritInverseConfiguration
    public abstract Employee toEmployee(EmployeeDTO employeeDTO);

    public abstract List<EmployeeDTO> toEmployeeDTOs(Collection<Employee> employees);

    public abstract List<Employee> toEmployees(Collection<EmployeeDTO> employeeDTOs);
}
