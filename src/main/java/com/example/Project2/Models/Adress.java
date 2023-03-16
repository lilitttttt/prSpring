package com.example.Project2.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "adress")
public class Adress
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 100, message = "От 1 до 50 символов")
    private  String adres;

    public Adress(String adres, University owner) {
        this.adres = adres;
        this.owner = owner;
    }

    public Adress() {
    }

    @OneToOne(optional = true, mappedBy = "adress", cascade = CascadeType.ALL)
    private University owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public University getOwner() {
        return owner;
    }

    public void setOwner(University owner) {
        this.owner = owner;
    }
}
















