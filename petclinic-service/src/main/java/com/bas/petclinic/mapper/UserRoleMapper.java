package com.bas.petclinic.mapper;

import com.bas.petclinic.dao.UserRoleDAO;
import com.bas.petclinic.enumeration.UserRoleType;
import com.bas.petclinic.model.UserRole;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Translates between objects from tables "user_roles" and enum UserRoleType
 */
@Mapper(componentModel = "spring")
public abstract class UserRoleMapper {

    @Autowired
    private UserRoleDAO userRoleDAO;

    private static final Map<Integer, UserRole> CACHED_ROLES = new HashMap<>();

    public UserRoleType roleToRoleType(UserRole userRole) {
        return UserRoleType.getById(userRole.getId());
    }

    public UserRole roleTypeToRole(UserRoleType roleType) {
        if (!CACHED_ROLES.containsKey(roleType.getId())) {
            CACHED_ROLES.put(roleType.getId(), userRoleDAO.getUserRoleById(roleType.getId()));
        }
        return CACHED_ROLES.get(roleType.getId());
    }

    public abstract Set<UserRoleType> rolesToRoleTypes(Collection<UserRole> roles);

    public abstract Set<UserRole> roleTypesToRoles(Collection<UserRoleType> roleTypes);
}
