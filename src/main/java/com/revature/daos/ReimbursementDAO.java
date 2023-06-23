package com.revature.daos;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbursementDAO extends JpaRepository<Reimbursement, Integer> {

    List<Reimbursement> findReimbursementByUser(User user);
}
