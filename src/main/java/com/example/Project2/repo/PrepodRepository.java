package com.example.Project2.repo;

import com.example.Project2.Models.Prepod;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrepodRepository extends CrudRepository<Prepod,Long> {
    List<Prepod> findByfamilia(String familia);
    List<Prepod> findByfamiliaContains(String familia);
}
