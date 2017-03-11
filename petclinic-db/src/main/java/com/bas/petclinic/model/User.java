package com.bas.petclinic.model;

import javax.persistence.*;
import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "id_role")
    private UserRole role;

    @Column(name = "created_date")
    private LocalDate createdAt;

    public User() {
    }

    public User(String username, String passwordAndSalt, UserRole role, LocalDate createdAt) {
        this.username = username;
        this.passwordAndSalt = passwordAndSalt;
        this.role = role;
        this.createdAt = createdAt;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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
