package com.pablozoani.recipeapp.converter.tocommand;

import com.pablozoani.recipeapp.command.CategoryCommand;
import com.pablozoani.recipeapp.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) return null;
        final CategoryCommand output = new CategoryCommand();
        output.setId(source.getId());
        output.setCategoryName((source.getCategoryName()));
        return output;
    }
}
