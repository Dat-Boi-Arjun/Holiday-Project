package com.arjun.projects.database;

import com.arjun.projects.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class UserDatabase {

    @PersistenceContext
    EntityManager entityManager;


    public User findById(String id) {
        return entityManager.find(User.class, id);
    }

    public User findByName(String name) {
        return entityManager.find(User.class, name);
    }

    public User updateUser(User user) {
        return entityManager.merge(user);
    }

    public User insertUser(User user) {
        return entityManager.merge(user);
    }

}
