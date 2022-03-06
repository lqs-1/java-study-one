package com.lqs.service;

import com.lqs.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User findByName(User user);
}
