package com.hari.maintenance.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hari.maintenance.dto.PasswordRequest;
import com.hari.maintenance.dto.UserDTO;
import com.hari.maintenance.dto.UserMapper;
import com.hari.maintenance.model.User;
import com.hari.maintenance.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDTO register(@Valid @RequestBody User user) {
        User saved = userService.registerUser(user);
        return UserMapper.toDTO(saved);
    }

    @GetMapping("/me")
    public UserDTO getDetails(Authentication auth) {
        User user =  userService.findByEmail(auth.getName());
        return UserMapper.toDTO(user);
    }

    @PatchMapping("/password")
    public String updatePassword(Authentication auth,
                               @RequestBody PasswordRequest request) {
        userService.updatePasswordByEmail(auth.getName(), request.getPassword());
        return "Password updated Successfully";
    }

    @DeleteMapping("/delete")
    public void deleteAccount(Authentication auth) {
        userService.deleteByEmail(auth.getName());
    }
}
