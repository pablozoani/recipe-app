package com.pablozoani.recipeapp.converter.fromcommand;

import com.pablozoani.recipeapp.command.CategoryCommand;
import com.pablozoani.recipeapp.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) return null;
        final Category output = new Category();
        output.setId(source.getId());
        output.setCategoryName(source.getCategoryName());
        return output;
    }
}
