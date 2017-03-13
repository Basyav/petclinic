package com.bas.petclinic.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean for owner
 */
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Pet> pets = new HashSet<>();

    public Owner() {
    }

    public Owner(String firstName, String lastName, String middleName, String address) {
        super(firstName, lastName, middleName);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Owner{" + this.getId() +
                ", firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", middleName='" + this.getMiddleName() + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
