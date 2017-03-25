package com.bas.petclinic.dto;

import com.bas.petclinic.model.Employee;
import com.bas.petclinic.model.IssueStatus;
import com.bas.petclinic.model.Pet;

import java.time.LocalDateTime;

/**
 * Data transfer object for Issue
 */
public class IssueDTO {

    private Long id;
    private Employee employee;
    private Pet pet;
    private String description;
    private LocalDateTime changedAt;
    private IssueStatus status;

    public IssueDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueDTO issueDTO = (IssueDTO) o;
        return id != null ? id.equals(issueDTO.id) : issueDTO.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "IssueDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", changedAt=" + changedAt +
                ", status=" + status +
                '}';
    }
}
