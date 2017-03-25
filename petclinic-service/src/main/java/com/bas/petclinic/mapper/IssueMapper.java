package com.bas.petclinic.mapper;

import com.bas.petclinic.dto.IssueDTO;
import com.bas.petclinic.model.Issue;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

/**
 * Translates between Issue and IssueDTO
 */
@Mapper(componentModel = "spring")
public abstract class IssueMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "employee", target = "employee")
    @Mapping(source = "pet", target = "pet")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "changedAt", target = "changedAt")
    @Mapping(source = "status", target = "status")
    public abstract IssueDTO toIssueDTO(Issue issue);

    @InheritInverseConfiguration
    public abstract Issue toIssue(IssueDTO issueDTO);

    public abstract List<IssueDTO> toIssueDTOs(Collection<Issue> issues);

    public abstract List<Issue> toIssues(Collection<IssueDTO> issueDTOs);
}
