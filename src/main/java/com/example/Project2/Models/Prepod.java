package com.example.Project2.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Prepod
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "От 1 до 50 символов")
    private  String familia, name;

    @Size(min = 0, max = 50, message = "До 50 символов")
    private  String otch;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private  String predmeti, grafic;

    public Prepod(String Familia, String Name, String Otch, String Predmeti, String Grafic)
    {
        this.familia = Familia;
        this.name = Name;
        this.otch = Otch;
        this.predmeti = Predmeti;
        this.grafic = Grafic;
    }

    public Prepod() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtch() {
        return otch;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }

    public String getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(String predmeti) {
        this.predmeti = predmeti;
    }

    public String getGrafic() {
        return grafic;
    }

    public void setGrafic(String grafic) {
        this.grafic = grafic;
    }
}
















