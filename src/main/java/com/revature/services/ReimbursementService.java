package com.revature.services;

import com.revature.Exceptions.ReimbursementNotFoundException;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.StatusDAO;
import com.revature.daos.PersonDAO;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.stereotype.Service;
import com.revature.security.JwtGenerator;
import java.util.List;
import java.util.Optional;

@Service
public class ReimbursementService {


    private final ReimbursementDAO reimbursementDAO;

    private final PersonDAO personDAO;

    private final StatusDAO statusDAO;
    private final JwtGenerator jwtGenerator;


    public ReimbursementService(ReimbursementDAO reimbursementDAO, PersonDAO personDAO, StatusDAO statusDAO, JwtGenerator jwtGenerator) {
        this.reimbursementDAO = reimbursementDAO;
        this.personDAO = personDAO;
        this.statusDAO = statusDAO;
        this.jwtGenerator = jwtGenerator;


    }

    public List<Reimbursement> getAllReimbursements(){
        return  reimbursementDAO.findAll();
    }

    public Reimbursement getReimbursementById(int id){
        return reimbursementDAO.findById(id).orElseThrow(() -> new ReimbursementNotFoundException("No reimbursement found with id: " + id));
    }

    public Reimbursement addReimbursement(Reimbursement r, String token){
        String username = jwtGenerator.getUsernameFromToken(token);
        r.setPerson(personDAO.findByUsername(username).get());
        r.setStatus(statusDAO.findByName("Pending"));
        Reimbursement returnedReimbursement = reimbursementDAO.save(r);
        if (returnedReimbursement.getId() > 0){
            return returnedReimbursement;
        } else {
            return null;
        }
    }

    public Reimbursement approveOrDeny(int id, String newStatus){
        Reimbursement reimbursement = reimbursementDAO.findById(id).orElseThrow();
        reimbursement.setStatus(statusDAO.findByName(newStatus));
        return reimbursementDAO.save(reimbursement);
    }
    public Reimbursement updateReimbursement(Reimbursement r){
        return reimbursementDAO.save(r);
    }



}
