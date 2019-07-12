package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView modify (@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("modify");
        modelAndView.addObject(postRepository.findById(id).orElse(null));
        return modelAndView;
    }

    @PutMapping(value = "/{id}")
    public Post updatetitle(@PathVariable Long id, @RequestBody Post body) {
        Post post = postRepository.findById(id).orElse(null);
        assert post != null;
        post.setTitle(body.getTitle());
        post.setContent(body.getContent());
        return postRepository.save(post);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        postRepository.deleteById(id);
    }

}
