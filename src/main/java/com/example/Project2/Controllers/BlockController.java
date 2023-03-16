package com.example.Project2.Controllers;

import com.example.Project2.Models.*;
import com.example.Project2.repo.PrepodRepository;
import com.example.Project2.repo.StudentRepository;
import com.example.Project2.repo.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.Project2.repo.PostRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class BlockController
{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PrepodRepository prepodRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    public UniversityRepository universityRepository;

    @GetMapping("/")
    public String Home(Model model)
    {
        return "Home";
    }

    @GetMapping("/blog")
    public String GetBlog(Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);
        return "block-main";
    }
    @GetMapping("/students")
    public String GetStudent(Model model)
    {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students",students);
        Iterable<University> universities = universityRepository.findAll();
        model.addAttribute("university",universities);
        return "student-main";
    }
    @GetMapping("/prepods")
    public String GetPrepod(Model model)
    {
        Iterable<Prepod> prepods = prepodRepository.findAll();
        model.addAttribute("prepods",prepods);
        return "prepod-main";
    }


    @GetMapping("/blog/add")
    public String blogAdd(Post post, Model model)
    {
        return "blog-add";
    }
    @GetMapping("/student/add")
    public String studentAdd(Student student,Model model)
    {
        Iterable<University> universities = universityRepository.findAll();
        model.addAttribute("university",universities);
        return "student-add";
    }
    @GetMapping("/prepod/add")
    public String prepodAdd(Prepod prepod,Model model)
    {
        return "prepod-add";
    }


    //    @PostMapping("/blog/add")
//    public String blogPostAdd(@RequestParam(value="title") String title,
//                              @RequestParam(value ="anons") String anons,
//                              @RequestParam(value = "full_text") String full_text,Model model)
//    {
//        Post post = new Post(title,anons,full_text);
//        postRepository.save(post);
//        return "redirect:/blog";
//    }
    @PostMapping("/blog/add")
    public String blogPostAdd(@ModelAttribute("post")@Valid Post post, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {

            return "blog-add";
        }
        postRepository.save(post);
        return "redirect:/blog";
    }
    @PostMapping("/student/add")
    public String studentAdd(@ModelAttribute("student")@Valid Student student, @RequestParam Long universitys, BindingResult bindingResult,
                             Model model)
    {
        if(bindingResult.hasErrors())
        {
            Iterable<University> universities = universityRepository.findAll();
            model.addAttribute("university",universities);
            return "student-add";
        }
        University university = universityRepository.findById(universitys).orElseThrow();
        student.setUniversitys(university);
        studentRepository.save(student);
        return "redirect:/students";
    }
    @PostMapping("/prepod/add")
    public String prepodAdd(@ModelAttribute("prepod")@Valid Prepod prepod, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "prepod-add";
        }
        prepodRepository.save(prepod);
        return "redirect:/prepods";
    }


    @GetMapping("/blog/filter")
    public String blogFilter(Model model)
    {
        return "blog-filter";
    }
    @GetMapping("/student/filter")
    public String studentFilter(Model model)
    {
        return "student-filter";
    }
    @GetMapping("/prepod/filter")
    public String prepodFilter(Model model)
    {
        return "prepod-filter";
    }


    @PostMapping("/blog/filter/result")
    public String blogResult(@RequestParam String title, Model model)
    {
//        List<Post> result = postRepository.findByTitle(title);
        List<Post> result = postRepository.findByTitleContains(title);
        model.addAttribute("result", result);
        return "blog-filter";
    }
    @PostMapping("/student/filter/result")
    public String studentResult(@RequestParam String familia, Model model)
    {
//        List<Student> result = studentRepository.findByTitle(Familia);
        List<Student> result = studentRepository.findByfamiliaContains(familia);
        model.addAttribute("result", result);
        return "student-filter";
    }
    @PostMapping("/prepod/filter/result")
    public String prepodResult(@RequestParam String familia, Model model)
    {
        List<Prepod> result = prepodRepository.findByfamilia(familia);
//        List<Prepod> result = prepodRepository.findByTitleContains(Familia);
        model.addAttribute("result", result);
        return "prepod-filter";
    }


    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        if(!postRepository.existsById(id))
        {
            return "redirect:/blog";
        }
        return "blog-details";
    }
//    @GetMapping("/blog/{id}/edit")
//    public String blogEdit(@PathVariable("id")long id,
//                           Model model)
//    {
//        if(!postRepository.existsById(id)){
//            return "redirect:/blog";
//        }
//        Optional<Post> post = postRepository.findById(id);
//        ArrayList<Post> res = new ArrayList<>();
//        post.ifPresent(res::add);
//        model.addAttribute("post",res);
//        return "blog-edit";
//    }
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable("id")long id, Model model)
    {
        Post res = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id:" + id));
        model.addAttribute("post",res);
        return "blog-edit";
    }
//    @PostMapping("/blog/{id}/edit")
//    public String blogPostUpdate(@PathVariable("id")long id,
//                                 @RequestParam String title,
//                                 @RequestParam String anons,
//                                 @RequestParam String full_text,
//                                 Model model)
//    {
//        Post post = postRepository.findById(id).orElseThrow();
//        post.setTitle(title);
//        post.setAnons(anons);
//        post.setFull_text(full_text);
//        postRepository.save(post);
//        return "redirect:/blog";
//    }
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable("id")long id, @ModelAttribute("post")@Valid Post post, BindingResult bindingResult)
    {
        post.setId(id);
        if(bindingResult.hasErrors())
        {
            return "blog-edit";
        }
        postRepository.save(post);
        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}/remove")
    public String blogBlogDelete(@PathVariable("id") long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }


    @GetMapping("/prepod/{id}")
    public String prepodDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Prepod> prepod = prepodRepository.findById(id);
        ArrayList<Prepod> res = new ArrayList<>();
        prepod.ifPresent(res::add);
        model.addAttribute("prepod", res);
        if(!prepodRepository.existsById(id))
        {
            return "redirect:/prepods";
        }
        return "prepod-details";
    }
    @GetMapping("/prepod/{id}/edit")
    public String prepodEdit(@PathVariable("id")long id, Model model)
    {
        Prepod res = prepodRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id:" + id));
        model.addAttribute("prepod",res);
        return "prepod-edit";
    }
    @PostMapping("/prepod/{id}/edit")
    public String PrepodUpdate(@PathVariable("id")long id, @ModelAttribute("prepod")@Valid Prepod prepod, BindingResult bindingResult)
    {
        prepod.setId(id);
        if(bindingResult.hasErrors())
        {
            return "prepod-edit";
        }
        prepodRepository.save(prepod);
        return "redirect:/prepods";
    }
    @PostMapping("/prepod/{id}/remove")
    public String PrepodDelete(@PathVariable("id") long id, Model model){
        Prepod prepod = prepodRepository.findById(id).orElseThrow();
        prepodRepository.delete(prepod);
        return "redirect:/prepods";
    }


    @GetMapping("/student/{id}")
    public String studentDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Student> student = studentRepository.findById(id);
        ArrayList<Student> res = new ArrayList<>();
        student.ifPresent(res::add);
        model.addAttribute("student", res);
        if(!studentRepository.existsById(id))
        {
            return "redirect:/students";
        }
        return "student-details";
    }
    @GetMapping("/student/{id}/edit")
    public String studentEdit(@PathVariable("id")long id, Model model)
    {
        Student res = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Неверный id:" + id));
        model.addAttribute("student",res);
        return "student-edit";
    }
    @PostMapping("/student/{id}/edit")
    public String StudentUpdate(@PathVariable("id")long id, @ModelAttribute("student")@Valid Student student, BindingResult bindingResult)
    {
        student.setId(id);
        if(bindingResult.hasErrors())
        {
            return "student-edit";
        }
        studentRepository.save(student);
        return "redirect:/students";
    }
    @PostMapping("/student/{id}/remove")
    public String StudentDelete(@PathVariable("id") long id, Model model){
        Student student = studentRepository.findById(id).orElseThrow();
        studentRepository.delete(student);
        return "redirect:/students";
    }
}
