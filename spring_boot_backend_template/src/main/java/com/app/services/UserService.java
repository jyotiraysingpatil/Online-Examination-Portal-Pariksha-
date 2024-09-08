package com.app.services;

import java.util.List;
import com.app.entities.User;

public interface UserService {
    User addNew(User user);
    User updateUser(User user);
    List<User> getAllUsers();
    void deleteUser(Long userId); 
    User getByUserId(Long userId);
}
