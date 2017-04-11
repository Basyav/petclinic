package com.bas.petclinic.service.impl;

import com.bas.petclinic.dao.OwnerDAO;
import com.bas.petclinic.dto.OwnerDTO;
import com.bas.petclinic.mapper.OwnerMapper;
import com.bas.petclinic.model.Owner;
import com.bas.petclinic.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerDAO ownerDAO;

    @Autowired
    private OwnerMapper ownerMapper;

    @Override
    @Transactional
    public OwnerDTO getOwnerByUserId(Long id) {
        Owner owner = ownerDAO.getOwnerByUserId(id);
        return ownerMapper.toOwnerDTO(owner);
    }
}
