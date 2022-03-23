package com.rocktech.jwtrolebased.service;

import com.rocktech.jwtrolebased.dao.RoleDao;
import com.rocktech.jwtrolebased.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role){
        return roleDao.save(role);
    }
}
