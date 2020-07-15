package com.pablozoani.recipeapp.converter.fromcommand;

import com.pablozoani.recipeapp.command.RecipeCommand;
import com.pablozoani.recipeapp.model.Recipe;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CommandToNotes commandToNotes;

    private final CommandToCategory commandToCategory;

    private final CommandToIngredient commandToIngredient;

    @Autowired
    public CommandToRecipe(CommandToNotes commandToNotes,
                           CommandToCategory commandToCategory,
                           CommandToIngredient commandToIngredient) {
        this.commandToNotes = commandToNotes;
        this.commandToCategory = commandToCategory;
        this.commandToIngredient = commandToIngredient;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) return null;
        final Recipe output = new Recipe();
        output.setId(source.getId());
        output.setUrl(source.getUrl());
        output.setServings(source.getServings());
        output.setDirections(source.getDirections());
        output.setDifficulty(source.getDifficulty());
        output.setPrepTime(source.getPrepTime());
        output.setCookTime(source.getCookTime());
        output.setSource(source.getSource());
        output.setDescription(source.getDescription());
        output.setImage(source.getImage());
        output.setNotes(commandToNotes.convert(source.getNotes()));
        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                  .forEach(categoryCommand -> {
                      output.getCategories().add(commandToCategory.convert(categoryCommand));
                  });
        }
        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                  .forEach(ingredientCommand -> {
                      output.getIngredients().add(commandToIngredient.convert(ingredientCommand));
                  });
        }
        return output;
    }
}
