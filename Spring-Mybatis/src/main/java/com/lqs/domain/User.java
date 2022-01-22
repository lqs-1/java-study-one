package com.lqs.domain;

import java.util.Date;

public class User {

    private String username;
    private int id;

    private Role role;

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", role=" + role +
                '}';
    }

    public void setRole(Role role) {
        this.role = role;
    }
    //    private Date bd;

//    public Date getBd() {
//        return bd;
//    }
//
//    public void setBd(Date bd) {
//        this.bd = bd;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
