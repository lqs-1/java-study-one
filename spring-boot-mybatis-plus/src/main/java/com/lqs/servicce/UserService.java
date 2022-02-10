package com.lqs.servicce;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lqs.domain.User;

import java.util.List;

public interface UserService extends IService<User> {
    Boolean insert(User user);
    Boolean update(User user);
    Boolean delete(int id);
    User getById(int id);
    List<User> getAll();
    IPage<User> getPaginator(int currentPage, int page);
}
