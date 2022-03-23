package com.rocktech.jwtrolebased.dao;

import com.rocktech.jwtrolebased.entity.Role;
import com.rocktech.jwtrolebased.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, String> {
}
