package com.pablozoani.recipeapp.converter.fromcommand;

import com.pablozoani.recipeapp.command.IngredientCommand;
import com.pablozoani.recipeapp.model.Ingredient;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final CommandToUnitOfMeasure commandToUnitOfMeasure;

    @Autowired
    public CommandToIngredient(CommandToUnitOfMeasure commandToUnitOfMeasure) {
        this.commandToUnitOfMeasure = commandToUnitOfMeasure;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) return null;
        final Ingredient output = new Ingredient();
        output.setId(source.getId());
        output.setDescription(source.getDescription());
        output.setAmount(source.getAmount());
        output.setUnitOfMeasure(commandToUnitOfMeasure.convert(source.getUnitOfMeasure()));
        return output;
    }
}
