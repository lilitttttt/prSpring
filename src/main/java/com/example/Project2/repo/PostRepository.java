package com.example.Project2.repo;

import com.example.Project2.Models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post,Long> {
    List<Post> findByTitle(String title);
    List<Post> findByTitleContains(String title);
}
