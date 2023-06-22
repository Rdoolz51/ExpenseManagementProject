package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.StatusDAO;
import com.revature.daos.UserDAO;

public class ReimbursementsService {


    private final ReimbursementDAO reimbursementDAO;

    private final UserDAO userDAO;

    private final StatusDAO statusDAO;


    public ReimbursementsService(ReimbursementDAO reimbursementDAO, UserDAO userDAO, StatusDAO statusDAO) {
        this.reimbursementDAO = reimbursementDAO;
        this.userDAO = userDAO;
        this.statusDAO = statusDAO;
    }
}
