package com.revature.models;

import javax.persistence.*;

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

    @ManyToOne
    private User user;

    @ManyToOne
    private Status status;

    public Reimbursement(int id, int amount, String description, User user, Status status) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.user = user;
        this.status = status;
    }

    public Reimbursement(int amount, String description, User user, Status status) {
        this.amount = amount;
        this.description = description;
        this.user = user;
        this.status = status;
    }

    public Reimbursement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", status=" + status +
                '}';
    }
}
