package com.example.ims.service;

import com.example.ims.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category category);
    Category deleteCategoryById(Integer id);
    Category updateCategoryById(Integer id, Category category);
}
