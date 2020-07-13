package com.pablozoani.recipeapp.controller;

import com.pablozoani.recipeapp.Service.IngredientService;
import com.pablozoani.recipeapp.Service.RecipeService;
import com.pablozoani.recipeapp.command.RecipeCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model) {
        RecipeCommand command = recipeService.findRecipeCommandById(Long.valueOf(id));
        log.debug("Command Recipe Ingredients: " + command.getIngredients());
        model.addAttribute("recipe", command);
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id,
                                       Model model) {
        model.addAttribute("ingredient",
                           ingredientService.findByRecipeIdAndId(Long.valueOf(recipeId), Long.valueOf(id)));
        return "recipe/ingredient/show";
    }
}