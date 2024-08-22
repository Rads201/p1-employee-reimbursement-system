package com.revature.services;

import com.revature.DAOs.UserDAO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User registerUser(User user) throws Exception {
        return userDAO.save(user);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public int deleteUserById(int userId) {

        if (userDAO.existsById(userId)) {
            userDAO.deleteById(userId);
            return 1;
        }

        return 0;
    }

    public User updatePassword(String newPassword, int userId) {

        Optional<User> existingUser = userDAO.findById(userId);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setPassword(newPassword);
            return userDAO.save(user);
        }
        else {
            return null;
        }
    }

}
