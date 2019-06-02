package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/post")
public class PostController {

    private PostRepository postRepository;
    @Autowired
    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @PostMapping
    public Post put(@RequestBody Post body){
        return postRepository.save(new Post(body.getTitle(), body.getContent()));
    }

    @GetMapping
    public ArrayList<Post> findAll() {
        return (ArrayList<Post>) postRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Post findOne(@PathVariable Long id) {
        return postRepository.findById(id).orElse(null);
    }

//    업데이트 기능 : 아직 Postman 으로만 실행 가능
    @PutMapping(value = "/{id}")
    public Post updatetitle(@PathVariable Long id, @RequestBody Post body) {
        Post post = postRepository.findById(id).orElse(null);
        post.setTitle(body.getTitle());
        post.setContent(body.getContent());
        return postRepository.save(post);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        postRepository.deleteById(id);
    }

}
