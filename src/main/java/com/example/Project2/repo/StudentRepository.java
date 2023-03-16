package com.example.Project2.repo;

import com.example.Project2.Models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {
    List<Student> findByfamilia(String familia);
    List<Student> findByfamiliaContains(String familia);
}
