package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.command.IngredientCommand;
import com.pablozoani.recipeapp.converter.tocommand.IngredientToCommand;
import com.pablozoani.recipeapp.model.Recipe;
import com.pablozoani.recipeapp.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;

    private final IngredientToCommand ingredientToCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToCommand ingredientToCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToCommand = ingredientToCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndId(Long recipeId, Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            // TODO implement error handling
            throw new RuntimeException(getClass().getSimpleName() + "/findByRecipeIdAndId()/1");
        }

        Optional<IngredientCommand> ingredientCommand = recipeOptional.get().getIngredients()
            .stream()
            .filter(ingredient -> ingredient.getId().equals(id))
            .map(ingredientToCommand::convert)
            .findFirst();

        if (!ingredientCommand.isPresent()) {
            // TODO implement error handling
            throw new RuntimeException(getClass().getSimpleName() + "/findByRecipeIdAndId()/2");
        }

        return ingredientCommand.orElse(null);
    }
}
