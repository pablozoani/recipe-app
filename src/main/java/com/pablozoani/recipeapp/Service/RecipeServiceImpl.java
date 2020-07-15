package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.command.RecipeCommand;
import com.pablozoani.recipeapp.converter.fromcommand.CommandToRecipe;
import com.pablozoani.recipeapp.converter.tocommand.RecipeToCommand;
import com.pablozoani.recipeapp.exception.NotFoundException;
import com.pablozoani.recipeapp.model.Recipe;
import com.pablozoani.recipeapp.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final CommandToRecipe commandToRecipe;

    private final RecipeToCommand recipeToCommand;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             CommandToRecipe commandToRecipe,
                             RecipeToCommand recipeToCommand) {
        this.recipeRepository = recipeRepository;
        this.commandToRecipe = commandToRecipe;
        this.recipeToCommand = recipeToCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id)
                               .orElseThrow(() -> new NotFoundException("Recipe Not Found"));
    }

    @Transactional
    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe recipe = commandToRecipe.convert(recipeCommand);
        log.debug("saveRecipeCommand(), detached recipe id: " + recipe.getId());
        Recipe savedRecipe = recipeRepository.save(recipe);
        log.debug("saveRecipeCommand(), saved recipe id: " + savedRecipe.getId());
        return recipeToCommand.convert(savedRecipe);
    }

    @Transactional
    @Override
    public RecipeCommand findRecipeCommandById(Long id) {
        Recipe recipe = recipeRepository.findById(id).get();
        log.debug("findRecipeCommandById(), recipe id: " + recipe.getId());
        return recipeToCommand.convert(recipe);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleteById() called, id: " + id);
        recipeRepository.deleteById(id);
    }
}
