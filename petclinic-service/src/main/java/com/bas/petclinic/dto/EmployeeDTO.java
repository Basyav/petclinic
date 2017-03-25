package com.bas.petclinic.dto;

/**
 * Data transfer object for Employee
 */
public class EmployeeDTO extends PersonDTO{

    private Integer experience;

    public EmployeeDTO() {
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + this.getId() +
                ", firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", middleName='" + this.getMiddleName() + '\'' +
                ", experience=" + this.experience +
                '}';
    }
}
