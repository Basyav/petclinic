package com.bas.petclinic.mapper;

import com.bas.petclinic.dto.IssueDTO;
import com.bas.petclinic.model.Issue;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Translates between Issue and IssueDTO
 */
@Mapper(componentModel = "spring")
public abstract class IssueMapper {

    @Autowired
    protected EmployeeMapper employeeMapper;

    @Autowired
    protected PetMapper petMapper;

    @Mapping(source = "id", target = "id")
    @Mapping(expression = "java(employeeMapper.toEmployeeDTO(issue.getEmployee()))", target = "employee")
    @Mapping(expression = "java(petMapper.toPetDTO(issue.getPet()))", target = "pet")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "changedAt", target = "changedAt")
    @Mapping(source = "status", target = "status")
    public abstract IssueDTO toIssueDTO(Issue issue);

    @InheritInverseConfiguration
    @Mapping(expression = "java(employeeMapper.toEmployee(issueDTO.getEmployee()))", target = "employee")
    @Mapping(expression = "java(petMapper.toPet(issueDTO.getPet()))", target = "pet")
    public abstract Issue toIssue(IssueDTO issueDTO);

    public abstract List<IssueDTO> toIssueDTOs(Collection<Issue> issues);

    public abstract List<Issue> toIssues(Collection<IssueDTO> issueDTOs);
}
