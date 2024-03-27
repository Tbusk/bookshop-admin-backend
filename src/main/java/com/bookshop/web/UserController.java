package com.bookshop.web;

import com.bookshop.domain.User;
import com.bookshop.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/list", produces = "application/json")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public String addUser(@RequestBody Map<String, String> userMap) {

        if(userRepository.findByUsername(userMap.get("username")).isPresent()) {
            return "Username taken. Please choose another username";
        }

        User user = new User();

        try {
            user.setUsername(userMap.get("username"));
            user.setPassword(userMap.get("password"));
        } catch (Exception e) {
            return "Username and password are required";
        }

        // Setting optional fields
        user.setFirstName(userMap.getOrDefault("firstName", null));
        user.setLastName(userMap.getOrDefault("lastName", null));
        user.setEmailAddress(userMap.getOrDefault("emailAddress",null));
        user.setPhoneNumber(userMap.getOrDefault("phoneNumber",null));
        user.setProfilePicture(userMap.getOrDefault("profilePicture", "https://icons.getbootstrap.com/assets/icons/person-circle.svg"));
        user.setRole(userMap.getOrDefault("role", "USER"));

        userRepository.save(user);
        return "User added";
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public String updateUser(@PathVariable Long id, @RequestBody Map<String, String> userMap) {

        User user = userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;

        // Checking if user exists
        if(user == null) {
            return "User not found";
        }

        // Updating user fields
        user.setUsername(userMap.getOrDefault("username", user.getUsername()));
        user.setPassword(userMap.getOrDefault("password", user.getPassword()));
        user.setFirstName(userMap.getOrDefault("firstName", user.getFirstName()));
        user.setLastName(userMap.getOrDefault("lastName", user.getLastName()));
        user.setEmailAddress(userMap.getOrDefault("emailAddress", user.getEmailAddress()));
        user.setPhoneNumber(userMap.getOrDefault("phoneNumber", user.getPhoneNumber()));
        user.setProfilePicture(userMap.getOrDefault("profilePicture", user.getProfilePicture()));

        userRepository.save(user);
        return "User updated";
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public String deleteUser(@PathVariable Long id) {
        if(userRepository.findById(id).isEmpty()) {
            return "User not found";
        }
        userRepository.deleteById(id);
        return "User deleted";
    }
}


