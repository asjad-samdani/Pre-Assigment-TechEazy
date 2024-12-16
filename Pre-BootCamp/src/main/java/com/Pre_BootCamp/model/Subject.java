package com.Pre_BootCamp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "student_id", nullable = false)
  private Student student;

}
