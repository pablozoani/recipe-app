package com.pablozoani.recipeapp.converter.tocommand;

import com.pablozoani.recipeapp.command.IngredientCommand;
import com.pablozoani.recipeapp.model.Ingredient;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToCommand unitOfMeasureToCommand;

    @Autowired
    public IngredientToCommand(UnitOfMeasureToCommand unitOfMeasureToCommand) {
        this.unitOfMeasureToCommand = unitOfMeasureToCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null) return null;
        final IngredientCommand output = new IngredientCommand();
        output.setId(source.getId());
        output.setDescription(source.getDescription());
        output.setAmount(source.getAmount());
        output.setUnitOfMeasure(unitOfMeasureToCommand.convert(source.getUnitOfMeasure()));
        return output;
    }
}
