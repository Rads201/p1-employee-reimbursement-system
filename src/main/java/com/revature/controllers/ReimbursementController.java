package com.revature.controllers;

import com.revature.DAOs.UserDAO;
import com.revature.models.DTOs.IncomingReimbursementDTO;
import com.revature.models.User;
import com.revature.models.Reimbursement;
import com.revature.services.UserService;
import com.revature.services.ReimbursementService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin(origins = "http://localhost:3000")
public class ReimbursementController {

    private UserService userService;
    private ReimbursementService reimbursementService;

    @Autowired
    public ReimbursementController(UserService userService, ReimbursementService reimbursementService) {
        this.userService = userService;
        this.reimbursementService = reimbursementService;
    }

    @PostMapping
    public ResponseEntity<Object> addReimbursement(@RequestBody IncomingReimbursementDTO newReimbursement) {

        Reimbursement reimbursement = reimbursementService.createReimbursement(newReimbursement);

        if(reimbursement == null) {
            return ResponseEntity.status(400).body("Couldn't find User with ID: " + newReimbursement.getUserId());
        }

        return ResponseEntity.status(201).body(reimbursement);
    }

    @GetMapping
    public ResponseEntity<List<Reimbursement>> getAllReimbursements() {
        return ResponseEntity.ok(reimbursementService.getAllReimbursements());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reimbursement>> getAllReimbursementsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(reimbursementService.getAllReimbursementsByStatus(status));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reimbursement>> getAllReimbursementsById(@PathVariable int userId) {
        return ResponseEntity.ok(reimbursementService.getReimbursementsByUserId(userId));
    }

    @GetMapping("/status/{status}/user/{userId}")
    public ResponseEntity<List<Reimbursement>> getAllReimbursementsByStatusAndUserId(@PathVariable String status, @PathVariable int userId) {
        return ResponseEntity.ok(reimbursementService.getReimbursementsByStatusAndUserId(status, userId));
    }

    @PatchMapping("/{reimbId}")
    public ResponseEntity<Object> updateStatus(@RequestBody String status, @PathVariable int reimbId) {
        Reimbursement reimbursement = reimbursementService.updateReimbursementStatus(status, reimbId);
        if (reimbursement == null) {
            return ResponseEntity.status(400).body("Reimbursement not found with ID: " + reimbId);
        }
        else {
            return ResponseEntity.ok(reimbursement);
        }
    }
}
