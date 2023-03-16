package com.example.Project2.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Student
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
    @Size(min = 1, max = 50, message = "От 1 до 50 символов")
    private  String grupa;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 10, max = 10, message = "Должно быть 10 символов")
    private  String birthday;

    @ManyToMany (cascade = CascadeType.DETACH)
    @JoinTable (name="student_gruppa",
            joinColumns=@JoinColumn (name="student_id"),
            inverseJoinColumns=@JoinColumn(name="gruppa_id"))
    private List<Gruppa> gruppas;

    public University getUniversitys() {
        return universitys;
    }

    public void setUniversitys(University universitys) {
        this.universitys = universitys;
    }



    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private University universitys;


    public List<Gruppa> getGruppas() {
        return gruppas;
    }

    public void setGruppas(List<Gruppa> gruppas) {
        this.gruppas = gruppas;
    }

    public Student(String familia, String name, String otch, String grupa, String birthday, List<Gruppa> gruppas, University universitys) {
        this.familia = familia;
        this.name = name;
        this.otch = otch;
        this.grupa = grupa;
        this.birthday = birthday;
        this.gruppas = gruppas;
        this.universitys = universitys;
    }

    public Student() {

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

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
















