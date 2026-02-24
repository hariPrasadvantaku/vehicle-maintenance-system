package com.hari.maintenance.service;

import org.springframework.stereotype.Service;

import com.hari.maintenance.model.User;
import com.hari.maintenance.repo.UserRepo;

@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User registerUser(User user) {
        return userRepo.save(user);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

  
    public User updatePasswordByEmail(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setPassword(password);
        return userRepo.save(user);
    }

    public User updateUserNameByEmail(String email, String name) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setName(name);
        return userRepo.save(user);
    }

    public void deleteByEmail(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        userRepo.delete(user);
    }
}
