package com.pablozoani.recipeapp.controller;

import com.pablozoani.recipeapp.Service.RecipeService;
import com.pablozoani.recipeapp.command.RecipeCommand;
import com.pablozoani.recipeapp.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model) {
        log.debug("newRecipe() called");
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        log.debug("showById() called");
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
        return "recipe/show";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        log.debug("updateRecipe() called");
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        log.debug("deleteById() called, id: " + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @PostMapping("/recipe/")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand command) {
        log.debug("saveOrUpdateRecipe() called");
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("404 Not Found");
        return new ModelAndView("/recipe/404error", "exception", exception);
    }
}
