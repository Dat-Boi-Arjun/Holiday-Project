package com.arjun.projects.service;

import com.arjun.projects.database.UserDatabase;
import com.arjun.projects.database.WishlistDatabase;
import com.arjun.projects.entity.User;
import com.arjun.projects.entity.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    WishlistDatabase wishlistDatabase;

    @Autowired
    UserDatabase userDatabase;

    //Wrapper method for DAO
    public User getUser(String name) {
        return userDatabase.findByName(name);
    }

    public List<WishlistItem> getWishlist(String user) {
        return wishlistDatabase.findUserWishlist(user).stream()
                .sorted(Collections.reverseOrder(
                        Comparator.comparing(WishlistItem::getPrice)))
                .collect(Collectors.toList());
    }

    //Gets users based on highest match
    public List<User> matchUsers(String name) {
        User user = userDatabase.findByName(name);
        List<WishlistItem> items = wishlistDatabase.priceMatch(user.getMaxPrice());

        return items.stream()
                .collect(Collectors.groupingBy(WishlistItem::getUser, Collectors.counting()))
                .entrySet().stream()
                .map(e -> Map.entry(e.getKey(), e.getValue() +
                        userDatabase.findByName(e.getKey()).getScore()))
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(m -> userDatabase.findByName(m.getKey()))
                .collect(Collectors.toList());
    }

    public List<List<WishlistItem>> matchUserWishlists(String name) {
        return this.matchUsers(name)
                .stream()
                .map(u -> this.getWishlist(u.getName()))
                .collect(Collectors.toList());
    }
}
