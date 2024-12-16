package com.Pre_BootCamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Pre_BootCamp.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

  User findByUsername(String username);

}
