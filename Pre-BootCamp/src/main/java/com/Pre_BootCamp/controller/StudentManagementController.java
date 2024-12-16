package com.Pre_BootCamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Pre_BootCamp.model.Student;
import com.Pre_BootCamp.model.User;
import com.Pre_BootCamp.service.StudentService;

@RestController
public class StudentManagementController {
  @Autowired
  private StudentService studentService;

  @GetMapping("/getStudent")
  public ResponseEntity<?> getAllStudent() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();

    User currentUser = studentService.getAuthByUsername(username);

    if ("ROLE_ADMIN".equals(currentUser.getRole())) {
      List<Student> students = studentService.getAllStudents();
      return new ResponseEntity<>(students, HttpStatus.OK);

    } else {
      Student student = studentService.getStudentByAuth(currentUser);
      return new ResponseEntity<>(student, HttpStatus.NO_CONTENT);

    }

  }

  @PostMapping("/addStudent")
  public ResponseEntity<Student> addStudent(@RequestBody Student student) {
    {
      Student std = studentService.addStudent(student);
      if (std != null) {
        return new ResponseEntity<>(std, HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

  }

}
