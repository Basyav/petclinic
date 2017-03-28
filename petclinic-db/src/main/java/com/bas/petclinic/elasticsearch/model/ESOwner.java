package com.bas.petclinic.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 *
 */
@Document(indexName = "petclinic", type = "owners")
public class ESOwner {

    @Id
    private String id;
    private String fio;

    public ESOwner() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ESOwner esOwner = (ESOwner) o;
        return id != null ? id.equals(esOwner.id) : esOwner.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ESOwner{" +
                "id='" + id + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }
}
