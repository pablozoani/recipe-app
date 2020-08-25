package com.pablozoani.recipeapp.repository;

import com.pablozoani.recipeapp.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
