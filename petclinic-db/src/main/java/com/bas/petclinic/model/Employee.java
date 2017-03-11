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
    private Byte experience;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String middleName, Byte experience) {
        super(firstName, lastName, middleName);
        this.experience = experience;
    }

    public Byte getExperience() {
        return experience;
    }

    public void setExperience(Byte experience) {
        this.experience = experience;
    }
}
