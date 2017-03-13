package com.bas.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Bean for employee
 */
@Entity
@Table(name = "employees")
public class Employee extends Person {

    @Column(name = "experience")
    private Integer experience;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String middleName, Integer experience) {
        super(firstName, lastName, middleName);
        this.experience = experience;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.getId() +
                ", firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", middleName='" + this.getMiddleName() + '\'' +
                ", experience=" + experience +
                '}';
    }
}
