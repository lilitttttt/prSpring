package com.example.Project2.repo;

import com.example.Project2.Models.Adress;
import org.springframework.data.repository.CrudRepository;

public interface AdressRepository extends CrudRepository<Adress, Long> {
    Adress findByAdres(String adres);
}