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

    @JoinColumn(name = "person_fk", referencedColumnName = "person_id")
    @ManyToOne(targetEntity = Person.class)
    private int personId;

    @JoinColumn(name = "status_fk", referencedColumnName = "status_id")
    @ManyToOne(targetEntity = Status.class)
    private int status_id;

}
