package com.hari.maintenance.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hari.maintenance.model.User;
import com.hari.maintenance.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/me")
    public User getDetails(Authentication auth) {
        return userService.findByEmail(auth.getName());
    }

    @PatchMapping("/password")
    public User updatePassword(Authentication auth,
                               @RequestParam String password) {
        return userService.updatePasswordByEmail(auth.getName(), password);
    }

    @DeleteMapping("/delete")
    public void deleteAccount(Authentication auth) {
        userService.deleteByEmail(auth.getName());
    }
}
