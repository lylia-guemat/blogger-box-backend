package com.dauphine.blogger_box_backend.controllers;

import com.dauphine.blogger_box_backend.dto.PostRequest;
import com.dauphine.blogger_box_backend.models.Post;
import com.dauphine.blogger_box_backend.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service){
        this.service = service;
    }

    @GetMapping("/category/{catId}")
    public List<Post> getAllByCategoryId(@PathVariable UUID catId){
        return service.getAllByCategoryId(catId);
    }

    @GetMapping
    public List<Post> getAll(@RequestParam(required = false) String value){
        List<Post> posts = value == null || value.isBlank()
                ?service.getAll()
                :service.getAllByTitleOrContent(value) ;
        return posts;
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    public Post create(@RequestBody PostRequest postRequest) {
        return service.create(postRequest.getTitle(), postRequest.getContent(), postRequest.getCategoryId());
    }

    @PutMapping("{id}")
    public Post update(@PathVariable UUID id, @RequestBody PostRequest postRequest) {
        return service.update(id, postRequest.getTitle(), postRequest.getContent(), postRequest.getCategoryId());
    }

    @DeleteMapping("{id}")
    public boolean deleteById(@PathVariable UUID id){
        return service.deleteById(id);
    }
}
