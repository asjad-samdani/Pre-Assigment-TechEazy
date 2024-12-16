package com.Pre_BootCamp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Pre_BootCamp.model.Student;
import com.Pre_BootCamp.model.User;
import com.Pre_BootCamp.repository.StudentRepo;
import com.Pre_BootCamp.repository.UserRepo;

@Service
public class StudentService {
  @Autowired
  private StudentRepo studentRepo;
  @Autowired
  UserRepo userRepo;

  public Student addStudent(Student student) {
    return studentRepo.save(student);

  }

  public List<Student> getAllStudents() {
    return studentRepo.findAll();
  }

  public User getAuthByUsername(String username) {
    return userRepo.findByUsername(username);
  }

  public Student getStudentByAuth(User user) {
    return studentRepo.findById(user.getId())
        .orElseThrow(() -> new RuntimeException("Student not found"));

  }

}
