package com.example.Project2.Controllers;


import com.example.Project2.Models.Adress;
import com.example.Project2.Models.Post;
import com.example.Project2.Models.University;
import com.example.Project2.repo.AdressRepository;
import com.example.Project2.repo.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class OneToOneController
{
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private AdressRepository adressRepository;

    @GetMapping("/adres")
    public String Adres(Model model){
        Iterable<Adress> adresses = adressRepository.findAll();
        model.addAttribute("adress", adresses);
        return "adres";
    }
    @PostMapping("/adres/{id}/remove")
    public String AdresDelete(@PathVariable("id") long id, Model model){
        Adress adress = adressRepository.findById(id).orElseThrow();
        adressRepository.delete(adress);
        return "redirect:/adres";
    }
    @PostMapping("/university/{id}/remove")
    public String universityDelete(@PathVariable("id") long id, Model model){
        University university = universityRepository.findById(id).orElseThrow();
        universityRepository.delete(university);
        return "redirect:/university";
    }

    @GetMapping("/university")
    public String Univer(Model model){
        Iterable<University> universities = universityRepository.findAll();
        model.addAttribute("universitys", universities);
        return "university";
    }

    @GetMapping("/university/add")
    public String universityAdd(Model model){
        Iterable<Adress> adresses = adressRepository.findAll();
        ArrayList<Adress> adressArrayList = new ArrayList<>();
        for (Adress pass:adresses)
        {
            if(pass.getOwner() == null)
            {
                adressArrayList.add(pass);
            }
        }
        model.addAttribute("adress", adressArrayList);
        return "university-add";
    }
    @PostMapping("/university/add")
    public String universityPAdd(@RequestParam String adres, @RequestParam String nazvanie, Model model)
    {
        Adress adress = adressRepository.findByAdres(adres);
        University university = new University(nazvanie,adress);
        universityRepository.save(university);
        return "redirect:/university";
    }

    @GetMapping("/adres/add")
    public String adresAdd(Adress adress, Model model)
    {
        return "adres-add";
    }
    @PostMapping("/adres/add")
    public String adresPAdd(@ModelAttribute("adress")@Valid Adress adress, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "adres-add";
        }
        adressRepository.save(adress);
        return "redirect:/adres";
    }

}
