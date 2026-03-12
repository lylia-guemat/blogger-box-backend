package com.dauphine.blogger_box_backend.services;

import com.dauphine.blogger_box_backend.models.Category;
import com.dauphine.blogger_box_backend.models.Post;
import com.dauphine.blogger_box_backend.repositories.CategoryRepository;
import com.dauphine.blogger_box_backend.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final CategoryService categoryService;

    public PostServiceImpl(PostRepository repository,CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return repository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Post> getAll(){
        return repository.findAll() ;
    }

    @Override
    public Post getById(UUID id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Category category = categoryService.getById(categoryId);
        if (category == null) return null;
        Post post = new Post(title, content, category);
        return repository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content, UUID categoryId) {
        Post post = getById(id);
        if (post == null) return null;
        Category category = categoryService.getById(categoryId);
        if (category == null) return null;
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
        return repository.save(post);
    }

    @Override
    public boolean deleteById(UUID id){
        repository.deleteById(id);
        return true;
    }
}
