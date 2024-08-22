package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User requestUser) {
        try {
            User user = userService.registerUser(requestUser);
            return ResponseEntity.status(200).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> updatePassword(@RequestBody String password, @PathVariable int userId) {
        User user = userService.updatePassword(password, userId);
        if (user == null) {
            return ResponseEntity.status(400).body("User not found with ID: " + userId);
        }
        else {
            return ResponseEntity.ok(user);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Integer> deleteUser(@PathVariable int userId) {
        int result = userService.deleteUserById(userId);
        if (result == 0) {
            return ResponseEntity.status(200).body(null);
        }
        return ResponseEntity.ok(1);
    }

}
