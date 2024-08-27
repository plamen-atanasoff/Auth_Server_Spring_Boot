package com.Auth_Server_Spring_Boot.businesslogic.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll() {
        // create query
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);

        // return query result
        return query.getResultList();
    }

    @Override
    public User findByUsername(String username) {
        String queryMessage = String.format("FROM User WHERE username='%s'", username);
        TypedQuery<User> query = entityManager.createQuery(queryMessage, User.class);

        return query.getSingleResult();
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void delete(int userId) {
        User user = entityManager.find(User.class, userId);

        entityManager.remove(user);
    }
}
