package com.pablozoani.recipeapp.converter.tocommand;

import com.pablozoani.recipeapp.command.UnitOfMeasureCommand;
import com.pablozoani.recipeapp.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if (source == null) return null;
        final UnitOfMeasureCommand output = new UnitOfMeasureCommand();
        output.setId(source.getId());
        output.setUnitOfMeasure(source.getUnitOfMeasure());
        return output;
    }
}
