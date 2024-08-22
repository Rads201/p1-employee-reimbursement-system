package com.revature.controllers;

import com.revature.models.DTOs.LoginDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="http://localhost:3000", allowCredentials = "true")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PatchMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpSession session) {

        OutgoingUserDTO outgoingUserDTO = authService.login(loginDTO, session);

        if(outgoingUserDTO == null) {
            return ResponseEntity.status(401).body("Invalid Credentials! Try again");
        }

        return ResponseEntity.accepted().body(outgoingUserDTO);
    }
}
