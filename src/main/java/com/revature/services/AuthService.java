package com.revature.services;

import com.revature.DAOs.AuthDAO;
import com.revature.controllers.AuthController;
import com.revature.models.DTOs.LoginDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AuthDAO authDAO;

    @Autowired
    public AuthService(AuthDAO authDAO) {
        this.authDAO = authDAO;
    }

    public OutgoingUserDTO login(LoginDTO loginDTO, HttpSession session) {

        User user = authDAO.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if(user == null) {
            return null;
        }
        else {
            OutgoingUserDTO outgoingUserDTO = new OutgoingUserDTO(user.getUserId(), user.getUsername(), user.getRole());

            session.setAttribute("userId", user.getUserId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            return outgoingUserDTO;
        }
    }

}
