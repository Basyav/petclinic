package com.bas.petclinic.dto;

/**
 * Data transfer object for ESOwner
 */
public class ESOwnerDTO {

    private Long id;
    private String FIO;

    public ESOwnerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ESOwnerDTO that = (ESOwnerDTO) o;
        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ESOwnerDTO{" +
                "id=" + id +
                ", FIO='" + FIO + '\'' +
                '}';
    }
}
