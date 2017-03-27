package com.bas.petclinic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 *
 */
@Document(indexName = "petclinic", type = "owners")
public class ESOwner {

    @Id
    private String id;
    private String FIO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
                ", FIO='" + FIO + '\'' +
                '}';
    }
}
