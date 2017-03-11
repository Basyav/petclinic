package com.bas.petclinic.model;

import javax.persistence.*;

/**
 * Role of user
 */
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 20)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return id.equals(userRole.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
