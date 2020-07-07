package com.pablozoani.recipeapp.converter.fromcommand;

import com.pablozoani.recipeapp.command.UnitOfMeasureCommand;
import com.pablozoani.recipeapp.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null) return null;
        final UnitOfMeasure output = new UnitOfMeasure();
        output.setId(source.getId());
        output.setUnitOfMeasure(source.getUnitOfMeasure());
        return output;
    }
}
