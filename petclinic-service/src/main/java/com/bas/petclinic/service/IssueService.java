package com.bas.petclinic.service;

import com.bas.petclinic.dto.IssueDTO;

/**
 *
 */
public interface IssueService {

    IssueDTO getLastIssueByOwnerId(Long id);
}
