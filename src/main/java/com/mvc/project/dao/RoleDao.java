package com.mvc.project.dao;

import com.mvc.project.entity.Role;

/**
 * @author DacaP
 * @version 1.0
 * @create 25/09/2022 10:28 pm
 */
public interface RoleDao extends Dao<Role> {

    Role findByName(String name);
}