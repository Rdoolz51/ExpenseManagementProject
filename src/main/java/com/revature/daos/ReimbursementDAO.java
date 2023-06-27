package com.revature.daos;

import com.revature.models.Person;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbursementDAO extends JpaRepository<Reimbursement, Integer> {

    List<Reimbursement> findReimbursementByUser(Person person);

    List<Reimbursement> findByStatus(Status status);
}
