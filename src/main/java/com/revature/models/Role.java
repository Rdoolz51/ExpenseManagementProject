package com.revature.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_title", unique = true, nullable = false)
    private String roleTitle;


    public Role(int id, String roleTitle) {
        this.id = id;
        this.roleTitle = roleTitle;
    }

    public Role() {
    }

    public Role(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleTitle='" + roleTitle + '\'' +
                '}';
    }
}
