package com.bas.petclinic.dto;

import com.bas.petclinic.enumeration.UserRoleType;

import java.time.LocalDate;
import java.util.Set;

/**
 * Data transfer object from User
 */
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private Set<UserRoleType> roleTypes;
    private LocalDate createdAt;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRoleType> getRoleTypes() {
        return roleTypes;
    }

    public void setRoleTypes(Set<UserRoleType> roleTypes) {
        this.roleTypes = roleTypes;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id != null ? id.equals(userDTO.id) : userDTO.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
