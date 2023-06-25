package com.revature.services;

import com.revature.Exceptions.ReimbursementNotFoundException;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.StatusDAO;
import com.revature.daos.PersonDAO;
import com.revature.models.Reimbursement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReimbursementService {


    private final ReimbursementDAO reimbursementDAO;

    private final PersonDAO personDAO;

    private final StatusDAO statusDAO;


    public ReimbursementService(ReimbursementDAO reimbursementDAO, PersonDAO personDAO, StatusDAO statusDAO) {
        this.reimbursementDAO = reimbursementDAO;
        this.personDAO = personDAO;
        this.statusDAO = statusDAO;


    }

    public List<Reimbursement> getAllReimbursements(){
        return  reimbursementDAO.findAll();
    }

    public Reimbursement getReimbursementById(int id){
        return reimbursementDAO.findById(id).orElseThrow(() -> new ReimbursementNotFoundException("No reimbursement found with id: " + id));
    }

    public boolean deleteReimbursementByID(int id){
        reimbursementDAO.deleteById(id);
        if(!reimbursementDAO.existsById(id)){
            return true;
        } else {
            return false;
        }
    }

    public Reimbursement addReimbursement(Reimbursement r){
        Reimbursement returnedReimbursement = reimbursementDAO.save(r);
        if (returnedReimbursement.getId() > 0){
            return returnedReimbursement;
        } else {
            return null;
        }
    }

    public Reimbursement updateReimbursement(Reimbursement r){
        return reimbursementDAO.save(r);
    }


}
