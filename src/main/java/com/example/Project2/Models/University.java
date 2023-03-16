package com.example.Project2.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "university")
public class University
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 100, message = "От 1 до 50 символов")
    private  String nazvanie;


    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="adress_id")
    private Adress adress;

    @OneToMany (mappedBy = "universitys", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Student> students;

    public University(String nazvanie, Adress adress) {
        this.nazvanie = nazvanie;
        this.adress = adress;
    }

    public University() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
















