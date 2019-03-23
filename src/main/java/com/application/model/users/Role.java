package com.application.model.users;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name="role_seq", sequenceName = "ROLE_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role")
    private String role;



    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
