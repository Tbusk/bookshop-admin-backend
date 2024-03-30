package com.bookshop.web;

import com.bookshop.domain.User;
import com.bookshop.domain.UserRepository;
import com.bookshop.util.APIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private APIUtil apiUtil;

    // Get all users
    @GetMapping(value = "/list", produces = "application/json")
    public ResponseEntity<?> getUsers() {
        try {
            return ResponseEntity.ok().body(userRepository.findAll());
        }
        catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiUtil.buildResponse("There was an issue finding users"));
        }
    }

    // Get user by id
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(userRepository.findById(id).get());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiUtil.buildResponse("User not found"));
        }
    }

    // Add user
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addUser(@RequestBody Map<String, String> userMap) {

        if(userRepository.findByUsername(userMap.get("username")).isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiUtil.buildResponse("Username taken. Please choose another username").toString());
        }

        User user = new User();

        try {
            user.setUsername(userMap.get("username"));
            user.setPassword(userMap.get("password"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiUtil.buildResponse("Username and password are required").toString());
        }

        // Setting optional fields
        user.setFirstName(userMap.getOrDefault("firstName", null));
        user.setLastName(userMap.getOrDefault("lastName", null));
        user.setEmailAddress(userMap.getOrDefault("emailAddress",null));
        user.setPhoneNumber(userMap.getOrDefault("phoneNumber",null));
        user.setProfilePicture(userMap.getOrDefault("profilePicture", "https://icons.getbootstrap.com/assets/icons/person-circle.svg"));
        user.setRole(userMap.getOrDefault("role", "USER"));

        userRepository.save(user);
        return ResponseEntity.ok().body(apiUtil.buildResponse("User added").toString());
    }

    // Update user
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody Map<String, String> userMap) {

        User user = userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;

        // Checking if user exists
        if(user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiUtil.buildResponse("User not found").toString());
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
        return ResponseEntity.ok().body(apiUtil.buildResponse("User updated").toString());
    }

    // Delete user
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if(userRepository.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiUtil.buildResponse("User not found").toString());
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().body(apiUtil.buildResponse("User deleted").toString());
    }
}


