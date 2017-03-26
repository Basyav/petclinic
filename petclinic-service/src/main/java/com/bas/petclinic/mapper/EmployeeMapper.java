package com.bas.petclinic.mapper;

import com.bas.petclinic.dto.EmployeeDTO;
import com.bas.petclinic.model.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Translates between Employee and EmployeeDTO
 */
@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    @Autowired
    protected UserMapper userMapper;

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "middleName", target = "middleName")
    @Mapping(source = "experience", target = "experience")
    @Mapping(expression = "java(userMapper.toUserDTO(employee.getUsername()))", target = "user")
    public abstract EmployeeDTO toEmployeeDTO(Employee employee);

    @InheritInverseConfiguration
    @Mapping(expression = "java(userMapper.toUser(employeeDTO.getUser()))", target = "username")
    public abstract Employee toEmployee(EmployeeDTO employeeDTO);

    public abstract List<EmployeeDTO> toEmployeeDTOs(Collection<Employee> employees);

    public abstract List<Employee> toEmployees(Collection<EmployeeDTO> employeeDTOs);
}
