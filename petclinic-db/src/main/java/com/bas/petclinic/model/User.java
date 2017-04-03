package com.bas.petclinic.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * User for Spring Security
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login", length = 20, unique = true)
    private String username;

    @Column(name = "password")
    private String passwordAndSalt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_role")})
    private Set<UserRole> roles;

    @Column(name = "created_date", columnDefinition = "DEFAULT now()")
    private LocalDate createdAt;

    public User() {
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

    public String getPasswordAndSalt() {
        return passwordAndSalt;
    }

    public void setPasswordAndSalt(String passwordAndSalt) {
        this.passwordAndSalt = passwordAndSalt;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
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
        User user = (User) o;
        return id.equals(user.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwordAndSalt='" + passwordAndSalt + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
