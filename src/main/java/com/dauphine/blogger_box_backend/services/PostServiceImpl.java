package com.dauphine.blogger_box_backend.services;

import com.dauphine.blogger_box_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger_box_backend.exceptions.PostNotFoundByIdException;
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
    public Post getById(UUID id) throws PostNotFoundByIdException{
        return repository.findById(id)
                .orElseThrow(() -> new PostNotFoundByIdException(id));
    }

    @Override
    public Post create(String title, String content, UUID categoryId) throws CategoryNotFoundByIdException {
        Category category = categoryService.getById(categoryId);
        if (category == null) return null;
        Post post = new Post(title, content, category);
        return repository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content, UUID categoryId) throws PostNotFoundByIdException, CategoryNotFoundByIdException {
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

    @Override
    public List<Post> getAllByTitleOrContent(String value) {
        return repository.findAllByTitleOrContent(value);
    }
}
