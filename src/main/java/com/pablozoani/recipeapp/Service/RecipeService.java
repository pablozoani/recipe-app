package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.command.RecipeCommand;
import com.pablozoani.recipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(String id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findRecipeCommandById(String id);

    void deleteById(String id);
}
