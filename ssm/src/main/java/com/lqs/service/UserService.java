package com.lqs.service;

import com.lqs.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    public void save(User user) throws IOException;
    public List<User> findAll() throws IOException;
}
