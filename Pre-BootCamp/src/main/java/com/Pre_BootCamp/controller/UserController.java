package com.Pre_BootCamp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Pre_BootCamp.model.User;
import com.Pre_BootCamp.service.UserService;

@RestController
public class UserController {
  @Autowired
  UserService authService;

  @PostMapping("/authentication")
  public ResponseEntity<?> authentication(@RequestBody User auth) {
    try {
      String token = authService.userAuthentication(auth);
      if (token != null && !token.isEmpty()) {
        return ResponseEntity.ok(Map.of("token", token));

      } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Map.of("message", "Invalid username or password"));
      }

    } catch (Exception e) {

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Map.of("message", "Invalid username or password"));
    }
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody User user, @RequestParam String role) {
    User userResp = authService.register(user, role);

    if (userResp != null) {
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(Map.of("message", "Register successfully"));
    } else {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(Map.of("message", "Username already registered"));
    }
  }

}
