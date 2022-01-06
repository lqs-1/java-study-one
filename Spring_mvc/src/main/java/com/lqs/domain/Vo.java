package com.lqs.domain;

import java.util.List;

public class Vo {

    private List<User> listUser;


    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    @Override
    public String toString() {
        return "Vo{" +
                "listUser=" + listUser +
                '}';
    }
}
