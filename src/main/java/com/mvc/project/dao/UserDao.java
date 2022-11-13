package com.mvc.project.dao;

import com.mvc.project.entity.User;

/**
 * @author DacaP
 * @version 1.0
 * @create 25/09/2022 10:29 pm
 */
public interface UserDao extends Dao<User> {

    User findByUsername(String username);

    User findByEmail(String email);
}