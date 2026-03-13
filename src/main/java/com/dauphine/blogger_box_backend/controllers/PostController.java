package com.dauphine.blogger_box_backend.controllers;

import com.dauphine.blogger_box_backend.dto.PostRequest;
import com.dauphine.blogger_box_backend.models.Post;
import com.dauphine.blogger_box_backend.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<List<Post>> getAllByCategoryId(@PathVariable UUID catId){
        return ResponseEntity.ok(service.getAllByCategoryId(catId));
    }

    @GetMapping
    @Operation(
            summary = "Get all posts",
            description = "Retrieve all posts or filter like title or content"
    )
    public ResponseEntity<List<Post>> getAll(@RequestParam(required = false) String value){
        List<Post> posts = value == null || value.isBlank()
                ?service.getAll()
                :service.getAllByTitleOrContent(value) ;
        return ResponseEntity.ok(posts);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Get post by id",
            description = "Retrieve a post by id"
    )
    public ResponseEntity<Post> getById(@PathVariable UUID id){
        Post post = service.getById(id);
        if (post == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping
    @Operation(
            summary = "Create new post",
            description = "Create new post, required fields : title, content and a category"
    )
    public ResponseEntity<Post> create(@RequestBody PostRequest postRequest) {
        Post post = service.create(postRequest.getTitle(), postRequest.getContent(), postRequest.getCategoryId());
        return ResponseEntity
                .created(URI.create("v1/posts/" + post.getId()))
                .body(post);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update an existing post",
            description = "Update new post, only the title, content and category can be updated"
    )
    public ResponseEntity<Post> update(@PathVariable UUID id, @RequestBody PostRequest postRequest) {
        Post post = service.update(id, postRequest.getTitle(), postRequest.getContent(), postRequest.getCategoryId());
        if (post == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
