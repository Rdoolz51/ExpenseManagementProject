package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "statuses")
public class Status {

    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status_name", unique = true, nullable = false)
    private String statusName;


    public Status(int id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public Status(String statusName) {
        this.statusName = statusName;
    }

    public Status() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
