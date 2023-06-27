package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reimbursements")
public class Reimbursement {


    @Id
    @Column(name = "reimburse_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "reimbursement_person_fk", referencedColumnName = "person_id")
    private Person person;

    @ManyToOne(targetEntity = Status.class)
    @JoinColumn(name = "reimbursement_status_fk")
    private Status status;

}
