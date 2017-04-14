package com.bas.petclinic.service.impl;

import com.bas.petclinic.dao.IssueDAO;
import com.bas.petclinic.dto.IssueDTO;
import com.bas.petclinic.mapper.IssueMapper;
import com.bas.petclinic.model.Issue;
import com.bas.petclinic.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueDAO issueDAO;

    @Autowired
    private IssueMapper issueMapper;

    @Override
    public IssueDTO getLastIssueByOwnerId(Long id) {
        List<Issue> issues = issueDAO.getAllIssuesByOwnerId(id);
        return (issues.isEmpty() ? null : issueMapper.toIssueDTO(issues.get(0)));
    }
}
