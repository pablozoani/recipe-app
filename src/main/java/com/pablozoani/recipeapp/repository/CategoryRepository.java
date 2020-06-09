package com.pablozoani.recipeapp.repository;

import com.pablozoani.recipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
