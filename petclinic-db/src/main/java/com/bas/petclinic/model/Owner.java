package com.bas.petclinic.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean for owner
 */
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> pets = new ArrayList<>();

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
}
