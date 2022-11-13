package com.mvc.project.services;

import com.mvc.project.dao.RoleDao;
import com.mvc.project.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author DacaP
 * @version 1.0
 * @create 26/09/2022 6:44 pm
 */
@Service
public class RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    public void create(Role role) {
        roleDao.create(role);
    }

    @Transactional
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Transactional
    public Role findById(long id) {
        return roleDao.findById(id);
    }

    @Transactional
    public void update(Role role) {
        roleDao.update(role);
    }

    @Transactional
    public void remove(long id) {
        roleDao.remove(id);
    }

    @Transactional
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}
