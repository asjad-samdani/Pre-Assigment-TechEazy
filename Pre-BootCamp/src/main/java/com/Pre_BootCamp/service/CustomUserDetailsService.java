package com.Pre_BootCamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Pre_BootCamp.model.User;
import com.Pre_BootCamp.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User name not found");

    }
    return user;

  }

}
