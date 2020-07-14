package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.command.IngredientCommand;
import com.pablozoani.recipeapp.converter.fromcommand.CommandToIngredient;
import com.pablozoani.recipeapp.converter.tocommand.IngredientToCommand;
import com.pablozoani.recipeapp.model.Ingredient;
import com.pablozoani.recipeapp.model.Recipe;
import com.pablozoani.recipeapp.repository.RecipeRepository;
import com.pablozoani.recipeapp.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;

    private final IngredientToCommand ingredientToCommand;

    private final CommandToIngredient commandToIngredient;

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToCommand ingredientToCommand,
                                 CommandToIngredient commandToIngredient,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToCommand = ingredientToCommand;
        this.commandToIngredient = commandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
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
                                                                      .filter(
                                                                          ingredient -> ingredient.getId().equals(id))
                                                                      .map(ingredientToCommand::convert)
                                                                      .findFirst();

        if (!ingredientCommand.isPresent()) {
            // TODO implement error handling
            throw new RuntimeException(getClass().getSimpleName() + "/findByRecipeIdAndId()/2");
        }

        return ingredientCommand.orElse(null);
    }

    @Transactional
    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException(getClass().getSimpleName() + " - saveIngredientCommand() - 1");
        }

        log.debug(getClass().getSimpleName() + " - saveIngredientCommand() - 1.1");

        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe
            .getIngredients()
            .stream()
            .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
            .findFirst();

        if (ingredientOptional.isPresent()) {
            Ingredient ingredient = ingredientOptional.get();
            ingredient.setAmount(ingredientCommand.getAmount());
            ingredient.setDescription(ingredientCommand.getDescription());
            ingredient.setUnitOfMeasure(unitOfMeasureRepository.findById(ingredientCommand.getUnitOfMeasure()
                                                                                          .getId())
                                                               .orElseThrow(() -> new RuntimeException(
                                                                   getClass().getSimpleName() +
                                                                   " - saveIngredientCommand() - 2")));
        } else {
            log.debug(getClass().getSimpleName() + " - saveIngredientCommand() - 2.1");
            recipe.addIngredients(commandToIngredient.convert(ingredientCommand));
            log.debug(getClass().getSimpleName() + " - saveIngredientCommand() - 2.2");
        }

        log.debug(getClass().getSimpleName() + " - saveIngredientCommand() - 2.3");
        Recipe savedRecipe = recipeRepository.save(recipe);
        log.debug(getClass().getSimpleName() + " - saveIngredientCommand() - 3");

        // todo check for fail
        return ingredientToCommand
            .convert(savedRecipe.getIngredients()
                                .stream()
                                .filter(ingredient -> Objects
                                    .equals(ingredient.getDescription(), ingredientCommand.getDescription()))
                                .filter(ingredient -> Objects
                                    .equals(ingredient.getAmount(), ingredientCommand.getDescription()))
                                .filter(ingredient -> Objects.equals(ingredient.getUnitOfMeasure().getId(),
                                                                     ingredientCommand.getUnitOfMeasure().getId()))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException(
                                    getClass().getSimpleName() + " - saveIngredientCommand() - 3.1")));
    }

    @Override
    public void deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = recipe
                .getIngredients()
                .stream()
                .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientId))
                .findFirst();
            if (ingredientOptional.isPresent()) {
                Ingredient ingredient = ingredientOptional.get();
                recipe.deleteIngredient(ingredient);
                recipeRepository.save(recipe);
            } else {
                log.debug(getClass().getSimpleName() + " - deleteByRecipeIdAndIngredientId() - 1");
            }

        } else {
            log.debug(getClass().getSimpleName() + " - deleteByRecipeIdAndIngredientId() - 2");
        }
    }
}
