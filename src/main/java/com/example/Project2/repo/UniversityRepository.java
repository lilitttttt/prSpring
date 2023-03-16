package com.example.Project2.repo;

import com.example.Project2.Models.University;
import org.springframework.data.repository.CrudRepository;

public interface UniversityRepository extends CrudRepository<University, Long> {

    University findByNazvanie(String nazvanie);
}