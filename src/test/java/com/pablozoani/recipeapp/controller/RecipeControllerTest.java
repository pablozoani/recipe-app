package com.pablozoani.recipeapp.controller;

import com.pablozoani.recipeapp.Service.RecipeService;
import com.pablozoani.recipeapp.command.RecipeCommand;
import com.pablozoani.recipeapp.exception.NotFoundException;
import com.pablozoani.recipeapp.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
                                 .setControllerAdvice(new ExceptionHandlerController())
                                 .build();
    }

    @Test
    public void testGetRecipe() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("abcd");
        when(recipeService.findById(anyString())).thenReturn(recipe);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/17/show"))
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("recipe"))
               .andExpect(view().name("recipe/show"));
    }

    @Test
    void getRecipeNotFoundException() throws Exception {
        Mockito.when(recipeService.findById(anyString())).thenThrow(NotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
               .andExpect(MockMvcResultMatchers.view().name("/recipe/404error"));
    }

    @Test
    void testGetNewRecipeForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
               .andExpect(status().isOk())
               .andExpect(view().name("recipe/recipeform"))
               .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testPostNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId("abcd");

        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        mockMvc
            .perform(MockMvcRequestBuilders.post("/recipe/")
                                           .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                           .param("url", "http://abcd.com").param("description", "more text"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/" + command.getId() + "/show"));
    }

    @Test
    void testPostNewRecipeFormValidationFail() throws Exception {
        when(recipeService.saveRecipeCommand(any())).thenReturn(new RecipeCommand());

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/")
                                              .contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/recipeform"));
    }

    @Test
    void testGetUpdateView() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId("abcd");

        when(recipeService.findRecipeCommandById(anyString())).thenReturn(command);

        mockMvc
            .perform(MockMvcRequestBuilders.get("/recipe/2/update"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/recipeform"))
            .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testDeleteAction() throws Exception {
        mockMvc
            .perform(MockMvcRequestBuilders.get("/recipe/1/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"));
        verify(recipeService, times(1)).deleteById(anyString());
    }
}