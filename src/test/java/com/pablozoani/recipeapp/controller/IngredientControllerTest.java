package com.pablozoani.recipeapp.controller;

import com.pablozoani.recipeapp.Service.IngredientService;
import com.pablozoani.recipeapp.Service.RecipeService;
import com.pablozoani.recipeapp.Service.UnitOfMeasureService;
import com.pablozoani.recipeapp.command.IngredientCommand;
import com.pablozoani.recipeapp.command.RecipeCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    IngredientController ingredientController;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        initMocks(this);
        ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    void listIngredients() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);
        mockMvc
            .perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/list"))
            .andExpect(model().attributeExists("recipe"));
        verify(recipeService, times(1)).findRecipeCommandById(anyLong());
    }

    @Test
    void testShowIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();

        when(ingredientService.findByRecipeIdAndId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        mockMvc
            .perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/2/show"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/show"))
            .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    void testUpdateIngredientForm() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(5L);
        ingredientCommand.setRecipeId(7L);

        when(ingredientService.findByRecipeIdAndId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.findAll()).thenReturn(new HashSet<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/7/ingredient/5/update"))
               .andExpect(status().isOk())
               .andExpect(view().name("/recipe/ingredient/ingredientform"))
               .andExpect(model().attributeExists("ingredient"))
               .andExpect(model().attributeExists("unitOfMeasureList"));
    }

    @Test
    void saveOrUpdateIngredientTest() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(2L);
        ingredientCommand.setRecipeId(5L);

        when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/2/ingredient"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/recipe/5/ingredient/2/show"));
    }

    @Test
    void newIngredientFormTest() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(7L);

        Mockito.when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);
        Mockito.when(unitOfMeasureService.findAll()).thenReturn(new HashSet<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/new"))
               .andExpect(status().isOk())
               .andExpect(view().name("recipe/ingredient/ingredientform"))
               .andExpect(model().attributeExists("ingredient"))
               .andExpect(model().attributeExists("unitOfMeasureList"));

        Mockito.verify(recipeService, times(1)).findRecipeCommandById(anyLong());
    }
}