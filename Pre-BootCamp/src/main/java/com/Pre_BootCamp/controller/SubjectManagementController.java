package com.Pre_BootCamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Pre_BootCamp.model.Subject;
import com.Pre_BootCamp.service.SubjectService;

@RestController
public class SubjectManagementController {
  @Autowired
  SubjectService subjectService;

  @GetMapping("/getSubject")
  public ResponseEntity<?> getAllSubject() {
    List<Subject> subject = subjectService.getAllSubject();
    if (!subject.isEmpty()) {
      return new ResponseEntity<>(subject, HttpStatus.OK);

    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

  }

  @PostMapping("/addSubject")
  public ResponseEntity<?> addAllSubject(@RequestBody Subject subject) {
    Subject subjects = subjectService.addAllSubject(subject);
    if (subjects != null) {
      return new ResponseEntity<>(subject, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(subject, HttpStatus.INTERNAL_SERVER_ERROR);

    }
  }

}
