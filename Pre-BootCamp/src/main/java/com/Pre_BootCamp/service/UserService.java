package com.Pre_BootCamp.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Pre_BootCamp.model.User;
import com.Pre_BootCamp.repository.UserRepo;

@Service
public class UserService {
  @Autowired
  private UserRepo authRepo;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtService jwtService;
  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  // login
  public String userAuthentication(User auth) {
    try {
      Authentication authenticator = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
      if (authenticator.isAuthenticated()) {
        return jwtService.generateToken(auth.getUsername());

      } else {
        throw new AuthenticationException("Authentication failed");
      }

    } catch (AuthenticationException e) {
      e.printStackTrace();
      throw new RuntimeException("Invalid username or password", e);

    }

  }

  // register
  public User register(User user, String role) {
    if (authRepo.findByUsername(user.getUsername()) != null) {
      return null;
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(role.toUpperCase());
    return authRepo.save(user);

  }

}
