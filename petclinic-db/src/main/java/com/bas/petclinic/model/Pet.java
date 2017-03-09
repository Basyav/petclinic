package com.bas.petclinic.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Bean for pet
 */
@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 400)
    private String description;

    @Column(name = "reg_date")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "id_owner")
    private Owner owner;

    public Pet() {
    }

    public Pet(String name, String description, LocalDate createdAt) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
