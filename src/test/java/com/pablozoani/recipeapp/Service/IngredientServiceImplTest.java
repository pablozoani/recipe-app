package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.command.IngredientCommand;
import com.pablozoani.recipeapp.converter.tocommand.IngredientToCommand;
import com.pablozoani.recipeapp.converter.tocommand.UnitOfMeasureToCommand;
import com.pablozoani.recipeapp.model.Ingredient;
import com.pablozoani.recipeapp.model.Recipe;
import com.pablozoani.recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

    IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientToCommand = new IngredientToCommand(new UnitOfMeasureToCommand());
        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToCommand);
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
}