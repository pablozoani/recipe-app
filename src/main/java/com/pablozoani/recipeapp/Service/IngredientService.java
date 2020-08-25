package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.command.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndId(String recipeId, String id);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteByRecipeIdAndIngredientId(String recipeId, String ingredientId);
}
