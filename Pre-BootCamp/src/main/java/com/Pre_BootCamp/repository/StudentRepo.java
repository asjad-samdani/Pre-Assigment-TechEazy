package com.Pre_BootCamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Pre_BootCamp.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

}