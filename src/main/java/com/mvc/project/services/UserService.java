package com.mvc.project.services;

import com.mvc.project.dao.UserDao;
import com.mvc.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author DacaP
 * @version 1.0
 * @create 26/09/2022 6:45 pm
 */
@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void create(User user) {
        userDao.create(user);
    }

    @Transactional
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    public void remove(long id) {
        userDao.remove(id);
    }

    @Transactional
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Transactional
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
