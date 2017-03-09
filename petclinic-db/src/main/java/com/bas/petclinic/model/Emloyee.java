package com.bas.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Bean for emloyee
 */
@Entity
@Table(name = "emloyees")
public class Emloyee extends Person {

    @Column(name = "experience")
    private Byte experience;

    public Emloyee() {
    }

    public Emloyee(String firstName, String lastName, String middleName, Byte experience) {
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
