package com.example.ims.service;

import com.example.ims.dao.CategoryRepository;
import com.example.ims.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @Override
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }
    @Override
    public Category deleteCategoryById(Integer id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Category with id " + id + " not found"));
        categoryRepository.delete(category);
        return category;
    }
    @Override
    public Category updateCategoryById(Integer id, Category newCategory){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Category with id " + id + " not found"));
        if (category.getName() != null) {
            category.setName(newCategory.getName());
        }
        if (category.getDescription() != null) {
            category.setDescription(newCategory.getDescription());
        }
        return categoryRepository.save(category);
    }
}
