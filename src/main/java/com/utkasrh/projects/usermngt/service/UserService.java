package com.utkasrh.projects.usermngt.service;

import com.utkasrh.projects.usermngt.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}