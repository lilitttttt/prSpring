package com.example.Project2.repo;

import com.example.Project2.Models.Gruppa;
import com.example.Project2.Models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GruppaRepository extends CrudRepository<Gruppa,Long> {
    List<Gruppa> findByNazvanie(String nazvanie);
}
