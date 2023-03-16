package com.example.Project2.Controllers;

import com.example.Project2.Models.Adress;
import com.example.Project2.Models.Gruppa;
import com.example.Project2.Models.Student;
import com.example.Project2.repo.GruppaRepository;
import com.example.Project2.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class ManyToManyController
{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GruppaRepository gruppaRepository;

    @GetMapping("/grupps")
    private String Grupps(Model model){
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        Iterable<Gruppa> gruppa = gruppaRepository.findAll();
        model.addAttribute("gruppa", gruppa);
        return "grupps";
    }

    @PostMapping("/grupps/add")
    public String GruppaAdd(@RequestParam Long student, @RequestParam Long gruppas, Model model)
    {
        Student student2 = studentRepository.findById(student).orElseThrow();
        Gruppa gruppa2 = gruppaRepository.findById(gruppas).orElseThrow();
        student2.getGruppas().add(gruppa2);
        gruppa2.getStudents().add(student2);
        studentRepository.save(student2);
        gruppaRepository.save(gruppa2);
        return "redirect:/grupps";
    }

    @GetMapping("/grupps/add")
    public String gruppsAdd(Model model){
        Iterable<Gruppa> gruppas = gruppaRepository.findAll();
        model.addAttribute("gruppas", gruppas);
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "grupa-add";
    }

    @PostMapping("/grupps/{id}/remove")
    public String GruppaDelete(@PathVariable("id") long id, Model model){
        Gruppa gruppa = gruppaRepository.findById(id).orElseThrow();
        gruppaRepository.delete(gruppa);
        return "redirect:/grupps";
    }
}
