package com.arjun.projects.controller;

import com.arjun.projects.entity.User;
import com.arjun.projects.entity.WishlistItem;
import com.arjun.projects.service.ProfileService;
import com.arjun.projects.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;

    @GetMapping("/users/{user}")
    public User userDetails(@PathVariable String user) {
        return userService.getUser(user);
    }

    @GetMapping("/wishlists/{user}")
    public List<WishlistItem> userWishlist(@PathVariable String user) {
        return userService.getWishlist(user);
    }

    @GetMapping("/match/wishlists")
    public List<List<WishlistItem>> matchGetWishlists(@RequestParam String user) {
        return userService.matchUserWishlists(user);
    }

    @GetMapping("/match/users")
    public List<User> matchGetUsers(@RequestParam String user) {
        return userService.matchUsers(user);
    }

    @PostMapping("/users")
    public User updateUserDetails(
            @RequestParam String name,
            @RequestParam String bio,
            @RequestParam double maxPrice) {

        return profileService.update(name, bio, maxPrice);
    }

    @PostMapping("/signup")
    public User newUserSignup(
            @RequestParam String name,
            @RequestParam String bio,
            @RequestParam double maxPrice) {

        return profileService.signup(name, bio, maxPrice);
    }


}
