package com.fintechapp.role.services;

import java.util.List;

import com.fintechapp.res.Response;
import com.fintechapp.role.entity.Role;

public interface RoleService {
    Response<Role> createRole(Role roleRequest);

    Response<Role> updateRole(Role roleRequest);

    Response<List<Role>> getAllRoles();

    Response<?> deleteRole(Long id);
}
