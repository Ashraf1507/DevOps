package com.agi.service.impl;

import com.agi.core.user.Role;
import com.agi.payload.response.RoleResponse;
import com.agi.repository.RoleRepository;
import com.agi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<RoleResponse> index() {
        List<RoleResponse> roleResponses = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();
        for (Role role: roles){
            RoleResponse roleResponse = new RoleResponse();
            roleToRoleResponse(role, roleResponse);
            roleResponses.add(roleResponse);
        }
        return roleResponses;
    }

    private void roleToRoleResponse(Role role, RoleResponse roleResponse){
        roleResponse.setId(role.getId());
        roleResponse.setName(role.getName().name());
    }
}
