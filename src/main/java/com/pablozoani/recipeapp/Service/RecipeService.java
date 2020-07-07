package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);
}
