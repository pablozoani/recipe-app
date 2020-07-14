package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.command.IngredientCommand;
import com.pablozoani.recipeapp.command.UnitOfMeasureCommand;
import com.pablozoani.recipeapp.converter.fromcommand.CommandToIngredient;
import com.pablozoani.recipeapp.converter.fromcommand.CommandToUnitOfMeasure;
import com.pablozoani.recipeapp.converter.tocommand.IngredientToCommand;
import com.pablozoani.recipeapp.converter.tocommand.UnitOfMeasureToCommand;
import com.pablozoani.recipeapp.model.Ingredient;
import com.pablozoani.recipeapp.model.Recipe;
import com.pablozoani.recipeapp.repository.RecipeRepository;
import com.pablozoani.recipeapp.repository.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    IngredientToCommand ingredientToCommand;

    CommandToIngredient commandToIngredient;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientToCommand = new IngredientToCommand(new UnitOfMeasureToCommand());
        commandToIngredient = new CommandToIngredient(new CommandToUnitOfMeasure());
        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToCommand, commandToIngredient,
                                                      unitOfMeasureRepository);
    }

    @Test
    void findByRecipeIdAndId() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Ingredient i1 = new Ingredient();
        i1.setId(1L);
        Ingredient i2 = new Ingredient();
        i2.setId(2L);
        Ingredient i3 = new Ingredient();
        i3.setId(3L);
        recipe.addIngredients(i1, i2, i3);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndId(1L, 3L);

        assertNotNull(ingredientCommand);
        assertEquals(ingredientCommand.getId(), 3L);
        assertEquals(ingredientCommand.getRecipeId(), 1L);
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void saveIngredientCommand() {
        IngredientCommand input = new IngredientCommand();
        input.setId(7L);
        UnitOfMeasureCommand uomC = new UnitOfMeasureCommand();
        uomC.setId(29L);
        input.setUnitOfMeasure(uomC);
        Recipe r = new Recipe();
        r.setId(17L);
        input.setRecipeId(17L);
        r.addIngredients(commandToIngredient.convert(input));

        Mockito.when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(r));
        Mockito.when(recipeRepository.save(any())).thenReturn(r);
        Mockito.when(unitOfMeasureRepository.findById(anyLong()))
               .thenReturn(Optional.of(new CommandToUnitOfMeasure().convert(uomC)));

        IngredientCommand output = ingredientService.saveIngredientCommand(input);

        assertEquals(r.getIngredients().iterator().next().getId(), output.getId());
        assertEquals(17L, output.getRecipeId());
    }

    @Test
    void deleteByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(7L);
        recipe.addIngredients(ingredient);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        Mockito.when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        ingredientService.deleteByRecipeIdAndIngredientId(6L, 7L);

        Mockito.verify(recipeRepository, times(1)).findById(anyLong());
        Mockito.verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}