package com.mvc.project.dao.hibernate;

import com.mvc.project.dao.RoleDao;
import com.mvc.project.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DacaP
 * @version 1.0
 * @create 26/09/2022 2:32 pm
 */
@Repository
public class HibernateRoleDao implements RoleDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateRoleDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.save(role);
    }

    @Override
    public List<Role> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("FROM Role", Role.class).getResultList();
        return session.createQuery("FROM Role", Role.class).getResultList();
    }

    @Override
    public Role findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class, id);
    }

    @Override
    public void update(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.update(role);
    }

    @Override
    public void remove(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("DELETE Role WHERE id = " + id).executeUpdate();
    }

    @Override
    public Role findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query<Role> query = session.createQuery("FROM Role WHERE name=:name", Role.class);
        query.setParameter("name", name);
        return query.uniqueResult();
    }
}
