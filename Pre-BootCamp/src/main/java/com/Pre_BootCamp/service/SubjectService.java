package com.Pre_BootCamp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Pre_BootCamp.model.Student;
import com.Pre_BootCamp.model.Subject;
import com.Pre_BootCamp.repository.StudentRepo;
import com.Pre_BootCamp.repository.SubjectRepo;

@Service
public class SubjectService {
  @Autowired
  SubjectRepo subjectRepo;
  @Autowired
  private StudentRepo studentRepo;

  public List<Subject> getAllSubject() {
    return subjectRepo.findAll();

  }

  public Subject addAllSubject(Subject subject) {
    Student student = studentRepo.findById(subject.getStudent().getId())
        .orElseThrow(() -> new RuntimeException("Student Not found"));
    subject.setStudent(student);

    return subjectRepo.save(subject);

  }

}
