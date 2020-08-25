package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.exception.NotFoundException;
import com.pablozoani.recipeapp.model.Recipe;
import com.pablozoani.recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, null, null);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRecipesById() {
        Recipe recipe = new Recipe();
        recipe.setId("20l");
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
        Recipe recipe2 = recipeService.findById("20l");
        assertEquals(recipe, recipe2);
        verify(recipeRepository, times(1)).findById(anyString());
    }

    @Test
    void getRecipeByIdNotFoundTest() {
        Exception exception = assertThrows(NotFoundException.class, () -> {
            Optional<Recipe> recipeOptional = Optional.empty();

            when(recipeRepository.findById(ArgumentMatchers.anyString())).thenReturn(recipeOptional);

            Recipe recipeReturned = recipeService.findById("1L");
        });
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void deleteById() {
        String id = "3L";
        recipeService.deleteById(id);
        verify(recipeRepository, times(1)).deleteById(anyString());
    }
}