package com.rocktech.jwtrolebased.dao;

import com.rocktech.jwtrolebased.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {
}
