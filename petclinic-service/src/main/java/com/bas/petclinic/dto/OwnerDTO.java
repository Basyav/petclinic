package com.bas.petclinic.dto;

/**
 *  Data transfer object for Owner
 */
public class OwnerDTO extends PersonDTO {

    private String address;

    public OwnerDTO() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OwnerDTO{" +
                "id=" + this.getId() +
                ", firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", middleName='" + this.getMiddleName() + '\'' +
                ", address='" + this.address + '\'' +
                '}';
    }
}
