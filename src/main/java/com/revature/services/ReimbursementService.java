package com.revature.services;

import com.revature.DAOs.UserDAO;
import com.revature.DAOs.ReimbursementDAO;
import com.revature.models.DTOs.IncomingReimbursementDTO;
import com.revature.models.User;
import com.revature.models.Reimbursement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

// import com.revature.models.DTOs.*;

@Service
public class ReimbursementService {

    private UserDAO userDAO;
    private ReimbursementDAO reimbursementDAO;

    @Autowired
    public ReimbursementService(UserDAO userDAO, ReimbursementDAO reimbursementDAO) {
        this.userDAO = userDAO;
        this.reimbursementDAO = reimbursementDAO;
    }

    public Reimbursement createReimbursement(IncomingReimbursementDTO newReimbursement) {

        Reimbursement reimbursement = new Reimbursement(0, newReimbursement.getDescription(), newReimbursement.getAmount(), newReimbursement.getStatus(), null);

        Optional<User> user = userDAO.findById(newReimbursement.getUserId());

        if(user.isPresent()) {
            reimbursement.setUser(user.get());
            return reimbursementDAO.save(reimbursement);
        }
        else {
            return null;
        }
    }

    public List<Reimbursement> getAllReimbursements() {
        return reimbursementDAO.findAll();
    }

    public List<Reimbursement> getAllReimbursementsByStatus(String status) {
        return reimbursementDAO.findByStatus(status);
    }

    public List<Reimbursement> getReimbursementsByUserId(int userId) {
        return reimbursementDAO.findByUserUserId(userId);
    }

    public List<Reimbursement> getReimbursementsByStatusAndUserId(String status, int userId) {
        return reimbursementDAO.findByStatusAndUserUserId(status, userId);
    }

    public Reimbursement updateReimbursementStatus(String newStatus, int reimbId) {

        Optional<Reimbursement> existingReimbursement = reimbursementDAO.findById(reimbId);

        if (existingReimbursement.isPresent()) {
            Reimbursement reimbursement = existingReimbursement.get();
            reimbursement.setStatus(newStatus);
            return reimbursementDAO.save(reimbursement);
        }
        else {
            return null;
        }
    }

}
