package com.utkasrh.projects.usermngt.repository;

import com.utkasrh.projects.usermngt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}