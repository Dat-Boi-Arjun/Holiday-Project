package com.arjun.projects.service;

import com.arjun.projects.database.UserDatabase;
import com.arjun.projects.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    UserDatabase userDatabase;

    public User signup(String name, String bio, double maxPrice) {
        User user = new User(name, maxPrice >= 0 ? maxPrice : 0);
        user.setBio(bio == null ? "" : bio);
        return userDatabase.insertUser(user);
    }

    public User update(@NonNull String name, String bio, double maxPrice) {
        User user = userDatabase.findByName(name);
        if (bio != null) user.setBio(bio);
        if (maxPrice > 0) user.setMaxPrice(maxPrice);
        return userDatabase.updateUser(user);
    }
}
