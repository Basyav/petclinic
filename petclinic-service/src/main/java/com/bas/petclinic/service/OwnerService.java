package com.bas.petclinic.service;

import com.bas.petclinic.dto.IssueDTO;
import com.bas.petclinic.dto.OwnerDTO;

/**
 *
 */
public interface OwnerService {

    OwnerDTO getOwnerByUserId(Long id);
}
