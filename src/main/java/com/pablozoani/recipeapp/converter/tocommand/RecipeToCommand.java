package com.pablozoani.recipeapp.converter.tocommand;

import com.pablozoani.recipeapp.command.RecipeCommand;
import com.pablozoani.recipeapp.model.Recipe;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToCommand notesToCommand;

    private final CategoryToCommand categoryToCommand;

    private final IngredientToCommand ingredientToCommand;

    @Autowired
    public RecipeToCommand(NotesToCommand notesToCommand,
                           CategoryToCommand categoryToCommand,
                           IngredientToCommand ingredientToCommand) {
        this.notesToCommand = notesToCommand;
        this.categoryToCommand = categoryToCommand;
        this.ingredientToCommand = ingredientToCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) return null;
        final RecipeCommand output = new RecipeCommand();
        output.setId(source.getId());
        output.setCookTime(source.getCookTime());
        output.setDescription(source.getDescription());
        output.setDifficulty(source.getDifficulty());
        output.setDirections(source.getDirections());
        output.setPrepTime(source.getPrepTime());
        output.setServings(source.getServings());
        output.setUrl(source.getUrl());
        output.setSource(source.getSource());
        output.setImage(source.getImage());
        output.setNotes(notesToCommand.convert(source.getNotes()));
        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                  .forEach(ingredient -> output.getIngredients().add(ingredientToCommand.convert(ingredient)));
        }
        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                  .forEach(category -> output.getCategories().add(categoryToCommand.convert(category)));
        }
        return output;
    }
}
