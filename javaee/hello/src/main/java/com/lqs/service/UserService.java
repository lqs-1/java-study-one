package com.lqs.service;

import com.lqs.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> find();
}
