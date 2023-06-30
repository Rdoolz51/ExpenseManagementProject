package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.services.ReimbursementService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ReimbursementController {
    private final ReimbursementService reimbursementService;

    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    @GetMapping
    public List<Reimbursement> getAllReimbursements(){
        return reimbursementService.getAllReimbursements();
    }

    @GetMapping("/{id}")
    public Reimbursement getReimbursementById(@PathVariable("id")int id){
      return reimbursementService.getReimbursementById(id);
    }

    @PutMapping("/{id}")
    public Reimbursement updateReimbursement(@RequestBody Reimbursement reimbursement, @PathVariable("id") int id){
        reimbursement.setId(id);
        return reimbursementService.updateReimbursement(reimbursement);
    }

    @PostMapping
    public Reimbursement addReimbursement(@RequestBody Reimbursement reimbursement, @RequestHeader("Authorization") String token) {
        return reimbursementService.addReimbursement(reimbursement, token);
    }

    @PutMapping("/{id}/aod")
    public Reimbursement approveOrDeny(@PathVariable("id") int id, @RequestBody Status newStatus){
        return reimbursementService.approveOrDeny(id, newStatus.getName());
    }

}
