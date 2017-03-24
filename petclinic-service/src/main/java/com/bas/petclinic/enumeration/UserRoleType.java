package com.bas.petclinic.enumeration;

/**
 * Enum for UserRole
 */
public enum UserRoleType {

    СLIENT(1),
    EMPLOYEE(2),
    ADMIN(3);

    private final int id;

    UserRoleType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserRoleType getById(Integer id) {
        if (id == null) {
            return null;
        }
        for (UserRoleType role : values()) {
            if (role.id == id) {
                return role;
            }
        }
        return null;
    }

    public static UserRoleType getByName(String name) {
        if (name == null) {
            return null;
        }
        String upper = name.trim().toUpperCase();
        for (UserRoleType role : values()) {
            if (role.name().equals(upper)) {
                return role;
            }
        }
        return null;
    }
}
