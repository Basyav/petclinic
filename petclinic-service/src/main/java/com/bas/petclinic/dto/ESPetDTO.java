package com.bas.petclinic.dto;

/**
 * Data transfer object for ESPet
 */
public class ESPetDTO {

    private Long id;
    private String name;
    private String ownerFIO;

    public ESPetDTO() {
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

    public String getOwnerFIO() {
        return ownerFIO;
    }

    public void setOwnerFIO(String ownerFIO) {
        this.ownerFIO = ownerFIO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ESPetDTO esPetDTO = (ESPetDTO) o;
        return id != null ? id.equals(esPetDTO.id) : esPetDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ESPetDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerFIO='" + ownerFIO + '\'' +
                '}';
    }
}
